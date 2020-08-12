package ro.dobrescuandrei.yaktnes

import ro.dobrescuandrei.yaktnes.apu.APU
import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.bus.CPUBus
import ro.dobrescuandrei.yaktnes.bus.PPUBus
import ro.dobrescuandrei.yaktnes.ppu.PPU
import ro.dobrescuandrei.yaktnes.ram.RAM
import ro.dobrescuandrei.yaktnes.rom.ROMFile
import ro.dobrescuandrei.yaktnes.utils.Run

object NES
{
    class Components
    {
        val CPU : CPU = CPU()
        val CPU_BUS : CPUBus = CPUBus()
        val RAM : RAM = RAM()
        val PPU : PPU = PPU()
        val PPU_BUS : PPUBus = PPUBus()
        val APU : APU = APU()
        var RunningRomFile : ROMFile? = null
    }

    val CPU get() = components.CPU
    val CPU_BUS get() = components.CPU_BUS
    val RAM get() = components.RAM
    val PPU get() = components.PPU
    val PPU_BUS get() = components.PPU_BUS
    val APU get() = components.APU
    val RunningRomFile get() = components.RunningRomFile

    private var components = Components()
    fun reset() { components = Components() }

    fun execute(romFile : ROMFile)
    {
        components.RunningRomFile=romFile

        Run.thread { CPU.execute(machineCode = romFile.machineCode) }

        Run.thread { PPU.render() }
    }
}
