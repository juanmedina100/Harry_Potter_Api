package com.jimd.harrypotterapi.utils

import com.jimd.harrypotterapi.domain.model.HPCharacterModel

sealed class HomeUiState {
    object loading:HomeUiState()
    data class success(val charactersList:List<HPCharacterModel>):HomeUiState()
    data class failure(val error:Throwable):HomeUiState()
}