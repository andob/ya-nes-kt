package ro.dobrescuandrei.yaktnes.cpu.datatype

class Decimal
(
    //8-bit unsigned int
    private var value : Byte
)
{
    //comparison operators: < <= == > >= !=
    operator fun compareTo(another : Decimal) = value.compareTo(another.value)
    operator fun compareTo(another : Byte) = value.compareTo(another)
    override fun equals(another : Any?) = value==(another as? Decimal)?.value
    override fun hashCode() = value.toInt()
    fun isNil() = value==0.toByte()

    //arithmetic operators: + - * /
    operator fun plus(another : Decimal) = Decimal((value+another.value).toByte())
    operator fun minus(another : Decimal) = Decimal((value-another.value).toByte())
    operator fun times(another : Decimal) = Decimal((value*another.value).toByte())
    operator fun div(another : Decimal) = Decimal((value.toFloat()/another.value.toFloat()).toByte())
    operator fun rem(another : Decimal) = Decimal((value.rem(another.value)).toByte())

    //arithmetic assignment operators: += -= *= /=
    operator fun plusAssign(another : Decimal) { value=(value+another.value).toByte() }
    operator fun minusAssign(another : Decimal) { value=(value-another.value).toByte() }
    operator fun timesAssign(another : Decimal) { value=(value*another.value).toByte() }
    operator fun divAssign(another : Decimal) { value=(value.toFloat()/another.value.toFloat()).toByte() }
    operator fun remAssign(another : Decimal) { value=value.rem(another.value).toByte() }

    //increment, decrement operators: ++ --
    operator fun dec() = Decimal(value.dec())
    operator fun inc() = Decimal(value.inc())

    override fun toString() = "Decimal($value)"
    fun toInt() = value.toInt()
    fun toByte() = value
}
