package com.jimd.harrypotterapi.domain.model


data class HPCharacterModel(
    val id: String,
    val name: String,
    val species: String,
    val gender: String,
    val image: String?
)
