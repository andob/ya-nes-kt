package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Pointer
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.stx

object STXInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Pointer>
{
    override fun newInstance() : InstructionGroupDefinition<Pointer>
    {
        return InstructionGroupDefinition(
            name = "STX",
            argumentType = Pointer::class.java,
            execution = ::stx,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x86.toByte(),
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 3
                ),

                InstructionDefinition(
                    id = 0x96.toByte(),
                    addressingMode = AddressingMode.ZeroPageY,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x8E.toByte(),
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 4
                )
            )
        )
    }
}
