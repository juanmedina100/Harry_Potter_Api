package com.jimd.harrypotterapi.data

import com.jimd.harrypotterapi.data.dto.all.HPDto
import com.jimd.harrypotterapi.data.dto.character.CharacterDto
import com.jimd.harrypotterapi.utils.Constants.Companion.ALL_STUDENT
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(ALL_STUDENT)
    suspend fun getAllStudent(): HPDto

    @GET("character/{id}")
    suspend fun getStudent(@Path("id") id:String):CharacterDto
}