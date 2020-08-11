package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

class Renderer : ApplicationAdapter()
{
    val shapeRenderer by lazy {
        val shapeRenderer=ShapeRenderer()
        shapeRenderer.setAutoShapeType(true)
        shapeRenderer.color=Color.WHITE
        return@lazy shapeRenderer
    }

    var tileColorMatrices = listOf<ColorMatrix>()

    var renderingStep = 0

    override fun create()
    {
        super.create()

        NES.PPU.colorPalettes[0x3F00.toPointer()]=Color.CLEAR
        NES.PPU.colorPalettes[0x3F01.toPointer()]=Color.RED
        NES.PPU.colorPalettes[0x3F02.toPointer()]=Color.GREEN
        NES.PPU.colorPalettes[0x3F03.toPointer()]=Color.BLUE

        val colorPalette=NES.PPU.colorPalettes.getBackgroundPalettes().first()
        val numberOfTiles=NES.RunningRomFile!!.characterRom.rom.size/16
        val pointersToTiles=(0 until numberOfTiles).map { Pointer((it*16).toUShort()) }
        val tiles=pointersToTiles.map { NES.RunningRomFile!!.characterRom.getTile(it) }
        tileColorMatrices=tiles.map { it.toColorMatrix(colorPalette) }
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        val pixelSize=10.0f
        val colorMatrix=tileColorMatrices[renderingStep]
        for (y in 0 until 8) for (x in 0 until 8)
        {
            val xOnScreen=pixelSize*x
            val yOnScreen=pixelSize*y
            shapeRenderer.color=colorMatrix[8-y-1][8-x-1]
            shapeRenderer.rect(xOnScreen, yOnScreen, pixelSize, pixelSize)
        }

        shapeRenderer.end()

        renderingStep++
        if (renderingStep>=tileColorMatrices.size)
            renderingStep=0

        Thread.sleep(250)
    }

    override fun dispose()
    {
        shapeRenderer.dispose()
    }
}
