package ro.dobrescuandrei.yaktnes.cpu.datatype

class Pointer
(
    //16-bit address (0x0000 -> 0xFFFF)
    private var address : Short
)
{
    constructor(leastSignificantByte : Byte, mostSignificantByte : Byte) : this(
        address = (leastSignificantByte.toInt() shl 8 or mostSignificantByte.toInt()).toShort())

    //comparison operators: < <= == > >= !=
    operator fun compareTo(another : Pointer) = address.compareTo(another.address)
    override fun equals(another : Any?) = address==(another as? Pointer)?.address
    override fun hashCode() = address.toInt()

    //arithmetic operators: + - %
    operator fun plus(another : Decimal) = Pointer((address+another.toInt()).toShort())
    operator fun plus(another : Int) = Pointer((address+another).toShort())
    operator fun minus(another : Decimal) = Pointer((address-another.toInt()).toShort())
    operator fun rem(another : Short) = Pointer(address.rem(another).toShort())

    //increment / decrement operators: ++ --
    operator fun inc() = Pointer((address+1).toShort())
    operator fun dec() = Pointer((address-1).toShort())

    override fun toString() = "Pointer($address)"
    fun toInt() = address.toInt()
}
