package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.ldx

object LDXInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "LDX",
            argumentType = Int8::class.java,
            execution = ::ldx,
            definitions = listOf(

                InstructionDefinition(
                    id = 0xA2.toByte(),
                    addressingMode = AddressingMode.Immediate,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0xA6.toByte(),
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 3
                ),

                InstructionDefinition(
                    id = 0xB6.toByte(),
                    addressingMode = AddressingMode.ZeroPageY,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0xAE.toByte(),
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0xBE.toByte(),
                    addressingMode = AddressingMode.AbsoluteY,
                    targetExecutionTime = 4
                )
            )
        )
    }
}
