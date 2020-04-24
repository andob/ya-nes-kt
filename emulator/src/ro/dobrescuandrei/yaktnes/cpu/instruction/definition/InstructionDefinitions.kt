package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import org.reflections.Reflections
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode

object InstructionDefinitions
{
    //REFERENCE: http://www.6502.org/tutorials/6502opcodes.html
    private val groupDefinitions by lazy {
        Reflections(this::class.java.`package`.name)
            .getSubTypesOf(InstructionGroupDefinitionFactory::class.java)
            .map { it.getField("INSTANCE").get(null) as InstructionGroupDefinitionFactory<Any> }
            .map { factory -> factory.newInstance() }
    }

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

    operator fun get(name : String, addresingMode : AddressingMode) : InstructionDefinition<Any>?
    {
        for (groupDefinition in groupDefinitions)
            if (groupDefinition.name==name)
                for (definition in groupDefinition.definitions)
                    if (definition.addressingMode==addresingMode)
                        return definition
        return null
    }
}
