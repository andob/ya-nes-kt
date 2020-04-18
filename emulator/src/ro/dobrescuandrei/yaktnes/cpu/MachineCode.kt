package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta

@OptIn(ExperimentalUnsignedTypes::class)
class MachineCode
(
    val code : ByteArray
)
{
    companion object
    {
        val START_ADDRESS = 0x0600.toUShort()
    }

    private fun Pointer.ToMachineCode.toIndex() = (toUShort()-START_ADDRESS).toInt()

    fun hasNextByte() : Boolean
    {
        return NES.CPU.programCounter.toIndex()<code.size
    }

    fun nextByte() : Byte
    {
        val byte=code[NES.CPU.programCounter.toIndex()]
        NES.CPU.programCounter++
        return byte
    }

    fun nextInt8() : Int8
    {
        return nextByte().toInt8()
    }

    fun nextProgramCounterDelta() : ProgramCounterDelta
    {
        return nextByte().toInt8()
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
