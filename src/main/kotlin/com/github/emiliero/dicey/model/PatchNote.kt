package com.github.emiliero.dicey.model

class PatchNote(
    val version: String,
    val date: String,
    val new: ArrayList<String>,
    val update: ArrayList<String>,
    val removed: ArrayList<String>
)