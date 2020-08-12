package ro.dobrescuandrei.yaktnes.bus

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

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

    fun <DEVICE> addMapping(mapping : Mapping<DEVICE>)
    {
        mappings.add(mapping as Mapping<Any>)
    }

    class PointerMappingResult
    (
        val mapping : Mapping<Any>,
        val originalPointer : Pointer,
        val mappedPointer : Pointer
    )

    fun map(pointer : Pointer) : PointerMappingResult?
    {
        val pointerAddress=pointer.toUInt().toInt()

        for (mapping in mappings)
        {
            if (pointerAddress in mapping.addressRange)
                return PointerMappingResult(mapping = mapping, originalPointer = pointer, mappedPointer = pointer)

            for (mirrorRange in mapping.mirrorRanges)
            {
                if (pointerAddress in mirrorRange)
                {
                    val mappedPointer=(mapping.addressRange.first+pointerAddress-mirrorRange.first).toPointer()
                    return PointerMappingResult(mapping = mapping, originalPointer = pointer, mappedPointer = mappedPointer)
                }
            }
        }

        return null
    }
}
