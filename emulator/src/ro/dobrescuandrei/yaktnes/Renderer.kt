package ro.dobrescuandrei.yaktnes

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Renderer : ApplicationAdapter()
{
    val shapeRenderer by lazy {
        val shapeRenderer=ShapeRenderer()
        shapeRenderer.setAutoShapeType(true)
        shapeRenderer.color=Color.WHITE
        return@lazy shapeRenderer
    }

    override fun create()
    {
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin()
        shapeRenderer.rect(10.0f, 10.0f, 10.0f, 10.0f)
        shapeRenderer.end()
    }

    override fun dispose()
    {
        shapeRenderer.dispose()
    }
}
