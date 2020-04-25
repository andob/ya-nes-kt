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


        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x2000..0x2007,
            mirrorRanges = (0x2008..0x3FF8 step 8).map { it..it+7 },
            targetDevice = NES.PPU,
            reader = { PPU, pointer -> Int8.Zero },
            writer = { PPU, pointer, value -> }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x4000..0x4017,
            targetDevice = NES.APU,
            reader = { PPU, pointer -> Int8.Zero },
            writer = { PPU, pointer, value -> }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x4020..0xFFFF,
            targetDevice = NES.RunningRomFile,
            reader = { runningRomFile, pointer ->
                if (runningRomFile!=null)
                    runningRomFile.machineCode[pointer.toPointerToMachineCode()]
                else Int8.Zero
            },
            writer = { PPU, pointer, value -> }))

        return adapter
    }
}
