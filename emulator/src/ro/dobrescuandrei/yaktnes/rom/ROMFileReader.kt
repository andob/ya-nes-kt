package ro.dobrescuandrei.yaktnes.rom

import ro.dobrescuandrei.yaktnes.cpu.MachineCode
import ro.dobrescuandrei.yaktnes.ppu.CharacterRom
import java.io.File

fun File.toNesRomFile() : ROMFile
{
    val file=this

    file.inputStream().buffered().use { inputStream ->
        val header=ByteArray(size = 16)
        inputStream.read(header)

        if (!(header[0]==0x4E.toByte()   //N
            &&header[1]==0x45.toByte()   //E
            &&header[2]==0x53.toByte()   //S
            &&header[3]==0x1A.toByte())) //EOF
            throw RuntimeException("Invalid file signature!!!")

        //4th byte = size of program ROM in 16KB units
        val programRomSize=header[4].toUInt().toInt()*16*1024

        //5th byte = size of character ROM in 8KB units
        val characterRomSize=header[5].toUInt().toInt()*8*1024

        //todo parse various flags: header[6..15]
        //todo implement mapper 0

        val programRomBytes=ByteArray(size = programRomSize)
        inputStream.read(programRomBytes)

        val characterRomBytes=ByteArray(size = characterRomSize)
        inputStream.read(characterRomBytes)

        return ROMFile(file = file,
            machineCode = MachineCode(programRomBytes),
            characterRom = CharacterRom(characterRomBytes))
    }
}
