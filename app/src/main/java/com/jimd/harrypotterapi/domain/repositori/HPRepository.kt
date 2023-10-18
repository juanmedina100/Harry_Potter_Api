package com.jimd.harrypotterapi.domain.repositori

import com.jimd.harrypotterapi.data.dto.character.CharacterDto
import com.jimd.harrypotterapi.data.dto.character.CharacterDtoItem
import com.jimd.harrypotterapi.domain.model.HPCharacterIndividualModel
import com.jimd.harrypotterapi.domain.model.HPCharacterModel
import retrofit2.http.Path

interface HPRepository {

    suspend fun getAllStudent(): Result<List<HPCharacterModel>>

    suspend fun getStudent(id:String): Result<HPCharacterIndividualModel>
}