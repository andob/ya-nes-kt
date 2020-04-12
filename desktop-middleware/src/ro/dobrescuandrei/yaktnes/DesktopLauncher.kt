package ro.dobrescuandrei.yaktnes

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

fun main()
{
    val machineCode=MachineCode(
        code = byteArrayOf(
            0xA9.toByte(), 0x44.toByte(), //LDA $44
            0x85.toByte(), 0x99.toByte()  //STA $99
        ))

    CPU.execute(machineCode)

    val config=LwjglApplicationConfiguration()
    LwjglApplication(Renderer(), config)
}
