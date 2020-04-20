package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.ror

object RORInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "ROR",
            argumentType = Pointer::class.java,
            execution = ::ror,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x6A,
                    addressingMode = AddressingMode.Accumulator,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0x66,
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 5
                ),

                InstructionDefinition(
                    id = 0x76,
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x6E,
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x7E,
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 7
                )
            )
        )
    }
}
