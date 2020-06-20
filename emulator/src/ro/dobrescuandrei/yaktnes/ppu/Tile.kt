package ro.dobrescuandrei.yaktnes.ppu

import com.badlogic.gdx.graphics.Color

typealias ShortMatrix = Array<ShortArray>
typealias ColorMatrix = Array<Array<Color>>

class Tile
private constructor
(
    private val pixelPattern : ShortMatrix
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

                return@pixelPattern ShortArray(size = 8, init = { x ->
                    mostSignificantBitPlane[x].shl(1)
                        .or(leastSignificantBitPlane[x]).toShort()
                })
            })

            return Tile(pixelPattern)
        }
    }

    //todo test this
    fun toColorMatrix() : ColorMatrix
    {
        //todo remove this
        fun Short.toColor() = when(this)
        {
            0.toShort() -> Color.RED
            1.toShort() -> Color.GREEN
            2.toShort() -> Color.BLUE
            else -> Color.CLEAR
        }

        return Array(size = 8, init = { y ->
            Array(size = 8, init = { x ->
                pixelPattern[y][x].toColor()
            })
        })
    }
}
