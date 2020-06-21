package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointerToMachineCode

//REFERENCE: http://wiki.nesdev.com/w/index.php/CPU_memory_map
open class CPUBus protected constructor() : Bus()
{
    companion object
    {
        //smart constructor
        var factory = { CPUBus() }
        operator fun invoke() = factory.invoke()
    }

    //64KB of addressable space
    override val size get() = 0xFFFF

    override fun instantiateAdapter() : BusAdapter
    {
        val adapter=BusAdapter()

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x0000..0x07FF,
            mirrorRanges = listOf(0x0800..0x0FFF, 0x1000..0x17FF, 0x1800..0x1FFF),
            targetDevice = NES.RAM,
            reader = { RAM, pointer -> RAM[pointer] },
            writer = { RAM, pointer, value -> RAM[pointer]=value }))

        //todo test this mapping
        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x2000..0x2007,
            mirrorRanges = (0x2008..0x3FF8 step 8).map { it..it+7 },
            targetDevice = NES.PPU,
            reader = { PPU, pointer -> PPU[pointer] },
            writer = { PPU, pointer, value -> PPU[pointer]=value }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x4000..0x4013,
            targetDevice = NES.APU,
            reader = { APU, pointer -> Int8.Zero },
            writer = { APU, pointer, value -> }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x4014..0x4014,
            targetDevice = NES.PPU,
            reader = { PPU, pointer -> PPU[pointer] },
            writer = { PPU, pointer, value -> PPU[pointer]=value }))

        adapter.addMapping(BusAdapter.Mapping(
                addressRange = 0x4015..0x4017,
                targetDevice = NES.APU,
                reader = { APU, pointer -> Int8.Zero },
                writer = { APU, pointer, value -> }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x4020..0xFFFF,
            targetDevice = NES.RunningRomFile,
            reader = { runningRomFile, pointer ->
                if (runningRomFile!=null)
                    runningRomFile.machineCode[pointer.toPointerToMachineCode()]
                else Int8.Zero
            },
            writer = { runningRomFile, pointer, value ->
                if (runningRomFile!=null)
                    runningRomFile.machineCode[pointer.toPointerToMachineCode()]=value
            }))

        return adapter
    }
}
