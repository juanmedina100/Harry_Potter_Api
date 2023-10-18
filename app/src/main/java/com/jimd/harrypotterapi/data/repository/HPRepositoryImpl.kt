package com.jimd.harrypotterapi.data.repository

import com.jimd.harrypotterapi.data.ApiService
import com.jimd.harrypotterapi.domain.model.HPCharacterIndividualModel
import com.jimd.harrypotterapi.domain.model.HPCharacterModel
import com.jimd.harrypotterapi.domain.repositori.HPRepository
import com.jimd.harrypotterapi.utils.toModel
import javax.inject.Inject

class HPRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):HPRepository {
    override suspend fun getAllStudent(): Result<List<HPCharacterModel>> {
        return try {
            val character = apiService.getAllStudent().map {
                it.toModel()
            }
            Result.success(character)
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    override suspend fun getStudent(id: String): Result<HPCharacterIndividualModel> {
        return try {
            val character = apiService.getStudent(id).map {
                it.toModel()
            }
            Result.success(character.first())
        }catch (e:Exception){
            Result.failure(e)
        }
    }


}