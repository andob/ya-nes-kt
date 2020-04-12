package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

interface InstructionGroupDefinitionFactory<ARG>
{
    fun newInstance() : InstructionGroupDefinition<ARG>
}
