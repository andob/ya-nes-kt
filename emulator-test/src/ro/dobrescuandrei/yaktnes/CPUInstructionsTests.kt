package ro.dobrescuandrei.yaktnes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.cpu.MachineCode

class CPUInstructionsTests
{
    private var machineCodeBytes = byteArrayOf()

    @Before
    fun before() = NES.reset()

    private fun exec(instructionName : String, addressingMode : AddressingMode, vararg arguments : Byte)
    {
        val definition=InstructionDefinitions[instructionName, addressingMode]!!
        machineCodeBytes+=byteArrayOf(definition.id)
        machineCodeBytes+=arguments
        NES.CPU.execute(MachineCode(machineCodeBytes))
    }

    @Test
    fun testLoadAndSaveAccumulator()
    {
        println("Loading constant 0x44 (68) into accumulator")
        exec("LDA", AddressingMode.Immediate, 0x44)
        assertEquals(NES.CPU.A, Int8(0x44))
        assertEquals(NES.CPU.programCounter, 2)
        assertEquals(NES.CPU.status.Z, false)
        assertEquals(NES.CPU.status.N, false)

        println("Storing accumulator valur into address 0x99")
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x0099.toUShort())], Int8(0x44))
        assertEquals(NES.CPU.programCounter, 4)

        println("Loading constant 0x00 (0) into accumulator")
        exec("LDA", AddressingMode.Immediate, 0x00)
        assertEquals(NES.CPU.A, Int8(0x00))
        assertEquals(NES.CPU.programCounter, 6)
        assertEquals(NES.CPU.status.Z, true)
        assertEquals(NES.CPU.status.N, false)

        println("Loading constant 0xff (-124) into accumulator")
        exec("LDA", AddressingMode.Immediate, 0xff.toByte())
        assertEquals(NES.CPU.A, Int8(0xff.toByte()))
        assertEquals(NES.CPU.programCounter, 8)
        assertEquals(NES.CPU.status.Z, false)
        assertEquals(NES.CPU.status.N, true)

        println("Loading value from zero page pointer 0x99 into accumulator")
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU.A, Int8(0x44))
        assertEquals(NES.CPU.programCounter, 10)

        println("Saving accumulator into zero page pointer 0x5f")
        exec("STA", AddressingMode.ZeroPage, 0x5f)
        assertEquals(NES.CPU_BUS[Pointer(0x005f.toUShort())], Int8(0x44))
        assertEquals(NES.CPU.programCounter, 12)

        println("Loading constant value 0xff into accumulator")
        exec("LDA", AddressingMode.Immediate, 0xff.toByte())
        assertEquals(NES.CPU.A, Int8(0xff.toByte()))
        assertEquals(NES.CPU.programCounter, 14)

        println("Loading value from zero page pointer 0x5f into accumulator")
        exec("LDA", AddressingMode.ZeroPage, 0x5f.toByte())
        assertEquals(NES.CPU.A, Int8(0x44.toByte()))
        assertEquals(NES.CPU.programCounter, 16)

        println("Saving value from accumulator into pointer with address 0x1234")
        exec("STA", AddressingMode.Absolute, 0x12, 0x34)
        assertEquals(NES.CPU_BUS[Pointer(0x1234.toUShort())], Int8(0x44))
        assertEquals(NES.CPU.programCounter, 19)

        println("Loading constant value 0xaa into accumulator")
        exec("LDA", AddressingMode.Immediate, 0xaa.toByte())
        assertEquals(NES.CPU.A, Int8(0xaa.toByte()))
        assertEquals(NES.CPU.programCounter, 21)

        println("Loading value from pointer with address 0x1234 into accumulator")
        exec("LDA", AddressingMode.Absolute, 0x12, 0x34)
        assertEquals(NES.CPU.A, Int8(0x44))
        assertEquals(NES.CPU.programCounter, 24)
    }

    @Test
    fun testLoadAndSaveXRegister()
    {
        println("Loading constant value 0x32 into X register")
        exec("LDX", AddressingMode.Immediate, 0x32)
        assertEquals(NES.CPU.X, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 2)

        println("Saving X register into zero page pointer with address 0x55")
        exec("STX", AddressingMode.ZeroPage, 0x55)
        assertEquals(NES.CPU_BUS[Pointer(0x55.toUShort())], Int8(0x32))
        assertEquals(NES.CPU.programCounter, 4)

        println("Loading constant value 0x64 into X register")
        exec("LDX", AddressingMode.Immediate, 0x64)
        assertEquals(NES.CPU.X, Int8(0x64))
        assertEquals(NES.CPU.programCounter, 6)

        println("Loading value from pointer with address 0x55 into X register")
        exec("LDX", AddressingMode.ZeroPage, 0x55)
        assertEquals(NES.CPU.X, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 8)

        println("Saving X register into pointer with address 0x9876")
        exec("STX", AddressingMode.Absolute, 0x98.toByte(), 0x76)
        assertEquals(NES.CPU_BUS[Pointer(0x9876.toUShort())], Int8(0x32))
        assertEquals(NES.CPU.programCounter, 11)

        println("Loading constant value 0x64 into X register")
        exec("LDX", AddressingMode.Immediate, 0x64)
        assertEquals(NES.CPU.X, Int8(0x64))
        assertEquals(NES.CPU.programCounter, 13)

        println("Loading value from pointer with address 0x9876 into X register")
        exec("LDX", AddressingMode.Absolute, 0x98.toByte(), 0x76)
        assertEquals(NES.CPU.X, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 16)
    }

    @Test
    fun testLoadAndSaveYRegister()
    {
        println("Loading constant value 0x32 into Y register")
        exec("LDY", AddressingMode.Immediate, 0x32)
        assertEquals(NES.CPU.Y, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 2)

        println("Saving Y register into zero page pointer with address 0x55")
        exec("STY", AddressingMode.ZeroPage, 0x55)
        assertEquals(NES.CPU_BUS[Pointer(0x55.toUShort())], Int8(0x32))
        assertEquals(NES.CPU.programCounter, 4)

        println("Loading constant value 0x64 into Y register")
        exec("LDY", AddressingMode.Immediate, 0x64)
        assertEquals(NES.CPU.Y, Int8(0x64))
        assertEquals(NES.CPU.programCounter, 6)

        println("Loading value from pointer with address 0x55 into Y register")
        exec("LDY", AddressingMode.ZeroPage, 0x55)
        assertEquals(NES.CPU.Y, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 8)

        println("Saving Y register into pointer with address 0x9876")
        exec("STY", AddressingMode.Absolute, 0x98.toByte(), 0x76)
        assertEquals(NES.CPU_BUS[Pointer(0x9876.toUShort())], Int8(0x32))
        assertEquals(NES.CPU.programCounter, 11)

        println("Loading constant value 0x64 into Y register")
        exec("LDY", AddressingMode.Immediate, 0x64)
        assertEquals(NES.CPU.Y, Int8(0x64))
        assertEquals(NES.CPU.programCounter, 13)

        println("Loading value from pointer with address 0x9876 into Y register")
        exec("LDY", AddressingMode.Absolute, 0x98.toByte(), 0x76)
        assertEquals(NES.CPU.Y, Int8(0x32))
        assertEquals(NES.CPU.programCounter, 16)
    }

    @Test
    fun testLoadAndSaveRegisteresWithIndexedAddressing()
    {
        TODO("lda zero page x")
        TODO("todo sta zero page x")

        TODO("todo ldy zero page x")
        TODO("todo sty zero page x")

        TODO("todo ldx zero page y")
        TODO("todo stx zero page y")

        TODO("todo lda absolute x")
        TODO("todo sta absolute x")

        TODO("todo lda absolute y")
        TODO("todo sta absolute y")

        TODO("todo lda indirect x")
        TODO("todo sta indirect x")

        TODO("todo lda indirect y")
        TODO("todo sta indirect y")

        TODO("todo ldx absolute y")

        TODO("todo ldy absolute x")
    }

    @Test
    fun testRegisterOperationsInstructions()
    {
        println("load 0x12 into A, 0x34 into X, transfer A to X")
        exec("LDA", AddressingMode.Immediate, 0x12)
        exec("LDX", AddressingMode.Immediate, 0x34)
        exec("TAX", AddressingMode.Implicit)
        assertEquals(NES.CPU.X, NES.CPU.A)
        assertEquals(NES.CPU.X, Int8(0x12))

        println("decrement X")
        exec("DEX", AddressingMode.Implicit)
        assertEquals(NES.CPU.X, Int8(0x11))

        println("increment X twice")
        exec("INX", AddressingMode.Implicit)
        exec("INX", AddressingMode.Implicit)
        assertEquals(NES.CPU.X, Int8(0x13))

        println("load 0x42 into X, 0x18 into A, transfer X to A")
        exec("LDX", AddressingMode.Immediate, 0x42)
        exec("LDA", AddressingMode.Immediate, 0x18)
        exec("TXA", AddressingMode.Implicit)
        assertEquals(NES.CPU.A, NES.CPU.X)
        assertEquals(NES.CPU.A, Int8(0x42))

        println("load 0x12 into A, 0x34 into Y, transfer A to Y")
        exec("LDA", AddressingMode.Immediate, 0x12)
        exec("LDY", AddressingMode.Immediate, 0x34)
        exec("TAY", AddressingMode.Implicit)
        assertEquals(NES.CPU.Y, NES.CPU.A)
        assertEquals(NES.CPU.Y, Int8(0x12))

        println("decrement Y")
        exec("DEY", AddressingMode.Implicit)
        assertEquals(NES.CPU.Y, Int8(0x11))

        println("increment Y twice")
        exec("INY", AddressingMode.Implicit)
        exec("INY", AddressingMode.Implicit)
        assertEquals(NES.CPU.Y, Int8(0x13))

        println("load 0x42 into Y, 0x18 into A, transfer Y to A")
        exec("LDY", AddressingMode.Immediate, 0x42)
        exec("LDA", AddressingMode.Immediate, 0x18)
        exec("TYA", AddressingMode.Implicit)
        assertEquals(NES.CPU.A, NES.CPU.Y)
        assertEquals(NES.CPU.A, Int8(0x42))

        println("load 0x00 into X, decrement X, the result should be 0xff")
        exec("LDX", AddressingMode.Immediate, 0x00)
        exec("DEX", AddressingMode.Implicit)
        assertEquals(NES.CPU.X, Int8(0xff.toByte()))
        assertEquals(NES.CPU.status.V, false)

        println("load 0xff into X, increment X, the result should be 0x00")
        exec("LDX", AddressingMode.Immediate, 0xff.toByte())
        exec("INX", AddressingMode.Implicit)
        assertEquals(NES.CPU.X, Int8(0x00))
        assertEquals(NES.CPU.status.V, false)
    }
}