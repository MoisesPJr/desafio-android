package  com.picpay.desafio.android.feature.userList.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import com.picpay.desafio.android.feature.userList.domain.useCase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UserUiState {
    data class Success(val users: List<UserDomain>) : UserUiState()
    data object Error : UserUiState()
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UserUiState>()
    val uiState: LiveData<UserUiState> = _uiState

    fun getUsersLocal() {
        viewModelScope.launch {
            try {
                val response = useCase.getUsersLocal()
                _uiState.postValue(UserUiState.Success(response))
            } catch (e: Exception) {
                Log.e("USER_VIEW", e.message.toString())
                _uiState.postValue(UserUiState.Error)
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = useCase.invoke()
                _uiState.postValue(UserUiState.Success(response))
            } catch (e: Exception) {
                Log.e("USER_VIEW", e.message.toString())
                _uiState.postValue(UserUiState.Error)
            }
        }
    }
}
