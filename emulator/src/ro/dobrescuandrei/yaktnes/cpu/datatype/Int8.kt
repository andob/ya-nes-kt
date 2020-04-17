package ro.dobrescuandrei.yaktnes.cpu.datatype

class Int8
(
    //8-bit signed or unsigned int
    private var value : Byte
)
{
    //comparison operators: < <= == > >= !=
    operator fun compareTo(another : Int8) = value.compareTo(another.value)
    operator fun compareTo(another : Byte) = value.compareTo(another)
    override fun equals(another : Any?) = value==(another as? Int8)?.value
    override fun hashCode() = value.toInt()
    fun isNil() = value==0.toByte()

    //arithmetic operators: + - * /
    operator fun plus(another : Int8) = Int8((value+another.value).toByte())
    operator fun minus(another : Int8) = Int8((value-another.value).toByte())
    operator fun times(another : Int8) = Int8((value*another.value).toByte())
    operator fun div(another : Int8) = Int8((value.toFloat()/another.value.toFloat()).toByte())
    operator fun rem(another : Int8) = Int8((value.rem(another.value)).toByte())

    //arithmetic assignment operators: += -= *= /=
    operator fun plusAssign(another : Int8) { value=(value+another.value).toByte() }
    operator fun minusAssign(another : Int8) { value=(value-another.value).toByte() }
    operator fun timesAssign(another : Int8) { value=(value*another.value).toByte() }
    operator fun divAssign(another : Int8) { value=(value.toFloat()/another.value.toFloat()).toByte() }
    operator fun remAssign(another : Int8) { value=value.rem(another.value).toByte() }

    //increment, decrement operators: ++ --
    operator fun dec() = Int8(value.dec())
    operator fun inc() = Int8(value.inc())

    fun toUInt() = value.toUInt()
    fun toInt() = value.toInt()
    fun toUShort() = value.toUShort()
    fun toShort() = value.toShort()
    fun toUByte() = value.toUByte()
    fun toByte() = value

    override fun toString() =
        "Int8: DEC: $value, "+
        "HEX: ${String.format("%02x", value)}"
}
