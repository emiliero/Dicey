package com.github.emiliero.dicey.model

import com.fasterxml.jackson.annotation.JsonProperty

class PatchNote(
    @JsonProperty("version")
    val version: String,
    @JsonProperty("date")
    val date: String,
    @JsonProperty("new")
    val new: ArrayList<String>,
    @JsonProperty("update")
    val update: ArrayList<String>,
    @JsonProperty("removed")
    val removed: ArrayList<String>
)