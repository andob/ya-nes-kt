package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.ppu.color.ColorFactory
import ro.dobrescuandrei.yaktnes.ppu.color.toInt8

//REFERENCE: https://wiki.nesdev.com/w/index.php/PPU_memory_map
open class PPUBus
protected constructor() : Bus()
{
    companion object
    {
        //smart constructor
        var factory = { PPUBus() }
        operator fun invoke() = factory.invoke()
    }

    //16KB of addressable space
    override val size get() = 0x3FFF

    override fun instantiateAdapter() : BusAdapter
    {
        val adapter=BusAdapter()

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x0000..0x1FFF,
            targetDevice = NES.RunningRomFile,
            reader = { runningRomFile, pointer ->
                if (runningRomFile!=null)
                    runningRomFile.characterRom[pointer]
                else Int8.Zero
            },
            writer = { runningRomFile, pointer, value ->
                if (runningRomFile!=null)
                    runningRomFile.characterRom[pointer]=value
            }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x2000..0x2FFF,
            targetDevice = NES.PPU.nametables,
            mirrorRanges = listOf(0x3000..0x3EFF),
            reader = { nametables, pointer -> nametables[pointer] },
            writer = { nametables, pointer, value -> nametables[pointer]=value }))

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x3F00..0x3F1F,
            mirrorRanges = (0x3F20..0x3FFF step 16).map { it..it+15 },
            targetDevice = NES.PPU.colorPalettes,
            reader = { colorPalettes, pointer ->
                colorPalettes[pointer].toInt8()
            },
            writer = { colorPalettes, pointer, color ->
                colorPalettes[pointer]=ColorFactory.newColor(color.toByte())
            }))

        return adapter
    }
}
