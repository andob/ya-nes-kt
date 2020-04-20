package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.execution.InstructionExecution

class InstructionGroupDefinition<ARG>
(
    //instruction ASM name
    val name : String,

    //instruction argument type
    val argumentType : Class<ARG>,

    //instruction execution lambda
    var execution : InstructionExecution<ARG>,

    val definitions : List<InstructionDefinition<ARG>>
)
{
    override fun toString() = name
}
