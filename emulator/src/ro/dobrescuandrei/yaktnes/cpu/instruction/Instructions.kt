package ro.dobrescuandrei.yaktnes.cpu.instruction

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import kotlin.experimental.inv

//REFERENCE: http://www.6502.org/tutorials/6502opcodes.html
//CROSS-COMPILER: https://skilldrick.github.io/easy6502/

//ADC = ADd with Carry
fun adc(value : Int8)
{
    var sum = NES.CPU.A.toInt()+value.toUByte().toInt()

    if (NES.CPU.status.C)
        sum++

    NES.CPU.status.N = sum.shr(7)==1
    NES.CPU.status.Z = sum==0
    NES.CPU.status.C = sum.shr(8)>0

    //https://stackoverflow.com/a/29224684
    NES.CPU.status.V =
        NES.CPU.A.toInt().xor(value.toInt()).inv()
            .and(NES.CPU.A.toInt().xor(sum))
            .and(0x80)>0

    NES.CPU.A = Int8(sum.and(0xff).toByte())
}

//AND = bitwise AND with accumulator
fun and(value : Int8)
{
    val result = NES.CPU.A.toInt().and(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//ASL = Arithmetic Shift Left
fun asl(pointer : Pointer)
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
fun bcc()
{
    TODO()
}

//BCS = Branch on Carry Set
fun bcs()
{
    TODO()
}

//BEQ = Branch on EQual
fun beq()
{
    TODO()
}

//BIT = test BITs
fun bit(address : Pointer)
{
    TODO()
}

//BMI = Branch on MInus
fun bmi()
{
    TODO()
}

//BNE = Branch on Not Equal
fun bne()
{
    TODO()
}

//BPL = Branch on PLus
fun bpl()
{
    TODO()
}

//BRK = BReaK
fun brk()
{
    TODO()
}

//BVC = Branch on oVerflow Clear
fun bvc()
{
    TODO()
}

//BVS = Branch on oVerflow Set
fun bvs()
{
    TODO()
}

//CLC = CLear Carry
fun clc()
{
    NES.CPU.status.C = false
}

//CLD = CLear Decimal
//NES doesn't support Decimal Mode
fun cld() {}

//CLI = CLear Interrupt
fun cli()
{
    TODO()
}

//CLV = CLear oVerflow
fun clv()
{
    NES.CPU.status.V = false
}

//CMP = CoMPare accumulator
fun cmp(value : Int8)
{
    TODO()
}

//CPX = ComPare X register
fun cpx(value : Int8)
{
    TODO()
}

//CPY = ComPare Y register
fun cpy(value : Int8)
{
    TODO()
}

//DEC = DECrement memory
fun dec(address : Pointer)
{
    NES.CPU_BUS[address]--

    NES.CPU.status.N = NES.CPU_BUS[address]<0
    NES.CPU.status.Z = NES.CPU_BUS[address].isNil()
}

//DEX = DEcrement X
fun dex() = ldx(NES.CPU.X-Int8(1))

//DEY = DEcrement Y
fun dey() = ldy(NES.CPU.Y-Int8(1))

//EOR = bitwise XOR
fun eor(value : Int8)
{
    val result = NES.CPU.A.toInt().xor(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//INC = INCrement memory
fun inc(address : Pointer)
{
    NES.CPU_BUS[address]++

    NES.CPU.status.N = NES.CPU_BUS[address]<0
    NES.CPU.status.Z = NES.CPU_BUS[address].isNil()
}

//INX = INcrement X
fun inx() = ldx(NES.CPU.X+Int8(1))

//INY = INcrement Y
fun iny() = ldy(NES.CPU.Y+Int8(1))

//JMP = JuMP
fun jmp(address : Pointer)
{
    TODO()
}

//JSR = Jump to SubRoutine
fun jsr(address : Pointer)
{
    TODO()
}

//LDA = LoaD Accumulator
fun lda(value : Int8)
{
    NES.CPU.A = value

    NES.CPU.status.N = value<0
    NES.CPU.status.Z = value.isNil()
}

//LDX = LoaD register X
fun ldx(value : Int8)
{
    NES.CPU.X = value

    NES.CPU.status.N = value<0
    NES.CPU.status.N = value.isNil()
}

//LDY = LoaD register Y
fun ldy(value : Int8)
{
    NES.CPU.Y = value

    NES.CPU.status.N = value<0
    NES.CPU.status.Z = value.isNil()
}

//LSR = Logical Shift Right
fun lsr(pointer : Pointer)
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
fun nop() {}

//ODA = bitwise OR with Accumulator
fun ora(value : Int8)
{
    val result = NES.CPU.A.toInt().or(value.toInt())

    NES.CPU.status.N = result<0
    NES.CPU.status.Z = result==0

    NES.CPU.A = Int8(result.toByte())
}

//PHA = PusH Accumulator
fun pha()
{
    TODO()
}

//PHP = PusH Processor status
fun php()
{
    TODO()
}

//PLA = PuLl Accumulator
fun pla()
{
    TODO()
}

//PLP = PuLl Processor status
fun plp()
{
    TODO()
}

//ROL = ROtate Left
fun rol(pointer : Pointer)
{
    //shift left
    asl(pointer)

    //rotate shifted byte
    if (NES.CPU.status.C)
        NES.CPU_BUS[pointer] = Int8(NES.CPU_BUS[pointer].toInt().or(0x01).toByte())
}

//ROR = ROtate Right
fun ror(pointer : Pointer)
{
    //shift right
    lsr(pointer)

    //rotate shifted byte
    if (NES.CPU.status.C)
        NES.CPU_BUS[pointer] = Int8(NES.CPU_BUS[pointer].toInt().or(0x80).toByte())
}

//RTI = ReTurn from Interrupt
fun rti()
{
    TODO()
}

//RTS = ReTurn from Subroutine
fun rts()
{
    TODO()
}

//SBC = SuBstract with Carry
fun sbc(value : Int8)
{
    //Just bitwise invert the number: x-y = x+(-y)
    adc(Int8(value.toByte().inv()))
}

//SEC = SEt Carry
fun sec()
{
    NES.CPU.status.C = true
}

//SED = SEt Decimal
//NES doesn't support Decimal Mode
fun sed() {}

//SEI = SEt Interrupt
fun sei()
{
    TODO()
}

//STA = STore Accumulator
fun sta(address : Pointer)
{
    NES.CPU_BUS[address] = NES.CPU.A
}

//STX = STore register X
fun stx(address : Pointer)
{
    NES.CPU_BUS[address] = NES.CPU.X
}

//STY = STore register Y
fun sty(address : Pointer)
{
    NES.CPU_BUS[address] = NES.CPU.Y
}

//TAX = Transfer A to X
fun tax() = ldx(NES.CPU.A)

//TAY = Transfer A to Y
fun tay() = ldy(NES.CPU.A)

//TSX = Transfer Stack pointer to X
fun tsx()
{
    TODO()
}

//TXA = Transfer X to A
fun txa() = lda(NES.CPU.X)

//TXS = Transfer X to Stack pointer
fun txs()
{
    TODO()
}

//TYA = Transfer Y to A
fun tya() = lda(NES.CPU.Y)
