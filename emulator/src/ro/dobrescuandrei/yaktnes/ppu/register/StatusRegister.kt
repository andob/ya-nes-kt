package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.utils.toInt

class StatusRegister : PPURegister()
{
    var wasVerticalBlankPeriodStarted = false

    //todo implement this
    var wasSpriteZeroHit = false

    //todo implement this
    var hasSpriteOverflowed = false

    var lastValueWroteIntoARegister : Int8 = Int8.Zero

    override val addressOnCpuBus get() = 0x2002.toPointer()

    override fun update(byte : Byte)
    {
        wasVerticalBlankPeriodStarted=byte.toInt().shr(7).and(1)>0
        wasSpriteZeroHit=byte.toInt().shr(6).and(1)>0
        hasSpriteOverflowed=byte.toInt().shr(5).and(1)>0
    }

    override fun toByte() =
        0x00.or(wasVerticalBlankPeriodStarted.toInt()).shl(1)
            .or(wasSpriteZeroHit.toInt()).shl(1)
            .or(hasSpriteOverflowed.toInt()).shl(5)
            .or(lastValueWroteIntoARegister.toInt().and(0x1F)).toByte()
}
