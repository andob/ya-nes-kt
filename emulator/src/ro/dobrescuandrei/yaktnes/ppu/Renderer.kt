package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.utils.SizeF
import ro.dobrescuandrei.yaktnes.ppu.scanline.ScreenResolution as NESScreenResolution

class Renderer : ApplicationAdapter()
{
    private val shapeRenderer by lazy {
        val shapeRenderer=ShapeRenderer()
        shapeRenderer.setAutoShapeType(true)
        shapeRenderer.color=Color.WHITE
        return@lazy shapeRenderer
    }

    private val pixelSize by lazy {
        SizeF(width = Gdx.graphics.width/NESScreenResolution.width.toFloat(),
            height = Gdx.graphics.height/NESScreenResolution.height.toFloat())
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        NES.PPU.currentFrame.iterate { x, y, color ->
            shapeRenderer.color=color
            shapeRenderer.rect(
                /*x*/ x.toFloat()*pixelSize.width,
                /*y*/ y.toFloat()*pixelSize.height,
                /*width*/ pixelSize.width,
                /*height*/ pixelSize.height)
        }

        shapeRenderer.end()
    }

    override fun dispose()
    {
        shapeRenderer.dispose()
    }
}
