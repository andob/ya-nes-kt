package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.asl
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object ASLInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "ASL",
            argumentType = Pointer::class.java,
            execution = ::asl,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x0A,
                    addressingMode = AddressingMode.Accumulator,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0x06,
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 5
                ),

                InstructionDefinition(
                    id = 0x16,
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x0E,
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x1E,
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 7
                )
            )
        )
    }
}
