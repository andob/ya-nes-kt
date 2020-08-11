package ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

object InstructionArgumentFactory
{
    //http://www.obelisk.me.uk/6502/addressing.html
    //http://wiki.nesdev.com/w/index.php/CPU_addressing_modes
    fun getInt8Argument(addressingMode : AddressingMode, machineCode : MachineCode) : Int8
    {
        when(addressingMode)
        {
            AddressingMode.Immediate ->
            {
                //the argument is a constant
                return machineCode.nextInt8()
            }

            AddressingMode.Relative ->
            {
                //the argument is a delta (ex: +5, -3)
                return machineCode.nextProgramCounterDelta()
            }

            AddressingMode.ZeroPage,
            AddressingMode.ZeroPageX,
            AddressingMode.ZeroPageY,
            AddressingMode.Absolute,
            AddressingMode.AbsoluteX,
            AddressingMode.AbsoluteY,
            AddressingMode.Indirect,
            AddressingMode.IndirectX,
            AddressingMode.IndirectY ->
            {
                val pointer=getPointerArgument(addressingMode, machineCode)
                if (pointer is Pointer.ToCPUAccumulator)
                    return NES.CPU.A
                return NES.CPU_BUS[pointer]
            }

            else -> throw RuntimeException("Invalid addressing mode!")
        }
    }

    fun getPointerArgument(addressingMode : AddressingMode, machineCode : MachineCode) : Pointer
    {
        when(addressingMode)
        {
            AddressingMode.Accumulator ->
            {
                //the argument is a pointer to CPU accumulator
                return Pointer.ToCPUAccumulator()
            }

            AddressingMode.ZeroPage ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                return machineCode.nextZeroPagePointer()
            }

            AddressingMode.ZeroPageX ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                val pointer=machineCode.nextZeroPagePointer()
                return (pointer+NES.CPU.X).rem(0xFF+1)
            }

            AddressingMode.ZeroPageY ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                val pointer=machineCode.nextZeroPagePointer()
                return (pointer+NES.CPU.Y).rem(0xFF+1)
            }

            AddressingMode.Absolute ->
            {
                //the argument is a 16-bit pointer
                return machineCode.nextPointer()
            }

            AddressingMode.AbsoluteX ->
            {
                //the argument is a 16-bit pointer
                val pointer=machineCode.nextPointer()
                return (pointer+NES.CPU.X).rem(0xFFFF+1)
            }

            AddressingMode.AbsoluteY ->
            {
                //the argument is a pointer
                val pointer=machineCode.nextPointer()
                return (pointer+NES.CPU.Y).rem(0xFFFF+1)
            }

            AddressingMode.Indirect ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextPointer()
                val leastSignificantByte=NES.CPU_BUS[pointer.rem(0xFFFF+1)].toByte()
                val mostSignificantByte=NES.CPU_BUS[(pointer+1).rem(0xFFFF+1)].toByte()
                return Pointer(leastSignificantByte, mostSignificantByte)
            }

            AddressingMode.IndirectX ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextZeroPagePointer()
                val leastSignificantByte=NES.CPU_BUS[(pointer+NES.CPU.X).rem(0xFF+1)].toByte()
                val mostSignificantByte=NES.CPU_BUS[(pointer+NES.CPU.X+1).rem(0xFF+1)].toByte()
                return Pointer(leastSignificantByte, mostSignificantByte)
            }

            AddressingMode.IndirectY ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextZeroPagePointer()
                val leastSignificantByte=NES.CPU_BUS[pointer.rem(0xFF+1)].toByte()
                val mostSignificantByte=NES.CPU_BUS[(pointer+1).rem(0xFF+1)].toByte()
                val childPointer=Pointer(leastSignificantByte, mostSignificantByte)
                return (childPointer+NES.CPU.Y).rem(0xFFFF+1)
            }

            else -> throw RuntimeException("Invalid addressing mode!")
        }
    }
}
