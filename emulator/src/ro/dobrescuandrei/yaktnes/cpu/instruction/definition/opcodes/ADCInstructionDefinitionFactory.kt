package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.adc
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object ADCInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "ADC",
            argumentType = Int8::class.java,
            execution = ::adc,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x69,
                    addressingMode = AddressingMode.Immediate
                ),

                InstructionDefinition(
                    id = 0x65,
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0x75,
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0x6D,
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x7D,
                    addressingMode = AddressingMode.AbsoluteX
                ),

                InstructionDefinition(
                    id = 0x79,
                    addressingMode = AddressingMode.AbsoluteY
                ),

                InstructionDefinition(
                    id = 0x61,
                    addressingMode = AddressingMode.IndirectX
                ),

                InstructionDefinition(
                    id = 0x71,
                    addressingMode = AddressingMode.IndirectY
                )
            )
        )
    }
}
