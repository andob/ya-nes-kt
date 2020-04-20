package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.dec
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory

object DECInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "DEC",
            argumentType = Pointer::class.java,
            execution = ::dec,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xC6.toByte(),
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 5
                ),

                InstructionDefinition(
                    id = 0xD6.toByte(),
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0xCE.toByte(),
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0xDE.toByte(),
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 7
                )
            )
        )
    }
}
