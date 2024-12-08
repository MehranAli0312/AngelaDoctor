package com.example.angeladoctor.ui.clean.presentation.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angeladoctor.ui.clean.domain.usecase.GetUserNameUseCase
import com.example.angeladoctor.ui.clean.domain.usecase.SetUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val setUserNameUseCase: SetUserNameUseCase
) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> get() = _userName

    init {
        loadUserName()
    }

    private fun loadUserName() {
        viewModelScope.launch {
            getUserNameUseCase.execute().collect { name ->
                _userName.value = name
            }
        }
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            setUserNameUseCase.execute(name)
        }
    }
}