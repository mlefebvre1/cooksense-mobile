package org.chef.cooksense.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false,
)

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState(isLoading = true)
            try {
                repository.signIn(email, password)
                _uiState.value = AuthUiState(isSuccess = true)
            } catch (e: Exception) {
                _uiState.value = AuthUiState(errorMessage = e.message)
            }
        }

    }
}