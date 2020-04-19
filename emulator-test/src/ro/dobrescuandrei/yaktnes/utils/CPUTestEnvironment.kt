package ro.dobrescuandrei.yaktnes.utils

import org.junit.Assert.*
import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions

fun withCPUTestEnvironment(block : CPUTestEnvironment.() -> (Unit))
{
    NES.reset()

    block(CPUTestEnvironment())
}

class CPUTestEnvironment
{
    private var machineCodeBytes = byteArrayOf()

    private val loopedInstructionTesters = mutableListOf<LoopedInstructionTester>()

    class LoopedInstructionTester
    (
        val instructionDefinition : InstructionDefinition<Any>,
        val targetNumberOfTimesInstructionShouldBeExecuted : Int
    )
    {
        var numberOfTimesInstructionWasExecuted = 1

        init
        {
            val originalExecution=instructionDefinition.groupDefinition.execution

            instructionDefinition.groupDefinition.execution={ argument ->
                originalExecution.invoke(argument)
                numberOfTimesInstructionWasExecuted++
            }
        }

        fun assert()
        {
            try
            {
                assertEquals(
                    targetNumberOfTimesInstructionShouldBeExecuted,
                    numberOfTimesInstructionWasExecuted)
            }
            catch (ex : AssertionError)
            {
                System.err.println(instructionDefinition.groupDefinition.name)
                throw ex
            }
        }
    }

    inner class InstructionExecutionTestEnvironment
    (
        val instructionDefinition : InstructionDefinition<Any>
    )
    {
        fun shouldBeExecutedTwice()
        {
            loopedInstructionTesters.add(
                LoopedInstructionTester(instructionDefinition,
                    targetNumberOfTimesInstructionShouldBeExecuted = 2))
        }

        fun shouldBeExecutedThreeTimes()
        {
            loopedInstructionTesters.add(
                LoopedInstructionTester(instructionDefinition,
                    targetNumberOfTimesInstructionShouldBeExecuted = 3))
        }

        fun assertLoopExecution()
        {
            loopedInstructionTesters.forEach { it.assert() }
            loopedInstructionTesters.clear()
        }
    }

    fun exec(instructionName : String, addressingMode : AddressingMode, vararg arguments : Byte) : InstructionExecutionTestEnvironment
    {
        val definition=InstructionDefinitions[instructionName, addressingMode]!!
        machineCodeBytes+=byteArrayOf(definition.id)
        machineCodeBytes+=arguments.reversed()
        val machineCode=MachineCode(machineCodeBytes)
        NES.CPU.execute(machineCode)

        //on branch, jump etc, there are instructions to run in the loop
        while (machineCode.hasNextByte())
            NES.CPU.execute(machineCode)

        return InstructionExecutionTestEnvironment(definition)
    }

    fun assemble(sourceCodeProvider : AssemblerContext.() -> (Unit)) : MachineCode
    {
        val assemblerContext=AssemblerContext()
        sourceCodeProvider.invoke(assemblerContext)
        return MachineCode(assemblerContext.machineCodeBytes)
    }

    class AssemblerContext
    {
        var machineCodeBytes = byteArrayOf()

        fun asm(instructionName : String, addressingMode : AddressingMode, vararg arguments : Byte)
        {
            val definition=InstructionDefinitions[instructionName, addressingMode]!!
            machineCodeBytes+=byteArrayOf(definition.id)
            machineCodeBytes+=arguments.reversed()
        }
    }
}
