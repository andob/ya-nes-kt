package ro.dobrescuandrei.yaktnes.cpu.instruction.definition

import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes.*

object InstructionDefinitions
{
    private val groupDefinitions = listOf(
        //REFERENCE: http://www.6502.org/tutorials/6502opcodes.html
        ADCInstructionDefinitionFactory.newInstance(),
        ANDInstructionDefinitionFactory.newInstance(),
        ASLInstructionDefinitionFactory.newInstance(),
        BCCInstructionDefinitionFactory.newInstance(),
        BCSInstructionDefinitionFactory.newInstance(),
        BEQInstructionDefinitionFactory.newInstance(),
        BITInstructionDefinitionFactory.newInstance(),
        BMIInstructionDefinitionFactory.newInstance(),
        BNEInstructionDefinitionFactory.newInstance(),
        BPLInstructionDefinitionFactory.newInstance(),
        BRKInstructionDefinitionFactory.newInstance(),
        BVCInstructionDefinitionFactory.newInstance(),
        BVSInstructionDefinitionFactory.newInstance(),
        CLCInstructionDefinitionFactory.newInstance(),
        CLDInstructionDefinitionFactory.newInstance(),
        CLIInstructionDefinitionFactory.newInstance(),
        CLVInstructionDefinitionFactory.newInstance(),
        CMPInstructionDefinitionFactory.newInstance(),
        CPXInstructionDefinitionFactory.newInstance(),
        CPYInstructionDefinitionFactory.newInstance(),
        DECInstructionDefinitionFactory.newInstance(),
        DEXInstructionDefinitionFactory.newInstance(),
        DEYInstructionDefinitionFactory.newInstance(),
        EORInstructionDefinitionFactory.newInstance(),
        INCInstructionDefinitionFactory.newInstance(),
        INXInstructionDefinitionFactory.newInstance(),
        INYInstructionDefinitionFactory.newInstance(),
        JMPInstructionDefinitionFactory.newInstance(),
        JSRInstructionDefinitionFactory.newInstance(),
        LDAInstructionDefinitionFactory.newInstance(),
        LDXInstructionDefinitionFactory.newInstance(),
        LDYInstructionDefinitionFactory.newInstance(),
        LSRInstructionDefinitionFactory.newInstance(),
        NOPInstructionDefinitionFactory.newInstance(),
        ORAInstructionDefinitionFactory.newInstance(),
        PHAInstructionDefinitionFactory.newInstance(),
        PHPInstructionDefinitionFactory.newInstance(),
        PLAInstructionDefinitionFactory.newInstance(),
        PLPInstructionDefinitionFactory.newInstance(),
        ROLInstructionDefinitionFactory.newInstance(),
        RORInstructionDefinitionFactory.newInstance(),
        RTIInstructionDefinitionFactory.newInstance(),
        RTSInstructionDefinitionFactory.newInstance(),
        SBCInstructionDefinitionFactory.newInstance(),
        SECInstructionDefinitionFactory.newInstance(),
        SEDInstructionDefinitionFactory.newInstance(),
        SEIInstructionDefinitionFactory.newInstance(),
        STAInstructionDefinitionFactory.newInstance(),
        STXInstructionDefinitionFactory.newInstance(),
        STYInstructionDefinitionFactory.newInstance(),
        TAXInstructionDefinitionFactory.newInstance(),
        TAYInstructionDefinitionFactory.newInstance(),
        TSXInstructionDefinitionFactory.newInstance(),
        TXAInstructionDefinitionFactory.newInstance(),
        TXSInstructionDefinitionFactory.newInstance(),
        TYAInstructionDefinitionFactory.newInstance()
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

    operator fun get(name : String, addresingMode : AddressingMode) : InstructionDefinition<Any>?
    {
        for (groupDefinition in groupDefinitions)
            if (groupDefinition.name==name)
                for (definition in groupDefinition.definitions)
                    if (definition.addressingMode==addresingMode)
                        return definition
        return null
    }

    fun toList() = groupDefinitions
}
