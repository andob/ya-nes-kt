package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import kotlin.random.Random

class CPUBus
{
    //64KB of RAM -> 0x0000 -> 0xFFFF
    private val values = Array<Int8>(size = 0xFFFF, init = { Int8(0x00) })

    init
    {
        val randomizer=Random(System.currentTimeMillis())
        for (index in values.indices)
            values[index]=Int8(randomizer.nextBytes(size = 1).first())
    }

    operator fun get(address : Pointer) : Int8
    {
        return values[address.toInt()]
    }

    operator fun set(address : Pointer, value : Int8)
    {
        values[address.toInt()]=value
    }
}
