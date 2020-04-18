package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import kotlin.random.Random

class CPUBus
{
    //64KB of RAM -> 0x0000 -> 0xFFFF
    private val values = Array(size = 0xFFFF, init = { Int8.Zero })

    init
    {
        val randomizer=Random(System.currentTimeMillis())
        for (index in values.indices)
            values[index]=randomizer.nextBytes(size = 1).first().toInt8()
    }

    private fun Pointer.toIndex() = toUInt().toInt().rem(0xFFFF)

    operator fun get(pointer : Pointer) : Int8
    {
        if (pointer is Pointer.ToAccumulator)
            return NES.CPU.A

        return values[pointer.toIndex()]
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        if (pointer is Pointer.ToAccumulator)
            NES.CPU.A=value
        else values[pointer.toIndex()]=value
    }
}
