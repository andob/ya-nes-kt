package ro.dobrescuandrei.yaktnes.cpu.instruction

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import kotlin.experimental.inv

//REFERENCE: http://www.6502.org/tutorials/6502opcodes.html
//REFERENCE: http://www.obelisk.me.uk/6502/reference.html
//CROSS-COMPILER: https://skilldrick.github.io/easy6502/

//CODING CONVENTIONS:
//1. All functions should use only local variables
//   and variables from NES.CPU.*, NES.CPU_BUS.*
//2. Do not declare static variables in this file

typealias ProgramCounterDelta = Int8

//ADC = ADd with Carry
internal fun adc(value : Int8)
{
    var sum = NES.CPU.A.toUByte().toInt()+value.toUByte().toInt()

    if (NES.CPU.status.C)
        sum++

    NES.CPU.status.C = sum.shr(8)>0
    NES.CPU.status.N = sum.shr(7)==1
    NES.CPU.status.Z = sum==0

    //https://stackoverflow.com/a/29224684
    NES.CPU.status.V =
        NES.CPU.A.toInt().xor(value.toInt()).inv()
            .and(NES.CPU.A.toInt().xor(sum))
            .and(0x80)>0

    NES.CPU.A = Int8(sum.and(0xff).toByte())
}

//AND = bitwise AND with accumulator
internal fun and(value : Int8)
{
    val result = NES.CPU.A.toInt().and(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//ASL = Arithmetic Shift Left
internal fun asl(pointer : Pointer)
{
    val inputValue = NES.CPU_BUS[pointer]
    val shiftedValue = inputValue.toInt().and(0xff).shl(1)
    val outputValue = Int8(shiftedValue.and(0xff).toByte())
    NES.CPU_BUS[pointer] = outputValue

    NES.CPU.status.N = outputValue<0
    NES.CPU.status.Z = outputValue.isNil()
    NES.CPU.status.C = shiftedValue.shr(8)>0
}

//BCC = Branch on Carry Clear
internal fun bcc(delta : ProgramCounterDelta)
{
    if (!NES.CPU.status.C)
        NES.CPU.programCounter+=delta.toInt()
}

//BCS = Branch on Carry Set
internal fun bcs(delta : ProgramCounterDelta)
{
    if (NES.CPU.status.C)
        NES.CPU.programCounter+=delta.toInt()
}

//BEQ = Branch on EQual
internal fun beq(delta : ProgramCounterDelta)
{
    if (NES.CPU.status.Z)
        NES.CPU.programCounter+=delta.toInt()
}

//BIT = test BITs
internal fun bit(pointer : Pointer)
{
    TODO()
}

//BMI = Branch on MInus
internal fun bmi(delta : ProgramCounterDelta)
{
    if (NES.CPU.status.N)
        NES.CPU.programCounter+=delta.toInt()
}

//BNE = Branch on Not Equal
internal fun bne(delta : ProgramCounterDelta)
{
    if (!NES.CPU.status.Z)
        NES.CPU.programCounter+=delta.toInt()
}

//BPL = Branch on PLus
internal fun bpl(delta : ProgramCounterDelta)
{
    if (!NES.CPU.status.N)
        NES.CPU.programCounter+=delta.toInt()
}

//BRK = BReaK
internal fun brk()
{
    TODO()
}

//BVC = Branch on oVerflow Clear
internal fun bvc(delta : ProgramCounterDelta)
{
    if (!NES.CPU.status.V)
        NES.CPU.programCounter+=delta.toInt()
}

//BVS = Branch on oVerflow Set
internal fun bvs(delta : ProgramCounterDelta)
{
    if (NES.CPU.status.V)
        NES.CPU.programCounter+=delta.toInt()
}

//CLC = CLear Carry
internal fun clc()
{
    NES.CPU.status.C = false
}

//CLD = CLear Decimal
//NES doesn't support Decimal Mode
internal fun cld() {}

//CLI = CLear Interrupt
internal fun cli()
{
    TODO()
}

//CLV = CLear oVerflow
internal fun clv()
{
    NES.CPU.status.V = false
}

//CMP = CoMPare accumulator
internal fun cmp(value : Int8)
{
    NES.CPU.status.C = NES.CPU.A>=value
    NES.CPU.status.Z = NES.CPU.A==value
    NES.CPU.status.N = NES.CPU.A<value
}

//CPX = ComPare X register
internal fun cpx(value : Int8)
{
    NES.CPU.status.C = NES.CPU.X>=value
    NES.CPU.status.Z = NES.CPU.X==value
    NES.CPU.status.N = NES.CPU.X<value
}

//CPY = ComPare Y register
internal fun cpy(value : Int8)
{
    NES.CPU.status.C = NES.CPU.Y>=value
    NES.CPU.status.Z = NES.CPU.Y==value
    NES.CPU.status.N = NES.CPU.Y<value
}

//DEC = DECrement memory
internal fun dec(pointer : Pointer)
{
    NES.CPU_BUS[pointer]--

    NES.CPU.status.N = NES.CPU_BUS[pointer]<0
    NES.CPU.status.Z = NES.CPU_BUS[pointer].isNil()
}

//DEX = DEcrement X
internal fun dex() = ldx(NES.CPU.X-Int8(1))

//DEY = DEcrement Y
internal fun dey() = ldy(NES.CPU.Y-Int8(1))

//EOR = bitwise XOR
internal fun eor(value : Int8)
{
    val result = NES.CPU.A.toInt().xor(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//INC = INCrement memory
internal fun inc(pointer : Pointer)
{
    NES.CPU_BUS[pointer]++

    NES.CPU.status.N = NES.CPU_BUS[pointer]<0
    NES.CPU.status.Z = NES.CPU_BUS[pointer].isNil()
}

//INX = INcrement X
internal fun inx() = ldx(NES.CPU.X+Int8(1))

//INY = INcrement Y
internal fun iny() = ldy(NES.CPU.Y+Int8(1))

//JMP = JuMP
internal fun jmp(pointer : Pointer)
{
    TODO()
}

//JSR = Jump to SubRoutine
internal fun jsr(pointer : Pointer)
{
    TODO()
}

//LDA = LoaD Accumulator
internal fun lda(value : Int8)
{
    NES.CPU.A = value

    NES.CPU.status.N = value<0
    NES.CPU.status.Z = value.isNil()
}

//LDX = LoaD register X
internal fun ldx(value : Int8)
{
    NES.CPU.X = value

    NES.CPU.status.N = value<0
    NES.CPU.status.N = value.isNil()
}

//LDY = LoaD register Y
internal fun ldy(value : Int8)
{
    NES.CPU.Y = value

    NES.CPU.status.N = value<0
    NES.CPU.status.Z = value.isNil()
}

//LSR = Logical Shift Right
internal fun lsr(pointer : Pointer)
{
    val inputValue = NES.CPU_BUS[pointer]
    val shiftedValue = inputValue.toInt().and(0xff).shr(1)
    val outputValue = Int8(shiftedValue.and(0xff).toByte())
    NES.CPU_BUS[pointer] = outputValue

    NES.CPU.status.N = outputValue<0
    NES.CPU.status.Z = outputValue.isNil()
    NES.CPU.status.C = inputValue.toInt().and(1)>0
}

//NOP = No OPeration
internal fun nop() {}

//ODA = bitwise OR with Accumulator
internal fun ora(value : Int8)
{
    val result = NES.CPU.A.toInt().or(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//PHA = PusH Accumulator
internal fun pha()
{
    TODO()
}

//PHP = PusH Processor status
internal fun php()
{
    TODO()
}

//PLA = PuLl Accumulator
internal fun pla()
{
    TODO()
}

//PLP = PuLl Processor status
internal fun plp()
{
    TODO()
}

//ROL = ROtate Left
internal fun rol(pointer : Pointer)
{
    //shift left
    asl(pointer)

    //rotate shifted byte
    if (NES.CPU.status.C)
        NES.CPU_BUS[pointer] = Int8(NES.CPU_BUS[pointer].toInt().or(0x01).toByte())
}

//ROR = ROtate Right
internal fun ror(pointer : Pointer)
{
    //shift right
    lsr(pointer)

    //rotate shifted byte
    if (NES.CPU.status.C)
        NES.CPU_BUS[pointer] = Int8(NES.CPU_BUS[pointer].toInt().or(0x80).toByte())
}

//RTI = ReTurn from Interrupt
internal fun rti()
{
    TODO()
}

//RTS = ReTurn from Subroutine
internal fun rts()
{
    TODO()
}

//SBC = SuBstract with Carry
internal fun sbc(value : Int8)
{
    //Just bitwise invert the number: x-y = x+(-y)
    adc(Int8(value.toByte().inv()))
}

//SEC = SEt Carry
internal fun sec()
{
    NES.CPU.status.C = true
}

//SED = SEt Decimal
//NES doesn't support Decimal Mode
internal fun sed() {}

//SEI = SEt Interrupt
internal fun sei()
{
    TODO()
}

//STA = STore Accumulator
internal fun sta(pointer : Pointer)
{
    NES.CPU_BUS[pointer] = NES.CPU.A
}

//STX = STore register X
internal fun stx(pointer : Pointer)
{
    NES.CPU_BUS[pointer] = NES.CPU.X
}

//STY = STore register Y
internal fun sty(pointer : Pointer)
{
    NES.CPU_BUS[pointer] = NES.CPU.Y
}

//TAX = Transfer A to X
internal fun tax() = ldx(NES.CPU.A)

//TAY = Transfer A to Y
internal fun tay() = ldy(NES.CPU.A)

//TSX = Transfer Stack pointer to X
internal fun tsx()
{
    TODO()
}

//TXA = Transfer X to A
internal fun txa() = lda(NES.CPU.X)

//TXS = Transfer X to Stack pointer
internal fun txs()
{
    TODO()
}

//TYA = Transfer Y to A
internal fun tya() = lda(NES.CPU.Y)
