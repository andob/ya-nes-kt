package ro.dobrescuandrei.yaktnes.cpu.datatype

@OptIn(ExperimentalUnsignedTypes::class)
open class Pointer
(
    //16-bit address (0x0000 -> 0xFFFF)
    private var address : UShort
)
{
    constructor(leastSignificantByte : Byte, mostSignificantByte : Byte) : this(
        address = (leastSignificantByte.toUByte().toUInt() shl 8 or mostSignificantByte.toUByte().toUInt()).toUShort())

    //comparison operators: < <= == > >= !=
    operator fun compareTo(another : Pointer) = address.compareTo(another.address)
    override fun equals(another : Any?) = address==(another as? Pointer)?.address
    override fun hashCode() = address.toInt()

    //arithmetic operators: + - %
    operator fun plus(another : Int8) = Pointer((address+another.toUByte()).toUShort())
    operator fun plus(another : Int) = Pointer((address+another.toUInt()).toUShort())
    operator fun minus(another : Int8) = Pointer((address-another.toUByte()).toUShort())
    operator fun rem(another : Short) = Pointer(address.rem(another.toUInt()).toUShort())
    operator fun rem(another : Int) = Pointer(address.rem(another.toUInt()).toUShort())

    //increment / decrement operators: ++ --
    operator fun inc() = Pointer(address.inc())
    operator fun dec() = Pointer(address.dec())

    fun toUShort() = address.toUShort()
    fun toUInt() = address.toUInt()
    fun toInt() = toUInt().toInt()

    override fun toString() =
        "Pointer: DEC: $address, "+
        "HEX: ${String.format("%04x", address.toShort())}"

    //special type of Pointer - the operation should be applied on CPU accumulator
    class ToAccumulator : Pointer(0xffff.toUShort())
}
