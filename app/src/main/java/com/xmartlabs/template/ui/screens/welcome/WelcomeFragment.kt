package com.xmartlabs.template.ui.screens.welcome

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.xmartlabs.template.databinding.FragmentWelcomeBinding
import com.xmartlabs.template.ui.common.BaseFragment
import com.xmartlabs.template.ui.common.extensions.observeSuccessResult
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by mirland on 25/04/20.
 */
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>() {
  private val viewModel: WelcomeFragmentViewModel by viewModel()
  private val args: WelcomeFragmentArgs by navArgs()

  override fun inflateViewBinding(): FragmentWelcomeBinding = FragmentWelcomeBinding.inflate(layoutInflater)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.loadUser(args.userid)
    viewModel.userLiveData.observeSuccessResult(viewLifecycleOwner) { user ->
      @SuppressLint("SetTextI18n")
      viewBinding.titleTextView.text = "Hi ${user.name}"
    }
  }
}