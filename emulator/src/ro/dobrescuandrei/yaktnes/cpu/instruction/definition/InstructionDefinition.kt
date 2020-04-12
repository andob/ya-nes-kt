package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode

data class InstructionDefinition<ARG>
(
    val id : Byte,
    val addressingMode : AddressingMode
)
{
    lateinit var groupDefinition : InstructionGroupDefinition<ARG>
}
