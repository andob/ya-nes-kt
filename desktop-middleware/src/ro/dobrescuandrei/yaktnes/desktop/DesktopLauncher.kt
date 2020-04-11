package ro.dobrescuandrei.yaktnes.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ro.dobrescuandrei.yaktnes.Renderer

fun main()
{
    val config=LwjglApplicationConfiguration()
    LwjglApplication(Renderer(), config)
}
