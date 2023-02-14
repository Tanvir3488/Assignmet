package com.example.test.ui.AllUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.Adapter.UserAdapter
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.table.User
import com.example.test.databinding.FragmentAlluserBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllUserFragment : Fragment() {

    private var _binding: FragmentAlluserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val allUserViewModel =
            ViewModelProvider(this)[AllUserViewModel::class.java]

        _binding = FragmentAlluserBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).supportActionBar!!.title="All User"
        binding.userlist.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.divider
            )!!
        )
        binding.userlist.addItemDecoration(dividerItemDecoration)

        val navController = requireActivity().findNavController(R.id.nav_host_fragment_activity_main)

        allUserViewModel.getAllUser().observe(viewLifecycleOwner, Observer {

            binding.userlist.adapter = UserAdapter(it as ArrayList<User>){
                navController.popBackStack(R.id.userDetailsFragment,true)
                navController.navigate(R.id.userDetailsFragment, bundleOf("user" to it))



            }

        })


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}