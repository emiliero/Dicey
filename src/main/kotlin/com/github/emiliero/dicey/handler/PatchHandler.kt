package com.github.emiliero.dicey.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.emiliero.dicey.model.PatchNote
import com.github.emiliero.dicey.model.PatchNotes
import java.nio.file.Paths

fun getPatchNotes(): PatchNotes {
    val objectMapper = ObjectMapper()
    val filePath = Paths.get("src/main/resources/logs/patches.json").toAbsolutePath().toString()
    val fileContent = readFile(filePath)
    return objectMapper.readValue<PatchNotes>(fileContent, PatchNotes::class.java)
}

fun generatePatchNotes(patchNote: PatchNote): String {
    var message = ""

    val new = patchNote.new
    println(new.size)
    if(new.size > 0) {
        message += "Newly added commands:\n ${new.joinToString("\n")} \n\n"
    }

    val update = patchNote.update
    println(update.size)
    if(update.size > 0) {
        message += "Updated commands:\n ${update.joinToString("\n")} \n\n"
    }

    val removed = patchNote.removed
    println(removed.size)
    if(removed.size > 0) {
        message += "Removed commands:\n ${removed.joinToString("\n")} \n\n"
    }

    message += "To view the full list of commands, type `!cmds`"

    return message
}