package com.example.test.ui.add_user

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.FragmentAddUserBinding
import com.example.test.table.User
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddUserFragment : Fragment() {

    private var _binding: FragmentAddUserBinding? = null
    lateinit var  addUserViewModel :AddUserViewModel
     var user: User?=null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(!requireArguments().isEmpty) {
                user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requireArguments().getParcelable("user", User::class.java)!!
                } else {
                    requireArguments().getParcelable<User>("user")!!
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        addUserViewModel = ViewModelProvider(this)[AddUserViewModel::class.java]

        if (user==null)
        (requireActivity() as MainActivity).supportActionBar!!.title="Add User"
        else
        (requireActivity() as MainActivity).supportActionBar!!.title="Update User"


        _binding = FragmentAddUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        addUserViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        if (user!=null){
            binding.save.text="Update"
            binding.firstName.setText(user!!.first_name)
            binding.lastName.setText(user!!.last_name)
            binding.phone.setText(user!!.phone_number)
            binding.email.setText(user!!.email)
        }

        binding.save.setOnClickListener {
           saveUser()
        }



        return root
    }

    private fun saveUser() {
        val first_name = binding.firstName.text.toString()
        val last_name = binding.lastName.text.toString()
        val phone_number = binding.phone.text.toString()
        val email = binding.email.text.toString()

        if (first_name.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Input FirstName", Toast.LENGTH_SHORT).show()
            return
        }
        else if (last_name.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Input LastName", Toast.LENGTH_SHORT).show()
            return
        }else if (phone_number.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Input Phone Number", Toast.LENGTH_SHORT).show()
            return
        }else if (email.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Input Email", Toast.LENGTH_SHORT).show()
            return
        }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(requireContext(), "Invalid Email", Toast.LENGTH_SHORT).show()
            return
        }




            addUserViewModel.getDuplicateUser(phone_number).observe(viewLifecycleOwner, Observer {

                val user = User(
                    user!!.id,
                    first_name,
                    last_name,
                    phone_number,
                    email
                )
                if (it==null){
                    if (user!=null){
                       updateuser(user)
                    }else {
                        addUserViewModel.addUser(
                            User(
                                0,
                                first_name,
                                last_name,
                                phone_number,
                                email
                            )
                        )
                        Toast.makeText(
                            requireContext(),
                            "User Add Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        clean()
                    }
                }else{
                    updateuser(user)
                    if (user==null)
                    Toast.makeText(requireContext(), "Phone Number already Exist", Toast.LENGTH_SHORT).show()
                }
            })




    }

    private fun updateuser(user: User?) {

        if (user!=null){

            addUserViewModel.updateuser(
                user
            )
            Toast.makeText(
                requireContext(),
                "User Update Successfully",
                Toast.LENGTH_SHORT
            ).show()

            requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.userDetailsFragment,
                bundleOf("user" to user)
            )
        }

    }

    private fun clean() {
        binding.email.setText("")
        binding.firstName.setText("")
        binding.lastName.setText("")
        binding.phone.setText("")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}