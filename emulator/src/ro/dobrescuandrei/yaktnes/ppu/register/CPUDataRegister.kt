package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

//todo implement this
class CPUDataRegister : PPURegister()
{
    var targetValue = Int8.Zero

    override val addressOnCpuBus get() = 0x2007.toPointer()

    override fun update(value : Byte)
    {
        targetValue=value.toInt8()
    }

    override fun toByte() : Byte
    {
        return targetValue.toByte()
    }
}
