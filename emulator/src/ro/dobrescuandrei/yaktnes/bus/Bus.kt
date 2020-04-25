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
        if (pointer is Pointer.ToAccumulator)
            return NES.CPU.A

        adapter.getMapping(pointer.toIndex())?.let { mapping ->
            return mapping.reader.invoke(mapping.targetDevice, pointer)
        }

        return randomizer.nextInt8()
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        if (pointer is Pointer.ToAccumulator)
        {
            NES.CPU.A=value
        }
        else
        {
            adapter.getMapping(pointer.toIndex())?.let { mapping ->
                return mapping.writer.invoke(mapping.targetDevice, pointer, value)
            }
        }
    }
}
