package ro.dobrescuandrei.yaktnes

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import ro.dobrescuandrei.yaktnes.bus.BusAdapter
import ro.dobrescuandrei.yaktnes.cpu.datatype.*
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions
import ro.dobrescuandrei.yaktnes.ppu.CharacterRom
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
    fun testMachineCodeMappings()
    {
        val machineCode=assemble {
            asm("LDA", AddressingMode.Immediate, 0x0A.toByte())
            asm("STA", AddressingMode.ZeroPage, 0x99.toByte())
        }

        NES.execute(ROMFile(
            file = File("dummy"),
            machineCode = machineCode,
            characterRom = CharacterRom(ByteArray(size = 0))))

        assertEquals(NES.CPU_BUS[0x4020.toPointerToMachineCode()], InstructionDefinitions["LDA", AddressingMode.Immediate]!!.id.toInt8())
        assertEquals(NES.CPU_BUS[0x4021.toPointerToMachineCode()], 0x0A.toInt8())
        assertEquals(NES.CPU_BUS[0x4022.toPointerToMachineCode()], InstructionDefinitions["STA", AddressingMode.ZeroPage]!!.id.toInt8())
        assertEquals(NES.CPU_BUS[0x4023.toPointerToMachineCode()], 0x99.toInt8())
    }

    @Test
    fun testMirroring()
    {
        class DummyDevice
        {
            var lastWrotePointer : Pointer? = null
            operator fun set(pointer : Pointer, value : Int8) { lastWrotePointer=pointer }
            operator fun get(pointer : Pointer) : Int8 = Int8.Zero
        }

        val dummyDevice=DummyDevice()
        val adapter=BusAdapter()

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0xAC40..0xAC4F,
            mirrorRanges = listOf(0xEE20..0xEE2F),
            targetDevice = DummyDevice(),
            writer = { device, pointer, value -> device[pointer]=value },
            reader = { device, pointer -> device[pointer] }))

        //AC40 AC41 AC42 AC43 AC44 AC45 AC46 AC47 AC48 AC49 AC4A AC4B AC4C AC4D AC4E AC4F
        //EE20 EE21 EE22 EE23 EE24 EE25 EE26 EE27 EE28 EE29 EE2A EE2B EE2C EE2D EE2E EE2F

        val mapResult=adapter.map(0xEE27.toPointer())
        mapResult?.mapping?.writer?.invoke(
            /*device*/ dummyDevice,
            /*pointer*/ mapResult.mappedPointer,
            /*value*/ Int8.Zero)

        assertEquals(dummyDevice.lastWrotePointer!!, 0xAC47.toPointer())
    }
}
