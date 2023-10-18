package com.jimd.harrypotterapi.utils

import com.jimd.harrypotterapi.domain.model.HPCharacterIndividualModel

sealed class StudentUiState{
    object loading:StudentUiState()
    data class success(val student:HPCharacterIndividualModel):StudentUiState()
    data class failure(val error:Throwable):StudentUiState()
}
