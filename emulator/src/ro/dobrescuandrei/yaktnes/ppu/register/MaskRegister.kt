package ro.dobrescuandrei.yaktnes.ppu.register

import ro.dobrescuandrei.yaktnes.cpu.datatype.toPointer
import ro.dobrescuandrei.yaktnes.utils.toInt

class MaskRegister : PPURegister()
{
    //todo implement this
    var shouldEmphasizeRedColor = false
    var shouldEmphasizeGreenColor = false
    var shouldEmphasizeBlueColor = false

    //todo implement this
    var shouldShowSprites = false

    //todo implement this
    var shouldShowBackground = false

    //todo implement this
    var shouldShowSpritesInLeftmostEightPixelsOfScreen = false

    //todo implement this
    var shouldShowBackgroundInLeftmostEightPixelsOfScreen = false

    //todo implement this
    var shouldShowOnlyGrayscaleColors = false

    override val addressOnCpuBus get() = 0x2001.toPointer()

    override fun update(byte : Byte)
    {
        shouldEmphasizeBlueColor=byte.toInt().shr(7).and(1)>0
        shouldEmphasizeGreenColor=byte.toInt().shr(6).and(1)>0
        shouldEmphasizeRedColor=byte.toInt().shr(5).and(1)>0
        shouldShowSprites=byte.toInt().shr(4).and(1)>0
        shouldShowBackground=byte.toInt().shr(3).and(1)>0
        shouldShowSpritesInLeftmostEightPixelsOfScreen=byte.toInt().shr(2).and(1)>0
        shouldShowBackgroundInLeftmostEightPixelsOfScreen=byte.toInt().shr(1).and(1)>0
        shouldShowOnlyGrayscaleColors=byte.toInt().and(1)>0
    }

    override fun toByte() =
        0x00.or(shouldEmphasizeBlueColor.toInt()).shl(1)
            .or(shouldEmphasizeGreenColor.toInt()).shl(1)
            .or(shouldEmphasizeRedColor.toInt()).shl(1)
            .or(shouldShowSprites.toInt()).shl(1)
            .or(shouldShowBackground.toInt()).shl(1)
            .or(shouldShowSpritesInLeftmostEightPixelsOfScreen.toInt()).shl(1)
            .or(shouldShowBackgroundInLeftmostEightPixelsOfScreen.toInt()).shl(1)
            .or(shouldShowOnlyGrayscaleColors.toInt()).toByte()
}
