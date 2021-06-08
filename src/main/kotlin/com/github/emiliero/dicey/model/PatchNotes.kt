package com.github.emiliero.dicey.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.github.emiliero.dicey.deserializer.ItemDeserializer

@JsonDeserialize(using = ItemDeserializer::class)
class PatchNotes constructor(private val patchNotes: ArrayList<PatchNote>) {

    fun getAllPatchNotes(): ArrayList<PatchNote> {
        return patchNotes
    }

    fun getLatestPatchNote(): PatchNote {
        return patchNotes[patchNotes.size - 1]
    }

    fun getPatchNote(version: String): PatchNote? {
        patchNotes.forEach {
            if(it.version === version) {
                return it
            }
        }

        return null
    }
}