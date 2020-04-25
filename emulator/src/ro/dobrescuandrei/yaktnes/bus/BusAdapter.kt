package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer

class BusAdapter
{
    private val mappings = mutableListOf<Mapping<Any>>()

    class Mapping<DEVICE>
    (
        val addressRange : IntRange,
        val mirrorRanges : List<IntRange> = listOf(),
        val targetDevice : DEVICE,
        val writer : (DEVICE, Pointer, Int8) -> (Unit),
        val reader : (DEVICE, Pointer) -> (Int8)
    )
    {
        fun isApplicableToAddress(address : Int) : Boolean
        {
            if (address in addressRange)
                return true

            for (mirrorRange in mirrorRanges)
                if (address in mirrorRange)
                    return true

            return false
        }
    }

    fun <DEVICE> addMapping(mapping : Mapping<DEVICE>)
    {
        mappings.add(mapping as Mapping<Any>)
    }

    fun getMapping(address : Int) : Mapping<Any>?
    {
        return mappings.find { mapping ->
            mapping.isApplicableToAddress(address)
        }
    }
}
