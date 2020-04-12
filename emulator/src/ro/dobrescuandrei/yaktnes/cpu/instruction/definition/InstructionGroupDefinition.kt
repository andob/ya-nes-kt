package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.InstructionExecution

class InstructionGroupDefinition<ARG>
(
    val name : String,
    val argumentType : Class<ARG>,
    val execution : InstructionExecution<ARG>,
    val definitions : List<InstructionDefinition<ARG>>
)
{
    override fun toString() = name
}
