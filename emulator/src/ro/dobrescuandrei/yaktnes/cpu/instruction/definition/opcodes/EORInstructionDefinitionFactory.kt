package ro.dobrescuandrei.yaktnes.cpu.instruction.definition.opcodes

import ro.dobrescuandrei.yaktnes.cpu.datatype.Int8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionGroupDefinitionFactory
import ro.dobrescuandrei.yaktnes.cpu.instruction.eor

object EORInstructionDefinitionFactory : InstructionGroupDefinitionFactory<Int8>
{
    override fun newInstance() : InstructionGroupDefinition<Int8>
    {
        return InstructionGroupDefinition(
            name = "EOR",
            argumentType = Int8::class.java,
            execution = ::eor,
            definitions = listOf(

                InstructionDefinition(
                    id = 0x49,
                    addressingMode = AddressingMode.Immediate,
                    targetExecutionTime = 2
                ),

                InstructionDefinition(
                    id = 0x45,
                    addressingMode = AddressingMode.ZeroPage,
                    targetExecutionTime = 3
                ),

                InstructionDefinition(
                    id = 0x55,
                    addressingMode = AddressingMode.ZeroPageX,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x4D,
                    addressingMode = AddressingMode.Absolute,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x5D,
                    addressingMode = AddressingMode.AbsoluteX,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x59,
                    addressingMode = AddressingMode.AbsoluteY,
                    targetExecutionTime = 4
                ),

                InstructionDefinition(
                    id = 0x41,
                    addressingMode = AddressingMode.IndirectX,
                    targetExecutionTime = 6
                ),

                InstructionDefinition(
                    id = 0x51,
                    addressingMode = AddressingMode.IndirectY,
                    targetExecutionTime = 5
                )
            )
        )
    }
}
