package ro.dobrescuandrei.yaktnes.rom

import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.ppu.CharacterRom
import java.io.File

class ROMFile
(
    //the file delegate
    val file : File,

    //CPU machine code (program ROM)
    val machineCode : MachineCode,

    //character ROM contains graphics
    val characterRom : CharacterRom
)
