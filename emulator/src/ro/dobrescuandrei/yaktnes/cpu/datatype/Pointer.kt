package ro.dobrescuandrei.yaktnes.cpu.datatype

import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.cpu.instruction.ProgramCounterDelta

open class Pointer
(
    //16-bit address (0x0000 -> 0xFFFF)
    protected var address : UShort
)
{
    companion object { val Zero = Pointer(0x00.toUShort()) }

    constructor(leastSignificantByte : Byte, mostSignificantByte : Byte) : this(
        address = (leastSignificantByte.toUByte().toUInt().shl(8)
                .or(mostSignificantByte.toUByte().toUInt())).toUShort())

    //comparison operators: < <= == > >= !=
    operator fun compareTo(another : Pointer) = address.compareTo(another.address)
    override fun equals(another : Any?) = address==(another as? Pointer)?.address
    override fun hashCode() = address.toInt()

    //arithmetic operators: + - %
    open operator fun plus(another : Int8) = Pointer((address+another.toUByte()).toUShort())
    open operator fun plus(another : Int) = Pointer((address+another.toUInt()).toUShort())
    open operator fun minus(another : Int8) = Pointer((address-another.toUByte()).toUShort())
    open operator fun rem(another : Short) = Pointer(address.rem(another.toUInt()).toUShort())
    open operator fun rem(another : Int) = Pointer(address.rem(another.toUInt()).toUShort())

    //increment / decrement operators: ++ --
    open operator fun inc() = Pointer(address.inc())
    open operator fun dec() = Pointer(address.dec())

    fun getLeastSignificantByte() = address.toInt().shr(8).toByte()
    fun getMostSignificantByte() = address.toInt().and(0xff).toByte()

    open fun toUShort() = address.toUShort()
    open fun toUInt() = address.toUInt()

    override fun toString() =
        "Pointer: DEC: $address, "+
        "HEX: ${String.format("%04x", address.toShort())}"

    //special type of Pointer - the operation should be applied on CPU accumulator
    class ToAccumulator : Pointer(0xffff.toUShort())

    //special type of Pointer - to machine code
    class ToMachineCode : Pointer
    {
        companion object { val Zero = Pointer.ToMachineCode(MachineCode.START_ADDRESS) }

        constructor(address : UShort) : super(maxOf(MachineCode.START_ADDRESS, address))

        constructor(leastSignificantByte : Byte, mostSignificantByte : Byte) : super(leastSignificantByte, mostSignificantByte)

        //increment / decrement operators: ++ --
        override operator fun inc() = Pointer.ToMachineCode(address.inc())
        override operator fun dec() = Pointer.ToMachineCode(address.dec())

        override operator fun plus(delta : ProgramCounterDelta) = Pointer.ToMachineCode((address+delta.toUShort()).toUShort())
    }
}

fun UShort.toPointer() = Pointer(this)
fun Int.toPointer() = Pointer(this.toUShort())

fun UShort.toPointerToMachineCode() = Pointer.ToMachineCode(this)
fun Int.toPointerToMachineCode() = Pointer.ToMachineCode(this.toUShort())
fun Pointer.toPointerToMachineCode() = Pointer.ToMachineCode(this.toUShort())
