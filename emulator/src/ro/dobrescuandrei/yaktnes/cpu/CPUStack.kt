package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import java.util.*

class CPUStack : Stack<Int8>()
{
    val stackPointer get() = (0xFF-size).toInt8()

    @ExperimentalUnsignedTypes
    fun seekTo(stackPointer : Int8)
    {
        val finalStackSize=(0xFF.toUByte()-stackPointer.toInt().toUByte()).toInt()
        while (finalStackSize>size) push(Int8.Zero)
        while (finalStackSize<size) pop()
    }
}
