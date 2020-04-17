package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta

class MachineCode
(
    val code : ByteArray
)
{
    fun hasNextByte() : Boolean
    {
        return NES.CPU.programCounter<code.size
    }

    fun nextByte() : Byte
    {
        val byte=code[NES.CPU.programCounter]
        NES.CPU.programCounter++
        return byte
    }

    fun nextInt8() : Int8
    {
        return Int8(nextByte())
    }

    fun nextProgramCounterDelta() : ProgramCounterDelta
    {
        return Int8(nextByte())
    }

    fun nextZeroPagePointer() : Pointer
    {
        val leastSignificantByte=0x00.toByte()
        val mostSignificantByte=nextByte()
        return Pointer(leastSignificantByte, mostSignificantByte)
    }

    fun nextPointer() : Pointer
    {
        val leastSignificantByte=nextByte()
        val mostSignificantByte=nextByte()
        return Pointer(leastSignificantByte, mostSignificantByte)
    }
}
