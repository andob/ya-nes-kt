package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta

@OptIn(ExperimentalUnsignedTypes::class)
class MachineCode
(
    private val code : ByteArray
)
{
    companion object
    {
        val START_ADDRESS = 0x4020.toUShort()
    }

    private fun Pointer.ToMachineCode.toIndex() =
        (toUShort()-START_ADDRESS).toInt().rem(code.size)

    operator fun get(pointer : Pointer.ToMachineCode) : Int8
    {
        return code[pointer.toIndex()].toInt8()
    }

    operator fun set(pointer : Pointer.ToMachineCode, value : Int8)
    {
        code[pointer.toIndex()]=value.toByte()
    }

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
        val mostSignificantByte=nextByte()
        val leastSignificantByte=nextByte()
        return Pointer(leastSignificantByte, mostSignificantByte)
    }
}
