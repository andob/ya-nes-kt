package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8

//todo unit test this
//REFERENCE: https://wiki.nesdev.com/w/index.php/PPU_memory_map
open class PPUBus protected constructor() : Bus()
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

        //todo implement this
//        $2000-$23FF 	$0400 	Nametable 0
//        $2400-$27FF 	$0400 	Nametable 1
//        $2800-$2BFF 	$0400 	Nametable 2
//        $2C00-$2FFF 	$0400 	Nametable 3
//        $3000-$3EFF 	$0F00 	Mirrors of $2000-$2EFF
//        $3F00-$3F1F 	$0020 	Palette RAM indexes
//        $3F20-$3FFF 	$00E0 	Mirrors of $3F00-$3F1F

        return adapter
    }
}
