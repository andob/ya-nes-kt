package ro.dobrescuandrei.yaktnes

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration

fun main()
{
    val config=LwjglApplicationConfiguration()
    LwjglApplication(Renderer(), config)
}
