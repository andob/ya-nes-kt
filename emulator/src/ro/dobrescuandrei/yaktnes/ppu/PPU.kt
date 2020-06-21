package ro.dobrescuandrei.yaktnes.ppu

import org.reflections.Reflections
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.ppu.color.ColorPalettes
import ro.dobrescuandrei.yaktnes.ppu.register.*

//Picture Processing Unit
class PPU
{
    val colorPalettes = ColorPalettes()

    val registers by lazy {
        Reflections(this::class.java.`package`.name)
            .getSubTypesOf(PPURegister::class.java)
            .map { registerClass -> registerClass.newInstance()!! }
    }

    inline fun <reified T> getRegister() =
        registers.find { it::class.java==T::class.java }!! as T

    operator fun set(pointer : Pointer, value : Int8)
    {
        for (register in registers)
            if (register.addressOnCpuBus==pointer)
                register.setValue(value)

        getRegister<StatusRegister>().lastValueWroteIntoARegister=value
    }

    operator fun get(pointer : Pointer) : Int8
    {
        for (register in registers)
            if (register.addressOnCpuBus==pointer)
                return register.getValue()
        return Int8.Zero
    }
}

