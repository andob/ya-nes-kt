package ro.dobrescuandrei.yaktnes

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses
(
    CPUInstructionsTests::class,
    CPUBusTests::class
)
class UnitTestsSuite
