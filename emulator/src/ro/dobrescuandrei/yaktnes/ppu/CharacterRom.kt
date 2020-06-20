package ro.dobrescuandrei.yaktnes.ppu

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8

class CharacterRom
(
    //todo make private
    val rom : ByteArray
)
{
    private fun Pointer.toIndex() = toUInt().toInt()

    operator fun get(pointer : Pointer) : Int8
    {
        return rom[pointer.toIndex()].toInt8()
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        rom[pointer.toIndex()]=value.toByte()
    }

    fun getTile(pointer : Pointer) : Tile
    {
        val tileBytes=(0x0..0xF).map { rom[(pointer+it).toIndex()] }
        return Tile.decodeFromCharacterRom(tileBytes)
    }
}
