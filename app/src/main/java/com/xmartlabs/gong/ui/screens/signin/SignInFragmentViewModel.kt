package com.xmartlabs.gong.ui.screens.signin

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.xmartlabs.gong.data.model.User
import com.xmartlabs.gong.device.common.Result
import com.xmartlabs.gong.domain.usecase.SignInUseCase
import com.xmartlabs.gong.domain.usecase.TimeTrackerUseCase
import java.util.Date

/**
 * Created by mirland on 25/04/20.
 */
class SignInFragmentViewModel(
    private val signInUseCase: SignInUseCase,
    timeTrackerUseCase: TimeTrackerUseCase
) : ViewModel() {
  private val signInMutableLiveData = MutableLiveData<SignInUseCase.Params>()
  val viewModelTime = timeTrackerUseCase.invoke(TimeTrackerUseCase.Params(Date()))
  val signIn: LiveData<Result<User>> = signInMutableLiveData
      .switchMap { params -> signInUseCase.invoke(params) }

  @MainThread
  fun signIn(userId: String, password: String) {
    signInMutableLiveData.value = SignInUseCase.Params(userId, password)
  }
}