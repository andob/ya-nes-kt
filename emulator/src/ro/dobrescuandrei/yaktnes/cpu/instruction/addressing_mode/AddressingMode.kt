package ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode

enum class AddressingMode
{
    //no argument
    Implicit,

    //argument = the accumulator
    Accumulator,

    //argument = a constant
    Immediate,

    //argument = a pointer to zero page (8-bit pointer)
    ZeroPage,
    ZeroPageX,
    ZeroPageY,

    //argument = a pointer (16-bit pointer)
    Absolute,
    AbsoluteX,
    AbsoluteY,

    //argument = a pointer to a pointer
    Indirect,
    IndirectX,
    IndirectY,

    //argument = a delta (ex: +5, -3)
    Relative
}
