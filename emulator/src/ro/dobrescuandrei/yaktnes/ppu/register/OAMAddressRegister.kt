package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer

//todo implement this
class OAMAddressRegister : PPURegister()
{
    var targetAddress : Pointer = Pointer.Zero

    override val addressOnCpuBus get() = 0x2003.toPointer()

    override fun update(value : Byte)
    {
        targetAddress=value.toInt().toPointer()
    }

    override fun toByte() : Byte
    {
        return targetAddress.toUInt().toByte()
    }
}
