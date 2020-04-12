package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Decimal
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.lda

//http://www.6502.org/tutorials/6502opcodes.html
object LDAInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Decimal>
{
    override fun newInstance() : InstructionGroupDefinition<Decimal>
    {
        return InstructionGroupDefinition(
            name = "LDA",
            argumentType = Decimal::class.java,
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
