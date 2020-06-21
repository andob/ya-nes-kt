package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

//todo implement this
class CPUAddressRegister : PPURegister()
{
    val targetAddress get() = Pointer(leastSignificantByte, mostSignificantByte)

    private var leastSignificantByte = 0x00.toByte()
    private var mostSignificantByte = 0x00.toByte()
    private var lastWrittenByte = 0x00.toByte()
    private var isIterationEven = false

    override val addressOnCpuBus get() = 0x2006.toPointer()

    override fun update(value : Byte)
    {
        if (isIterationEven)
            mostSignificantByte=value
        else leastSignificantByte=value

        lastWrittenByte=value

        isIterationEven=!isIterationEven
    }

    override fun toByte() : Byte
    {
        return lastWrittenByte
    }
}
