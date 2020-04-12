package ro.dobrescuandrei.yaktnes.cpu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import kotlin.random.Random

object CpuBus
{
    //64KB of RAM -> 0x0000 -> 0xFFFF
    private val values = Array<Decimal>(size = 0xFFFF, init = { index ->
        Decimal(randomizer.nextBytes(size = 1).first())
    })

    private val randomizer = Random(System.currentTimeMillis())

    operator fun get(address : Pointer) : Decimal
    {
        return values[address.toInt()]
    }

    operator fun set(address : Pointer, value : Decimal)
    {
        values[address.toInt()]=value
    }
}
