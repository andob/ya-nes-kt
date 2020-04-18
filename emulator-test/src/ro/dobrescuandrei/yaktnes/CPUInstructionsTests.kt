package ro.dobrescuandrei.yaktnes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.utils.withCPUTestEnvironment

class CPUInstructionsTests
{
    @Before
    fun before() = NES.reset()

    @Test
    fun testLoadAndSaveAccumulator() = withCPUTestEnvironment {
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
    fun testLoadAndSaveXRegister() = withCPUTestEnvironment {
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
    fun testLoadAndSaveYRegister() = withCPUTestEnvironment {
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
    fun testIndexedAddressing() = withCPUTestEnvironment {
        println("test zero page X indexing with LDA/STA")
        exec("LDA", AddressingMode.Immediate, 0x12.toByte())
        exec("LDX", AddressingMode.Immediate, 0xEE.toByte())
        exec("STA", AddressingMode.ZeroPageX, 0x41.toByte())
        exec("LDA", AddressingMode.Immediate, 0x00.toByte())
        exec("LDA", AddressingMode.ZeroPageX, 0x41.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x002F.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.A, Int8(0x12))

        println("test zero page X indexing with LDY/STY")
        exec("LDY", AddressingMode.Immediate, 0x12.toByte())
        exec("LDX", AddressingMode.Immediate, 0xEE.toByte())
        exec("STY", AddressingMode.ZeroPageX, 0x41.toByte())
        exec("LDY", AddressingMode.Immediate, 0x00.toByte())
        exec("LDY", AddressingMode.ZeroPageX, 0x41.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x002F.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.Y, Int8(0x12))

        println("test zero page Y indexing with LDX/STX")
        exec("LDX", AddressingMode.Immediate, 0x12.toByte())
        exec("LDY", AddressingMode.Immediate, 0xEE.toByte())
        exec("STX", AddressingMode.ZeroPageY, 0x41.toByte())
        exec("LDX", AddressingMode.Immediate, 0x00.toByte())
        exec("LDX", AddressingMode.ZeroPageY, 0x41.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x002F.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.X, Int8(0x12))

        println("test absolute X indexing with LDA/STA")
        exec("LDA", AddressingMode.Immediate, 0x12.toByte())
        exec("LDX", AddressingMode.Immediate, 0xEE.toByte())
        exec("STA", AddressingMode.AbsoluteX, 0x10.toByte(), 0x00.toByte())
        exec("LDA", AddressingMode.Immediate, 0x41.toByte())
        exec("LDA", AddressingMode.AbsoluteX, 0x10.toByte(), 0x00.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x10EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.A, Int8(0x12))

        println("test absolute Y indexing with LDA/STA")
        exec("LDA", AddressingMode.Immediate, 0x12.toByte())
        exec("LDY", AddressingMode.Immediate, 0xEE.toByte())
        exec("STA", AddressingMode.AbsoluteY, 0x10.toByte(), 0x00.toByte())
        exec("LDA", AddressingMode.Immediate, 0x41.toByte())
        exec("LDA", AddressingMode.AbsoluteY, 0x10.toByte(), 0x00.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x10EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.A, Int8(0x12))

        println("test absolute Y indexing with LDX")
        exec("LDX", AddressingMode.Immediate, 0x12.toByte())
        exec("LDY", AddressingMode.Immediate, 0xEE.toByte())
        exec("STX", AddressingMode.Absolute, 0x10.toByte(), 0xEE.toByte())
        exec("LDX", AddressingMode.Immediate, 0x41.toByte())
        exec("LDX", AddressingMode.AbsoluteY, 0x10.toByte(), 0x00.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x10EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.X, Int8(0x12))

        println("test absolute X indexing with LDY/STY")
        exec("LDY", AddressingMode.Immediate, 0x12.toByte())
        exec("LDX", AddressingMode.Immediate, 0xEE.toByte())
        exec("STY", AddressingMode.Absolute, 0x10.toByte(), 0xEE.toByte())
        exec("LDY", AddressingMode.Immediate, 0x41.toByte())
        exec("LDY", AddressingMode.AbsoluteX, 0x10.toByte(), 0x00.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x10EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.Y, Int8(0x12))

        println("test indirect X indexing with LDA/STA")
        exec("LDA", AddressingMode.Immediate, 0x12.toByte())
        exec("LDX", AddressingMode.Immediate, 0x00.toByte())
        exec("STX", AddressingMode.ZeroPage, 0x87.toByte())
        exec("LDX", AddressingMode.Immediate, 0xEE.toByte())
        exec("STX", AddressingMode.ZeroPage, 0x88.toByte())
        exec("STA", AddressingMode.IndirectX, 0x99.toByte())
        exec("LDA", AddressingMode.Immediate, 0x41.toByte())
        exec("LDA", AddressingMode.IndirectX, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x00EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.A, Int8(0x12))

        println("test indirect Y indexing with LDA/STA")
        exec("LDA", AddressingMode.Immediate, 0x12.toByte())
        exec("LDY", AddressingMode.Immediate, 0x00.toByte())
        exec("STY", AddressingMode.ZeroPage, 0x87.toByte())
        exec("LDY", AddressingMode.Immediate, 0xEE.toByte())
        exec("STY", AddressingMode.ZeroPage, 0x88.toByte())
        exec("STA", AddressingMode.IndirectY, 0x99.toByte())
        exec("LDA", AddressingMode.Immediate, 0x41.toByte())
        exec("LDA", AddressingMode.IndirectY, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x00EE.toUShort())], Int8(0x12))
        assertEquals(NES.CPU.A, Int8(0x12))

        TODO("test JMP with INDIRECT addressing")
    }

    @Test
    fun testRegisterOperationsInstructions() = withCPUTestEnvironment {
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

    @Test
    fun testIncrementAndDecrementMemoryInstructions() = withCPUTestEnvironment {
        println("load 0x11 into 0x99, increment memory, decrement memory")
        exec("LDA", AddressingMode.Immediate, 0x11)
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("INC", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x0099.toUShort())], Int8(0x12))
        exec("DEC", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x0099.toUShort())], Int8(0x11))

        println("load 0x00 into 0x99, decrement memory, increment memory")
        exec("LDA", AddressingMode.Immediate, 0x00)
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("DEC", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x0099.toUShort())], Int8(0xFF.toByte()))
        exec("INC", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU_BUS[Pointer(0x0099.toUShort())], Int8(0x00))
    }

    @Test
    fun testAddAndSubstractWithCarryInstructions() = withCPUTestEnvironment {
        println("Add without carry: 0x05 + 0x05 = 0x0A")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("ADC", AddressingMode.Immediate, 0x05)
        assertEquals(NES.CPU.A, Int8(0x0A))
        assertEquals(NES.CPU.status.C, false)
        assertEquals(NES.CPU.status.V, false)

        println("Add with carry: 0x40 + 0xff = 0x3f + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0x40)
        exec("ADC", AddressingMode.Immediate, 0xff.toByte())
        assertEquals(NES.CPU.A, Int8(0x3f))
        assertEquals(NES.CPU.status.C, true)
        assertEquals(NES.CPU.status.V, false)

        println("Let's use the carry flag: 0x3f + 0x02 + CARRY 1 = 0x41 + CARRY 1 = 0x42")
        exec("ADC", AddressingMode.Immediate, 0x02)
        assertEquals(NES.CPU.A, Int8(0x42))
        assertEquals(NES.CPU.status.C, false)
        assertEquals(NES.CPU.status.V, false)

        println("Add with overflow: 0x7F + 0x01 = 0x80 + OVERFLOW")
        exec("LDA", AddressingMode.Immediate, 0x7F)
        exec("ADC", AddressingMode.Immediate, 0x01)
        assertEquals(NES.CPU.A, Int8(0x80.toByte()))
        assertEquals(NES.CPU.status.C, false)
        assertEquals(NES.CPU.status.V, true)

        println("Test substract with carry: 0x05 - 0x03 - 1 = 0x01 + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("SBC", AddressingMode.Immediate, 0x03)
        assertEquals(NES.CPU.A, Int8(0x01.toByte()))
        assertEquals(NES.CPU.status.N, false)
        assertEquals(NES.CPU.status.C, true)
        assertEquals(NES.CPU.status.V, false)

        println("Clearing carry")
        exec("CLC", AddressingMode.Implicit)

        println("Test substract with carry: 0x05 - 0x05 - 1 = 0xFF")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("SBC", AddressingMode.Immediate, 0x05)
        assertEquals(NES.CPU.A, Int8(0xFF.toByte()))
        assertEquals(NES.CPU.status.N, true)
        assertEquals(NES.CPU.status.C, false)
        assertEquals(NES.CPU.status.V, false)
    }

    @Test
    fun testBitwiseOperations() = withCPUTestEnvironment {
        println("Bitwise AND: 0x05 AND 0x41 = 0x01")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("AND", AddressingMode.Immediate, 0x41)
        assertEquals(NES.CPU.A, Int8(0x01))

        println("Bitwise XOR: 0x05 XOR 0x41 = 0x44")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("EOR", AddressingMode.Immediate, 0x41)
        assertEquals(NES.CPU.A, Int8(0x44))

        println("Bitwise ORA: 0x05 OR 0x41 = 0x45")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("ORA", AddressingMode.Immediate, 0x41)
        assertEquals(NES.CPU.A, Int8(0x45))

        println("Bitwise shift left: 0x05 << 1 = 0x0A")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("ASL", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0x0A))
        assertEquals(NES.CPU.status.C, false)

        println("Bitwise shift left: 0xFF << 1 = 0xFE + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0xFF.toByte())
        exec("ASL", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0xFE.toByte()))
        assertEquals(NES.CPU.status.C, true)

        println("Bitwise shift left: 0x07 << 1 = 0x0E")
        exec("LDA", AddressingMode.Immediate, 0x07)
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("ASL", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU.A, Int8(0x0E.toByte()))
        assertEquals(NES.CPU.status.C, false)

        println("Bitwise shift left: 0xFF << 1 = 0xFE + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0xFF.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("ASL", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU.A, Int8(0xFE.toByte()))
        assertEquals(NES.CPU.status.C, true)

        println("Bitwise shift right: 0x05 >> 1 = 0x02 + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0x05)
        exec("LSR", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0x02))
        assertEquals(NES.CPU.status.C, true)

        println("Bitwise shift right: 0xFF >> 1 = 0x7F + CARRY 1")
        exec("LDA", AddressingMode.Immediate, 0xFF.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LSR", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU.A, Int8(0x7F))
        assertEquals(NES.CPU.status.C, true)

        println("Bitwise shift right: 0x1E >> 1 = 0x0F")
        exec("LDA", AddressingMode.Immediate, 0x1E)
        exec("LSR", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0x0F))
        assertEquals(NES.CPU.status.C, false)

        println("Rotate right: B5 >>< 3 = B6")
        exec("LDA", AddressingMode.Immediate, 0xB5.toByte())
        exec("ROR", AddressingMode.Accumulator)
        exec("ROR", AddressingMode.Accumulator)
        exec("ROR", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0xB6.toByte()))
        assertEquals(NES.CPU.status.C, true)

        println("Rotate left: B5 <<> 3 = AD")
        exec("LDA", AddressingMode.Immediate, 0xB5.toByte())
        exec("ROL", AddressingMode.Accumulator)
        exec("ROL", AddressingMode.Accumulator)
        exec("ROL", AddressingMode.Accumulator)
        assertEquals(NES.CPU.A, Int8(0xAD.toByte()))
        assertEquals(NES.CPU.status.C, true)
    }

    @Test
    fun testBranches() = withCPUTestEnvironment {
        println("Branch on equal")
        exec("LDA", AddressingMode.Immediate, 0xB4.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("INC", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("LDA", AddressingMode.Immediate, 0xB5.toByte()).shouldBeExecutedTwice()
        exec("CMP", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("BEQ", AddressingMode.Relative, 0xF8.toByte()).assertLoopExecution()
        exec("LDA", AddressingMode.Immediate, 0xAA.toByte())
        assertEquals(NES.CPU.A, Int8(0xAA.toByte()))

        println("Branch on not equal")
        exec("LDA", AddressingMode.Immediate, 0xAA.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("INC", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("LDX", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("CPX", AddressingMode.Immediate, 0xAC.toByte()).shouldBeExecutedTwice()
        exec("BNE", AddressingMode.Relative, 0xF8.toByte()).assertLoopExecution()
        exec("LDY", AddressingMode.Immediate, 0xBB.toByte())
        assertEquals(NES.CPU.A, Int8(0xAA.toByte()))
        assertEquals(NES.CPU.X, Int8(0xAC.toByte()))
        assertEquals(NES.CPU.Y, Int8(0xBB.toByte()))

        println("Branch on carry clear")
        exec("LDA", AddressingMode.Immediate, 0xFD.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("ADC", AddressingMode.Immediate, 0x01.toByte()).shouldBeExecutedTwice()
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("BCC", AddressingMode.Relative, 0xF8.toByte()).assertLoopExecution()
        assertEquals(NES.CPU.A, Int8(0x00))
        assertEquals(NES.CPU.status.C, true)

        println("Branch on carry set")
        exec("CLC", AddressingMode.Implicit)
        exec("LDA", AddressingMode.Immediate, 0xFF.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("ADC", AddressingMode.Immediate, 0x01.toByte()).shouldBeExecutedTwice()
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("BCS", AddressingMode.Relative, 0xFA.toByte()).assertLoopExecution()
        assertEquals(NES.CPU.A, Int8(0x02))
        assertEquals(NES.CPU.status.C, false)

        println("Branch on minus")
        exec("LDY", AddressingMode.Immediate, 0xAA.toByte())
        exec("LDA", AddressingMode.Immediate, 0xAC.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("DEC", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("CPY", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedTwice()
        exec("BMI", AddressingMode.Relative, 0xFA.toByte()).assertLoopExecution()
        exec("LDY", AddressingMode.Immediate, 0xFF.toByte())
        assertEquals(NES.CPU.A, Int8(0xAC.toByte()))
        assertEquals(NES.CPU.Y, Int8(0xFF.toByte()))
        assertEquals(NES.CPU.status.C, true)

        println("Branch on plus")
        exec("CLC", AddressingMode.Implicit)
        exec("LDY", AddressingMode.Immediate, 0xAC.toByte())
        exec("LDA", AddressingMode.Immediate, 0xAA.toByte())
        exec("STA", AddressingMode.ZeroPage, 0x99.toByte())
        exec("INC", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedThreeTimes()
        exec("CPY", AddressingMode.ZeroPage, 0x99.toByte()).shouldBeExecutedThreeTimes()
        exec("BPL", AddressingMode.Relative, 0xFA.toByte()).assertLoopExecution()
        exec("LDA", AddressingMode.ZeroPage, 0x99.toByte())
        assertEquals(NES.CPU.A, Int8(0xAD.toByte()))
        assertEquals(NES.CPU.Y, Int8(0xAC.toByte()))
        assertEquals(NES.CPU.status.C, false)

        println("Branch on overflow clear")
        exec("LDA", AddressingMode.Immediate, 0x7E.toByte())
        exec("ADC", AddressingMode.Immediate, 0x01.toByte()).shouldBeExecutedTwice()
        exec("BVC", AddressingMode.Relative, 0xFC.toByte()).assertLoopExecution()
        assertEquals(NES.CPU.A, Int8(0x80.toByte()))
        assertEquals(NES.CPU.status.V, true)

        println("Branch on overflow set")
        exec("CLV", AddressingMode.Implicit)
        exec("LDA", AddressingMode.Immediate, 0x7F.toByte())
        exec("ADC", AddressingMode.Immediate, 0x01.toByte()).shouldBeExecutedTwice()
        exec("BVS", AddressingMode.Relative, 0xFC.toByte()).assertLoopExecution()
        assertEquals(NES.CPU.A, Int8(0x81.toByte()))
        assertEquals(NES.CPU.status.V, false)
    }
}
