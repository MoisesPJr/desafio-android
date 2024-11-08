package  com.picpay.desafio.android.feature.userList.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.feature.userList.ErrorsEnum
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import com.picpay.desafio.android.feature.userList.domain.useCase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel  @Inject constructor(private var useCase: GetUsersUseCase) : ViewModel() {

    private var _user = MutableLiveData<List<UserDomain>>()
    val user: MutableLiveData<List<UserDomain>>
        get() = _user

    private var _error = MutableLiveData<ErrorsEnum>()
    val error: MutableLiveData<ErrorsEnum>
        get() = _error



    fun getUsersLocal() {
        viewModelScope.launch {
            try {
                val response = useCase.getUsersLocal()
                response.let { _user.postValue(response) }
            } catch (e: Exception) {
                Log.e("USER_VIEW", e.message.toString())
                handlerError()
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            try {
                val response = useCase.invoke()
                response.let { _user.postValue(response) }
            } catch (e: Exception) {
                Log.e("USER_VIEW", e.message.toString())
                handlerError()
            }
        }
    }


    private fun handlerError() {
        _error.postValue(ErrorsEnum.UNKNOWN_ERROR)
    }

}