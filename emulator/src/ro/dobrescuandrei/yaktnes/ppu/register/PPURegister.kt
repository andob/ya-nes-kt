package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8

abstract class PPURegister
{
    abstract val addressOnCpuBus : Pointer

    protected abstract fun update(value : Byte)
    protected abstract fun toByte() : Byte

    fun setValue(value : Int8) = update(value.toByte())
    fun getValue() = toByte().toInt8()
}
