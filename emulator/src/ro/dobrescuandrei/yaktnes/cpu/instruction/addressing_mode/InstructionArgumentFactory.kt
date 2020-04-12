package ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode

import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.cpu.CpuBus
import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.machine_code.MachineCode

object InstructionArgumentFactory
{
    //http://www.obelisk.me.uk/6502/addressing.html
    //http://wiki.nesdev.com/w/index.php/CPU_addressing_modes
    fun getDecimalArgument(addressingMode : AddressingMode, machineCode : MachineCode) : Decimal
    {
        when(addressingMode)
        {
            AddressingMode.Immediate ->
            {
                //the argument is a constant
                return machineCode.nextDecimal()
            }

            AddressingMode.Relative ->
            {
                return machineCode.nextDecimal()
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
                return CpuBus[pointer]
            }

            else -> throw RuntimeException("Invalid addressing mode!")
        }
    }

    fun getPointerArgument(addressingMode : AddressingMode, machineCode : MachineCode) : Pointer
    {
        when(addressingMode)
        {
            AddressingMode.ZeroPage ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                return machineCode.nextZeroPagePointer()
            }

            AddressingMode.ZeroPageX ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                val pointer=machineCode.nextZeroPagePointer()
                return (pointer+CPU.X).rem(0xFF)
            }

            AddressingMode.ZeroPageY ->
            {
                //the argument is a pointer from Zero-Page (#0000 -> #00FF)
                val pointer=machineCode.nextZeroPagePointer()
                return (pointer+CPU.Y).rem(0xFF)
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
                return (pointer+CPU.X).rem(0xFF)
            }

            AddressingMode.AbsoluteY ->
            {
                //the argument is a pointer
                val pointer=machineCode.nextPointer()
                return (pointer+CPU.Y).rem(0xFF)
            }

            AddressingMode.Indirect ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextPointer()
                val leastSignificantByte=CpuBus[pointer.rem(0xFF)].toByte()
                val mostSignificantByte=CpuBus[(pointer+1).rem(0xFF)].toByte()
                return Pointer(leastSignificantByte, mostSignificantByte)
            }

            AddressingMode.IndirectX ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextPointer()
                val leastSignificantByte=CpuBus[(pointer+CPU.X).rem(0xFF)].toByte()
                val mostSignificantByte=CpuBus[(pointer+CPU.X+1).rem(0xFF)].toByte()
                return Pointer(leastSignificantByte, mostSignificantByte)
            }

            AddressingMode.IndirectY ->
            {
                //the argument is a pointer to pointer
                val pointer=machineCode.nextPointer()
                val leastSignificantByte=CpuBus[pointer.rem(0xFF)].toByte()
                val mostSignificantByte=CpuBus[(pointer+1).rem(0xFF)].toByte()
                val childPointer=Pointer(leastSignificantByte, mostSignificantByte)
                return (childPointer+CPU.Y).rem(0xFF)
            }

            else -> throw RuntimeException("Invalid addressing mode!")
        }
    }
}
