package com.jimd.harrypotterapi.utils

import com.jimd.harrypotterapi.data.dto.all.HPDtoItem
import com.jimd.harrypotterapi.data.dto.character.CharacterDtoItem
import com.jimd.harrypotterapi.domain.model.HPCharacterIndividualModel
import com.jimd.harrypotterapi.domain.model.HPCharacterModel

fun HPDtoItem.toModel():HPCharacterModel{
    return HPCharacterModel(
        id = this.id, name = this.name, species = this.species,
        gender = this.gender, image = this.image
    )
}

fun CharacterDtoItem.toModel():HPCharacterIndividualModel{
    return HPCharacterIndividualModel(
        id, name, species, gender, house, dateOfBirth, yearOfBirth,
        ancestry, eyeColour, hairColour, patronus, hogwartsStudent,
        hogwartsStaff, actor, alive, image
    )
}