package ro.dobrescuandrei.yaktnes.ppu.color

import com.badlogic.gdx.graphics.Color
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8

object ColorFactory
{
    @JvmStatic
    fun newColor(code : Byte = 0xFF.toByte()) =
        byteToColorMap[code]?:Color.CLEAR
}

fun Color.toInt8() = (colorToByteMap[this]?:0xFF.toByte()).toInt8()

private val colorToByteMap by lazy {
    byteToColorMap.map { (byte, color) -> color to byte }.toMap()
}

private val byteToColorMap = mapOf(
    0x00.toByte() to Color.valueOf("656565FF")!!,
    0x01.toByte() to Color.valueOf("002D69FF")!!,
    0x02.toByte() to Color.valueOf("131F7FFF")!!,
    0x03.toByte() to Color.valueOf("3C137CFF")!!,
    0x04.toByte() to Color.valueOf("600B62FF")!!,
    0x05.toByte() to Color.valueOf("730A37FF")!!,
    0x06.toByte() to Color.valueOf("710F07FF")!!,
    0x07.toByte() to Color.valueOf("5A1A00FF")!!,
    0x08.toByte() to Color.valueOf("342800FF")!!,
    0x09.toByte() to Color.valueOf("0B3400FF")!!,
    0x0A.toByte() to Color.valueOf("003C00FF")!!,
    0x0B.toByte() to Color.valueOf("003D10FF")!!,
    0x0C.toByte() to Color.valueOf("003840FF")!!,
    0x0D.toByte() to Color.valueOf("000000FF")!!,
    0x0E.toByte() to Color.valueOf("000000FF")!!,
    0x0F.toByte() to Color.valueOf("000000FF")!!,

    0x10.toByte() to Color.valueOf("AEAEAEFF")!!,
    0x11.toByte() to Color.valueOf("0F63B3FF")!!,
    0x12.toByte() to Color.valueOf("4051D0FF")!!,
    0x13.toByte() to Color.valueOf("7841CCFF")!!,
    0x14.toByte() to Color.valueOf("A736A9FF")!!,
    0x15.toByte() to Color.valueOf("C03470FF")!!,
    0x16.toByte() to Color.valueOf("BD3C30FF")!!,
    0x17.toByte() to Color.valueOf("9F4A00FF")!!,
    0x18.toByte() to Color.valueOf("6D5C00FF")!!,
    0x19.toByte() to Color.valueOf("366D00FF")!!,
    0x1A.toByte() to Color.valueOf("077704FF")!!,
    0x1B.toByte() to Color.valueOf("00793DFF")!!,
    0x1C.toByte() to Color.valueOf("00727DFF")!!,
    0x1D.toByte() to Color.valueOf("000000FF")!!,
    0x1E.toByte() to Color.valueOf("000000FF")!!,
    0x1F.toByte() to Color.valueOf("000000FF")!!,

    0x20.toByte() to Color.valueOf("FEFEFFFF")!!,
    0x21.toByte() to Color.valueOf("5DB3FFFF")!!,
    0x22.toByte() to Color.valueOf("8FA1FFFF")!!,
    0x23.toByte() to Color.valueOf("C890FFFF")!!,
    0x24.toByte() to Color.valueOf("F785FAFF")!!,
    0x25.toByte() to Color.valueOf("FF83C0FF")!!,
    0x26.toByte() to Color.valueOf("FF8B7FFF")!!,
    0x27.toByte() to Color.valueOf("EF9A49FF")!!,
    0x28.toByte() to Color.valueOf("BDAC2CFF")!!,
    0x29.toByte() to Color.valueOf("85BC2FFF")!!,
    0x2A.toByte() to Color.valueOf("55C753FF")!!,
    0x2B.toByte() to Color.valueOf("3CC98CFF")!!,
    0x2C.toByte() to Color.valueOf("3EC2CDFF")!!,
    0x2D.toByte() to Color.valueOf("4E4E4EFF")!!,
    0x2E.toByte() to Color.valueOf("000000FF")!!,
    0x2F.toByte() to Color.valueOf("000000FF")!!,

    0x30.toByte() to Color.valueOf("FFFFFFFF")!!,
    0x31.toByte() to Color.valueOf("BCDFFFFF")!!,
    0x32.toByte() to Color.valueOf("D1D8FFFF")!!,
    0x33.toByte() to Color.valueOf("E8D1FFFF")!!,
    0x34.toByte() to Color.valueOf("FBCDFDFF")!!,
    0x35.toByte() to Color.valueOf("FFCCE5FF")!!,
    0x36.toByte() to Color.valueOf("FFCFCAFF")!!,
    0x37.toByte() to Color.valueOf("F8D5B4FF")!!,
    0x38.toByte() to Color.valueOf("E4DCA8FF")!!,
    0x39.toByte() to Color.valueOf("CCE3A9FF")!!,
    0x3A.toByte() to Color.valueOf("B9E8B8FF")!!,
    0x3B.toByte() to Color.valueOf("AEE8D0FF")!!,
    0x3C.toByte() to Color.valueOf("AFE5EAFF")!!,
    0x3D.toByte() to Color.valueOf("B6B6B6FF")!!,
    0x3E.toByte() to Color.valueOf("000000FF")!!,
    0x3F.toByte() to Color.valueOf("000000FF")!!)
