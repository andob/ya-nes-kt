package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.nextInt8
import kotlin.random.Random

abstract class Bus
{
    private val randomizer = Random(System.currentTimeMillis())

    private val adapter by lazy { instantiateAdapter() }

    protected abstract fun instantiateAdapter() : BusAdapter

    protected abstract val size : Int

    private fun Pointer.toIndex() = toUInt().toInt().rem(size)

    operator fun get(pointer : Pointer) : Int8
    {
        if (pointer is Pointer.ToCPUAccumulator)
            return NES.CPU.A

        val mapResult=adapter.map(pointer)
        val readResult=mapResult?.mapping?.reader?.invoke(
            /*device*/ mapResult.mapping.targetDevice,
            /*pointer*/ mapResult.mappedPointer)

        return readResult?:randomizer.nextInt8()
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        if (pointer is Pointer.ToCPUAccumulator)
        {
            NES.CPU.A=value
        }
        else
        {
            val mapResult=adapter.map(pointer)
            mapResult?.mapping?.writer?.invoke(
                /*device*/ mapResult.mapping.targetDevice,
                /*pointer*/ mapResult.mappedPointer,
                /*value*/ value)
        }
    }
}
