package com.jimd.harrypotterapi.domain.model

import com.google.gson.annotations.SerializedName
import com.jimd.harrypotterapi.data.dto.character.Wand

data class HPCharacterIndividualModel(
    val id: String,
    val name: String,
    val species: String?,
    val gender: String?,
    val house: String?,
    val dateOfBirth: String?,
    val yearOfBirth: Int?,
    val ancestry: String?,
    val eyeColour: String?,
    val hairColour: String?,
    val patronus: String?,
    val hogwartsStudent: Boolean?,
    val hogwartsStaff: Boolean?,
    val actor: String?,
    val alive: Boolean?,
    val image: String?
)
