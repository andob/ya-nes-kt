package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.utils.Vector

//todo implement this
class ScrollRegister : PPURegister()
{
    val scrollVector get() = Vector(x = scrollXOffset, y = scrollYOffset)

    private var scrollXOffset = Int8.Zero
    private var scrollYOffset = Int8.Zero
    private var lastWrittenValue = Int8.Zero
    private var isIterationEven = false

    override val addressOnCpuBus get() = 0x2005.toPointer()

    override fun update(value : Byte)
    {
        if (isIterationEven)
            scrollYOffset=value.toInt8()
        else scrollXOffset=value.toInt8()

        lastWrittenValue=value.toInt8()

        isIterationEven=!isIterationEven
    }

    override fun toByte() : Byte
    {
        return lastWrittenValue.toByte()
    }
}
