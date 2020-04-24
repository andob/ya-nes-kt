package ro.dobrescuandrei.yaktnes

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ro.dobrescuandrei.yaktnes.rom.toNesRomFile
import java.io.File

fun main(args : Array<String>)
{
    if (args.size!=1)
        throw RuntimeException("Syntax: emulator <.NES ROM file>")

    val file=File(args.first().replace("~", System.getProperty("user.home")))
    if (!file.exists())
        throw RuntimeException("ROM file does not exist!")

    NES.execute(romFile = file.toNesRomFile())

    val config=LwjglApplicationConfiguration()
    LwjglApplication(Renderer(), config)
}
