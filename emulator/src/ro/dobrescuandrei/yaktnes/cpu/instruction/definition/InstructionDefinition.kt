package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode

data class InstructionDefinition<ARG>
(
    //instruction ID as specified into machine code
    val id : Byte,

    //instruction argument addressing mode
    val addressingMode : AddressingMode,

    //minimum number of 6502 CPU cycles consumed (in theory) by the instruction
    //values took from http://www.6502.org/tutorials/6502opcodes.html
    val targetExecutionTime : Int
)
{
    lateinit var groupDefinition : InstructionGroupDefinition<ARG>
}
