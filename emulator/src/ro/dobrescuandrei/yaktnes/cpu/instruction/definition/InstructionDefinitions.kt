package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes.LDAInstructionDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes.STAInstructionDefinitionFactory

object InstructionDefinitions
{
    private val groupDefinitions = listOf(
        LDAInstructionDefinitionFactory.newInstance(),
        STAInstructionDefinitionFactory.newInstance()
    ) as List<InstructionGroupDefinition<Any>>

    init
    {
        for (groupDefinition in groupDefinitions)
           for (definition in groupDefinition.definitions)
               definition.groupDefinition=groupDefinition
    }

    operator fun get(id : Byte) : InstructionDefinition<Any>?
    {
        for (groupDefinition in groupDefinitions)
            for (definition in groupDefinition.definitions)
                if (definition.id==id)
                    return definition
        return null
    }
}
