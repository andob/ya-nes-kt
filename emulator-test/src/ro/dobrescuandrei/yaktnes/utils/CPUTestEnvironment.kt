package ro.dobrescuandrei.yaktnes.utils

import org.junit.Assert.*
import ro.dobrescuandrei.yaktnes.NES
import ro.dobrescuandrei.yaktnes.bus.BusAdapter
import ro.dobrescuandrei.yaktnes.bus.CPUBus
import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.cpu.datatype.toInt8
import ro.dobrescuandrei.yaktnes.cpu.instruction.addressing_mode.AddressingMode
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinition
import ro.dobrescuandrei.yaktnes.cpu.instruction.definition.InstructionDefinitions

fun withCPUTestEnvironment(block : CPUTestEnvironment.() -> (Unit))
{
    val previousCPUBusFactory=CPUBus.factory

    CPUBus.factory={ DummyCPUBus() }

    NES.reset()

    block(CPUTestEnvironment())

    CPUBus.factory=previousCPUBusFactory
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

fun assemble(sourceCodeProvider : CPUTestEnvironment.AssemblerContext.() -> Unit) : MachineCode
{
    val assemblerContext=CPUTestEnvironment.AssemblerContext()
    sourceCodeProvider.invoke(assemblerContext)
    return MachineCode(assemblerContext.machineCodeBytes)
}

private class DummyCPUBus : CPUBus()
{
    private val dummyRAM = ByteArray(size = size)

    override fun instantiateAdapter() : BusAdapter
    {
        val adapter=BusAdapter()

        adapter.addMapping(BusAdapter.Mapping(
            addressRange = 0x0000..size,
            targetDevice = dummyRAM,
            reader = { RAM, pointer -> RAM[pointer.toUInt().toInt()].toInt8() },
            writer = { RAM, pointer, value -> RAM[pointer.toUInt().toInt()]=value.toByte() }))

        return adapter
    }
}
