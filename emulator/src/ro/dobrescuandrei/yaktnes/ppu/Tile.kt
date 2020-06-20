package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.graphics.Color
import ro.dobrescuandrei.yaktnes.ppu.color.ColorPalette

typealias ByteMatrix = Array<ByteArray>
typealias ColorMatrix = Array<Array<Color>>

class Tile
private constructor
(
    private val pixelPattern : ByteMatrix
)
{
    companion object
    {
        //todo test this
        @JvmStatic
        fun decodeFromCharacterRom(bytes : List<Byte>) : Tile
        {
            if (bytes.size!=16)
                throw RuntimeException("Invalid bytecode! A tile must be 16 bytes!")

            fun Byte.toIntArrayWithBits() = IntArray(size = 8, init = { i ->
                this.toInt().shr(i).and(0x01)
            })

            val pixelPattern=Array(size = 8, init = pixelPattern@ { y ->
                val leastSignificantBitPlane=bytes[y].toIntArrayWithBits()
                val mostSignificantBitPlane=bytes[y+8].toIntArrayWithBits()

                return@pixelPattern ByteArray(size = 8, init = { x ->
                    mostSignificantBitPlane[x].shl(1)
                        .or(leastSignificantBitPlane[x]).toByte()
                })
            })

            return Tile(pixelPattern)
        }
    }

    //todo test this
    fun toColorMatrix(colorPalette : ColorPalette) : ColorMatrix
    {
        fun Byte.toColor() =
            colorPalette.colors.getOrNull(index = this.toInt()-1)
                ?:colorPalette.universalBackgroundColor

        return Array(size = 8, init = { y ->
            Array(size = 8, init = { x ->
                pixelPattern[y][x].toColor()
            })
        })
    }
}
