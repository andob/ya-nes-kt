package ro.dobrescuandrei.yaktnes.cpu.instruction

import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.CPU
import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer

//REFERENCE: http://www.6502.org/tutorials/6502opcodes.html
//CROSS-COMPILER: https://skilldrick.github.io/easy6502/

//ADC = ADd with Carry
fun adc(value : Int8)
{
    TODO()
}

//AND = bitwise AND with accumulator
fun and(value : Int8)
{
    TODO()
}

//ASL = Arithmetic Shift Left
fun asl(address : Pointer)
{
    if (address is Pointer.ToAccumulator)
    {
        TODO()
    }
    else
    {
        TODO()
    }
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
    TODO()
}

//CLD = CLear Decimal
fun cld()
{
    TODO()
}

//CLI = CLear Interrupt
fun cli()
{
    TODO()
}

//CLV = CLear oVerflow
fun clv()
{
    TODO()
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
    TODO()
}

//DEX = DEcrement X
fun dex() = ldx(NES.CPU.X-Int8(1))

//DEY = DEcrement Y
fun dey() = ldy(NES.CPU.Y-Int8(1))

//EOR = bitwise XOR
fun eor(value : Int8)
{
    TODO()
}

//INC = INCrement memory
fun inc(address : Pointer)
{
    TODO()
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

    NES.CPU.status = CPU.Status(
        N = value<0,
        Z = value.isNil())
}

//LDX = LoaD register X
fun ldx(value : Int8)
{
    NES.CPU.X = value

    NES.CPU.status = CPU.Status(
        N = value<0,
        Z = value.isNil())
}

//LDY = LoaD register Y
fun ldy(value : Int8)
{
    NES.CPU.Y = value

    NES.CPU.status = CPU.Status(
        N = value<0,
        Z = value.isNil())
}

//LSR = Logical Shift Right
fun lsr(address : Pointer)
{
    if (address is Pointer.ToAccumulator)
    {
        TODO()
    }
    else
    {
        TODO()
    }
}

//NOP = No OPeration
fun nop() {}

//ODA = bitwise OR with Accumulator
fun ora(value : Int8)
{
    TODO()
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
fun rol(address : Pointer)
{
    if (address is Pointer.ToAccumulator)
    {
        TODO()
    }
    else
    {
        TODO()
    }
}

//ROR = ROtate Right
fun ror(address : Pointer)
{
    if (address is Pointer.ToAccumulator)
    {
        TODO()
    }
    else
    {
        TODO()
    }
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
    TODO()
}

//SEC = SEt Carry
fun sec()
{
    TODO()
}

//SED = SEt Decimal
fun sed()
{
    TODO()
}

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
