package ro.dobrescuandrei.yaktnes.ppu.nametable

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8

//one nametable = 1KB
class Nametable
(
    val startAddress : Pointer
)
{
    val size get() = 1024

    private val values = ByteArray(size)

    private fun Pointer.toIndex() =
        (toUInt().toInt()-startAddress.toUInt().toInt()).rem(size)

    fun canAcceptPointer(pointer : Pointer) =
        startAddress<=pointer&&pointer<startAddress+size

    operator fun get(pointer : Pointer) : Int8
    {
        return values[pointer.toIndex()].toInt8()
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        values[pointer.toIndex()]=value.toByte()
    }
}
