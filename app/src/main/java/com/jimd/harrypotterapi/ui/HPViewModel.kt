package com.jimd.harrypotterapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimd.harrypotterapi.domain.repositori.HPRepository
import com.jimd.harrypotterapi.utils.HomeUiState
import com.jimd.harrypotterapi.utils.StudentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HPViewModel @Inject constructor(
    private val hpRepository: HPRepository
):ViewModel() {

    private val _character = MutableStateFlow<HomeUiState>(HomeUiState.loading)
    val character = _character

    fun getAllStudent(){
        viewModelScope.launch {
            hpRepository.getAllStudent().onSuccess {
                _character.value = HomeUiState.success(it)
            }.onFailure {
                _character.value = HomeUiState.failure(it)
            }
        }
    }

    private val _student=MutableStateFlow<StudentUiState>(StudentUiState.loading)
    val student = _student

    fun getStudent(id: String){
        viewModelScope.launch {
            hpRepository.getStudent(id).onSuccess {
                _student.value=StudentUiState.success(it)
            }.onFailure {
                _student.value=StudentUiState.failure(it)
            }
        }
    }

}