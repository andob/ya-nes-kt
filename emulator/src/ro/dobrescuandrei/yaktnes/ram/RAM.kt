package ro.dobrescuandrei.yaktnes.ram

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8

//2KB of RAM
class RAM
{
    val size get() = 2*1024

    private val values = ByteArray(size)

    private fun Pointer.toIndex() = toUInt().toInt().rem(size)

    operator fun get(pointer : Pointer) : Int8
    {
        return values[pointer.toIndex()].toInt8()
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        values[pointer.toIndex()]=value.toByte()
    }
}
