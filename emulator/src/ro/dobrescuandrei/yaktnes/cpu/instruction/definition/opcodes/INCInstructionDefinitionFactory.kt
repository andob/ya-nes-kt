package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.inc

object INCInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "INC",
            argumentType = Pointer::class.java,
            execution = ::inc,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xE6.toByte(),
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0xF6.toByte(),
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0xEE.toByte(),
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0xFE.toByte(),
                    addressingMode = AddressingMode.AbsoluteX
                )
            )
        )
    }
}
