package ro.dobrescuandrei.yaktnes.cpu.machine_code

import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer

class MachineCode
(
    val code : ByteArray
)
{
    private var currentIndex = 0

    fun hasNext() : Boolean
    {
        return currentIndex<code.size
    }

    fun nextByte() : Byte
    {
        val byte=code[currentIndex]
        currentIndex++
        return byte
    }

    fun nextDecimal() : Decimal
    {
        return Decimal(nextByte())
    }

    fun nextZeroPagePointer() : Pointer
    {
        return Pointer(nextByte().toShort())
    }

    fun nextPointer() : Pointer
    {
        val leastSignificantByte=nextByte()
        val mostSignificantByte=nextByte()
        return Pointer(leastSignificantByte, mostSignificantByte)
    }
}
