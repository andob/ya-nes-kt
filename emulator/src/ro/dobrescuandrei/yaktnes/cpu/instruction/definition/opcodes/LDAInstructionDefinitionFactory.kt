package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.lda

object LDAInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "LDA",
            argumentType = Int8::class.java,
            execution = ::lda,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xA9.toByte(),
                    addressingMode = AddressingMode.Immediate
                ),

                InstructionDefinition(
                    id = 0xA5.toByte(),
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0xB5.toByte(),
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0xAD.toByte(),
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0xBD.toByte(),
                    addressingMode = AddressingMode.AbsoluteX
                ),

                InstructionDefinition(
                    id = 0xB9.toByte(),
                    addressingMode = AddressingMode.AbsoluteY
                ),

                InstructionDefinition(
                    id = 0xA1.toByte(),
                    addressingMode = AddressingMode.IndirectX
                ),

                InstructionDefinition(
                    id = 0xB1.toByte(),
                    addressingMode = AddressingMode.IndirectY
                )
            )
        )
    }
}
