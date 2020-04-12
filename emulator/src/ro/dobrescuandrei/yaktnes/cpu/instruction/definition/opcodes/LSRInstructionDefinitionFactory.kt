package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.lsr

object LSRInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "LSR",
            argumentType = Pointer::class.java,
            execution = ::lsr,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x4A,
                    addressingMode = AddressingMode.Accumulator
                ),

                InstructionDefinition(
                    id = 0x46,
                    addressingMode = AddressingMode.ZeroPage
                ),

                InstructionDefinition(
                    id = 0x56,
                    addressingMode = AddressingMode.ZeroPageX
                ),

                InstructionDefinition(
                    id = 0x4E,
                    addressingMode = AddressingMode.Absolute
                ),

                InstructionDefinition(
                    id = 0x5E,
                    addressingMode = AddressingMode.AbsoluteX
                )
            )
        )
    }
}
