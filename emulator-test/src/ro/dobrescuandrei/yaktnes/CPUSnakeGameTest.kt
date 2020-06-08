package ro.dobrescuandrei.yaktnes

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.nextInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.utils.withCPUTestEnvironment
import kotlin.random.Random

//SNAKE GAME SOURCE CODE: https://gist.github.com/wkjagt/9043907

fun main()
{
    val config=LwjglApplicationConfiguration()
    LwjglApplication(SnakeGameRenderer(), config)
}

@ExperimentalUnsignedTypes
class SnakeGameRenderer : ApplicationAdapter(), InputProcessor
{
    private val randomiser = Random(System.currentTimeMillis())

    private val shapeRenderer by lazy {
        val shapeRenderer=ShapeRenderer()
        shapeRenderer.setAutoShapeType(true)
        shapeRenderer.color=Color.WHITE
        return@lazy shapeRenderer
    }

    private val spriteBatch by lazy { SpriteBatch() }

    private val bitmapFont by lazy {
        val font=BitmapFont()
        font.color=Color.LIGHT_GRAY
        return@lazy font
    }

    class Block
    (
        val x : Float,
        val y : Float
    )
    {
        companion object
        {
            val width : Float = Gdx.graphics.width.toFloat()/32
            val height : Float = Gdx.graphics.height.toFloat()/32

            private val positionPointerMatrix =
                (0x0200 until 0x0600 step 0x20).toList().map { row ->
                    (0x00 until 0x20).map { column ->
                        Pointer((row+column+1).toUShort())
                    }
                }

            fun fromPositionPointer(positionPointer : Pointer) : Block
            {
                for (y in positionPointerMatrix.indices)
                    for (x in positionPointerMatrix[y].indices)
                        if (positionPointerMatrix[y][x]==positionPointer)
                            return Block(x = x*width, y = Gdx.graphics.height-y*height-height)
                return Block(x = Float.MAX_VALUE, y = Float.MAX_VALUE)
            }
        }
    }

    private fun getAppleBlock() : Block
    {
        val mostSignificantByte=NES.CPU_BUS[0x00.toPointer()].toUByte().toByte()
        val leastSignificantByte=NES.CPU_BUS[0x01.toPointer()].toUByte().toByte()
        val positionPointer=Pointer(leastSignificantByte, mostSignificantByte)

        bitmapFont.draw(spriteBatch, "APPLE is at $positionPointer", 10.0f, 50.0f)

        return Block.fromPositionPointer(positionPointer)
    }

    private fun getSnakeBlocks() : List<Block>
    {
        val numberOfBlocks=(NES.CPU_BUS[0x03.toPointer()].toInt()/2)+1
        val blocks=mutableListOf<Block>()

        for (blockIndex in 0 until numberOfBlocks)
        {
            val mostSignificantByte=NES.CPU_BUS[(0x10+blockIndex*2).toPointer()].toUByte().toByte()
            val leastSignificantByte=NES.CPU_BUS[(0x11+blockIndex*2).toPointer()].toUByte().toByte()
            val positionPointer=Pointer(leastSignificantByte, mostSignificantByte)

            bitmapFont.draw(spriteBatch, "SNAKE[$blockIndex] is at $positionPointer", 10.0f, 70.0f+20.0f*blockIndex)

            blocks.add(Block.fromPositionPointer(positionPointer))
        }

        return blocks
    }

    override fun create()
    {
        this::class.java.classLoader.getResourceAsStream("snake6502.bin")!!.use { inputStream ->
            val machineCode=MachineCode(inputStream.readBytes())

            Thread {
                withCPUTestEnvironment {
                    NES.CPU.clock=Clock.withSpeedInKiloHertz(25f)
                    NES.CPU.execute(machineCode)
                }
            }.start()
        }

        Gdx.input.inputProcessor=this
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        //0xFE always contains a random number
        NES.CPU_BUS[0xFE.toPointer()]=randomiser.nextInt8()

        shapeRenderer.begin()
        spriteBatch.begin()

        val appleBlock=getAppleBlock()
        shapeRenderer.ellipse(appleBlock.x, appleBlock.y, Block.width, Block.height)

        for (snakeBlock in getSnakeBlocks())
            shapeRenderer.rect(snakeBlock.x, snakeBlock.y, Block.width, Block.height)

        spriteBatch.end()
        shapeRenderer.end()
    }

    override fun keyDown(keycode : Int) : Boolean
    {
        when(keycode)
        {
            Input.Keys.UP -> NES.CPU_BUS[0xff.toPointer()]=0x77.toInt8()
            Input.Keys.DOWN -> NES.CPU_BUS[0xff.toPointer()]=0x73.toInt8()
            Input.Keys.LEFT -> NES.CPU_BUS[0xff.toPointer()]=0x61.toInt8()
            Input.Keys.RIGHT -> NES.CPU_BUS[0xff.toPointer()]=0x64.toInt8()
            else -> NES.CPU_BUS[0xff.toPointer()]=0x00.toInt8()
        }

        return false
    }

    override fun touchUp(screenX : Int, screenY : Int, pointer : Int, button : Int) : Boolean = false
    override fun touchDragged(screenX : Int, screenY : Int, pointer : Int) : Boolean = false
    override fun touchDown(screenX : Int, screenY : Int, pointer : Int, button : Int) : Boolean = false
    override fun mouseMoved(screenX : Int, screenY : Int) : Boolean = false
    override fun scrolled(amount : Int) : Boolean = false
    override fun keyTyped(character : Char) : Boolean = false
    override fun keyUp(keycode : Int) : Boolean = false

    override fun dispose()
    {
        shapeRenderer.dispose()
    }
}
