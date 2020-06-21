package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.utils.Size
import ro.dobrescuandrei.yaktnes.utils.toInt
import java.lang.RuntimeException

//todo unit test this
class ControlRegister : PPURegister()
{
    //todo implement this
    var shouldGenerateNMIAtTheStartOfVBlankInterval = false

    //todo implement this
    var shouldReadBackdropFromExtPins = false
    var shouldWriteBackdropFromExtPins = false

    //todo implement this
    var spriteSize : Size = Size(width = 8.toInt8(), height = 8.toInt8())

    //todo implement this
    var backgroundPatternTableAddress : Pointer = Pointer.Zero

    //todo implement this
    //todo this is used for 8x8 sprites only. on 8x16 sprites, it is unused
    var spritePatternTableAddress : Pointer = Pointer.Zero

    //todo implement this
    //todo VRAM address increment per CPU read/write of data register
    //todo (0: add 1, going across; 1: add 32, going down)
    var vramAddressIncrementAfterDataRegisterIsSet : Int = 0

    //todo implement this
    var baseNametableAddress : Pointer = Pointer.Zero

    override val addressOnCpuBus get() = 0x2000.toPointer()

    override fun update(byte : Byte)
    {
        shouldGenerateNMIAtTheStartOfVBlankInterval=byte.toInt().shr(7).and(1)>0

        shouldWriteBackdropFromExtPins=byte.toInt().shr(6).and(1)>0
        shouldReadBackdropFromExtPins=!shouldWriteBackdropFromExtPins

        if (byte.toInt().shr(5).and(1)>0)
            spriteSize=Size(width = 8.toInt8(), height = 16.toInt8())
        else spriteSize=Size(width = 8.toInt8(), height = 8.toInt8())

        if (byte.toInt().shr(4).and(1)>0)
            backgroundPatternTableAddress=0x1000.toPointer()
        else backgroundPatternTableAddress=Pointer.Zero

        if (byte.toInt().shr(3).and(1)>0)
            spritePatternTableAddress=0x1000.toPointer()
        else spritePatternTableAddress=Pointer.Zero

        if (byte.toInt().shr(2).and(1)>0)
            vramAddressIncrementAfterDataRegisterIsSet=32
        else vramAddressIncrementAfterDataRegisterIsSet=1

        baseNametableAddress=when (byte.toInt().and(2))
        {
            0 -> 0x2000.toPointer()
            1 -> 0x2400.toPointer()
            2 -> 0x2800.toPointer()
            3 -> 0x2C00.toPointer()
            else -> throw RuntimeException("Invalid nametable address!")
        }
    }

    override fun toByte() =
        0x00.or(shouldGenerateNMIAtTheStartOfVBlankInterval.toInt()).shl(1)
            .or(shouldWriteBackdropFromExtPins.toInt()).shl(1)
            .or(if (spriteSize==Size(width = 8.toInt8(), height = 16.toInt8())) 1 else 0).shl(1)
            .or(if (backgroundPatternTableAddress==Pointer.Zero) 0 else 1).shl(1)
            .or(if (spritePatternTableAddress==Pointer.Zero) 0 else 1).shl(1)
            .or(if (vramAddressIncrementAfterDataRegisterIsSet==1) 0 else 1).shl(2)
            .or(when (baseNametableAddress)
            {
                0x2000.toPointer() -> 0
                0x2400.toPointer() -> 1
                0x2800.toPointer() -> 2
                0x2C00.toPointer() -> 3
                else -> throw RuntimeException("Invalid nametable address!")
            }).toByte()
}
