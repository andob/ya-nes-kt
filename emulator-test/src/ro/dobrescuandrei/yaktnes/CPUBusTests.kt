package ro.dobrescuandrei.yaktnes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointerToMachineCode
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.rom.ROMFile
import ro.dobrescuandrei.yaktnes.utils.assemble
import java.io.File

class CPUBusTests
{
    @Before
    fun setup() = NES.reset()

    @Test
    fun testRAMMappings()
    {
        NES.CPU_BUS[0x03FF.toPointer()]=41.toInt8()
        assertEquals(NES.CPU_BUS[0x03FF.toPointer()], 41.toInt8())
        assertEquals(NES.RAM[0x03FF.toPointer()], 41.toInt8())

        NES.CPU_BUS[0x0BFF.toPointer()]=21.toInt8()
        assertEquals(NES.CPU_BUS[0x0BFF.toPointer()], 21.toInt8())
        assertEquals(NES.RAM[0x03FF.toPointer()], 21.toInt8())

        NES.CPU_BUS[0x13FF.toPointer()]=7.toInt8()
        assertEquals(NES.CPU_BUS[0x13FF.toPointer()], 7.toInt8())
        assertEquals(NES.RAM[0x03FF.toPointer()], 7.toInt8())

        NES.CPU_BUS[0x1BFF.toPointer()]=60.toInt8()
        assertEquals(NES.CPU_BUS[0x1BFF.toPointer()], 60.toInt8())
        assertEquals(NES.RAM[0x03FF.toPointer()], 60.toInt8())
    }

    @Test
    fun testPPUMappings()
    {
        TODO()
    }

    @Test
    fun testAPUMappings()
    {
        TODO()
    }

    @Test
    fun testMachineCodeMappings()
    {
        val machineCode=assemble {
            asm("LDA", AddressingMode.Immediate, 0x0A.toByte())
            asm("STA", AddressingMode.ZeroPage, 0x99.toByte())
        }

        NES.execute(ROMFile(
            file = File("dummy"),
            machineCode = machineCode,
            characterRom = ByteArray(size = 0)))

        assertEquals(NES.CPU_BUS[0x4020.toPointerToMachineCode()], InstructionDefinitions["LDA", AddressingMode.Immediate]!!.id.toInt8())
        assertEquals(NES.CPU_BUS[0x4021.toPointerToMachineCode()], 0x0A.toInt8())
        assertEquals(NES.CPU_BUS[0x4022.toPointerToMachineCode()], InstructionDefinitions["STA", AddressingMode.ZeroPage]!!.id.toInt8())
        assertEquals(NES.CPU_BUS[0x4023.toPointerToMachineCode()], 0x99.toInt8())
    }
}