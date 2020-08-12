package ro.dobrescuandrei.yaktnes.ppu.nametable

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

//nametables = 4KB
class Nametables
{
    private val nametables = arrayOf(
        Nametable(startAddress = 0x2000.toPointer()),
        Nametable(startAddress = 0x2400.toPointer()),
        Nametable(startAddress = 0x2800.toPointer()),
        Nametable(startAddress = 0x2C00.toPointer()))

    operator fun get(pointer : Pointer) : Int8
    {
        for (nametable in nametables)
            if (nametable.canAcceptPointer(pointer))
                return nametable[pointer]
        return Int8.Zero
    }

    operator fun set(pointer : Pointer, value : Int8)
    {
        for (nametable in nametables)
            if (nametable.canAcceptPointer(pointer))
                nametable[pointer]=value
    }
}
