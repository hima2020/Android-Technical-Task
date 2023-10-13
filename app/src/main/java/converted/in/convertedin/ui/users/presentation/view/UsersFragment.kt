package converted.`in`.convertedin.ui.users.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import converted.`in`.convertedin.databinding.FragmentUsersBinding
import converted.`in`.convertedin.ui.users.presentation.view.adapter.UserListAdapter
import converted.`in`.convertedin.ui.users.presentation.viewmodel.UsersViewModel
import converted.`in`.convertedin.utils.GeneralStates
import converted.`in`.convertedin.utils.Utils
import converted.`in`.domain.response.UsersApiResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UsersFragment : Fragment() {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var adapter: UserListAdapter
    private val viewModel: UsersViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        initActions()
        initObservation()
        getUsers()
        return binding.root
    }

    private fun getUsers() {
        if (viewModel.users.isEmpty()) {
            viewModel.getUsers()
            initObservation()
        } else {
            initUsersView(viewModel.users)
        }
    }

    private fun initObservation() {
        lifecycleScope.launch {
            viewModel.successResponse.collect {
                when (it) {
                    is GeneralStates.Failed -> {
                        Utils.hideProgressBar()
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()

                    }

                    is GeneralStates.Loading -> {
                        Utils.showProgressBar(requireContext(), false)
                    }

                    is GeneralStates.Success<*> -> {
                        Utils.hideProgressBar()
                        initUsersView(it.data as UsersApiResponse)
                    }
                }
            }
        }
    }

    private fun initUsersView(res: UsersApiResponse) {
        viewModel.users = res
        adapter = UserListAdapter(res) { userDate ->
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToAlbumsFragment(
                    Gson().toJson(userDate)
                )
            )
        }
        binding.apply {
            rvUsers.adapter = adapter
        }

    }

    private fun initActions() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        binding.btnRandom.setOnClickListener {
            findNavController().navigate(
                UsersFragmentDirections.actionUsersFragmentToAlbumsFragment(
                    Gson().toJson(viewModel.users.random())
                )
            )
        }

    }


}