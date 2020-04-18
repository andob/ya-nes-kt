package ro.dobrescuandrei.yaktnes

import org.junit.Test
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

//SNAKE GAME SOURCE CODE: https://gist.github.com/wkjagt/9043907
//COMPILED WITH: https://skilldrick.github.io/easy6502/
class CPUSnakeGameTest
{
    @Test
    fun runSnakeGame()
    {
        //todo graphics
        //todo keyboard input
        //todo slow down cpu (set cpu clock)
        this::class.java.classLoader.getResourceAsStream("snake6502.bin")!!.use { inputStream ->
            val machineCode=MachineCode(inputStream.readBytes())
            NES.CPU.execute(machineCode)
        }
    }
}
