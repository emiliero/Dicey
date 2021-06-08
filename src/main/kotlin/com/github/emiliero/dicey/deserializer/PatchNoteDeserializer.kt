package com.github.emiliero.dicey.deserializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.github.emiliero.dicey.model.PatchNote
import com.github.emiliero.dicey.model.PatchNotes

class PatchNoteDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<PatchNotes?>(vc) {
    override fun deserialize(jsonParser: JsonParser, context: DeserializationContext): PatchNotes {
        val patchNotes = ArrayList<PatchNote>()
        val node = jsonParser.codec.readTree<JsonNode>(jsonParser)
        node.forEach { mainNode ->
            val version = mainNode.get("version").toString()
            val date = mainNode.get("date").toString()
            val new = ArrayList<String>()
            mainNode.get("new").forEach { newNode ->
                new.add(newNode.toString())
            }

            val update = ArrayList<String>()
            mainNode.get("update").forEach { updateNode ->
                update.add(updateNode.toString())
            }

            val removed = ArrayList<String>()
            mainNode.get("removed").forEach { removedNode ->
                removed.add(removedNode.toString())
            }

            patchNotes.add(PatchNote(version, date, new, update, removed))
        }

        return PatchNotes(patchNotes)
    }
}