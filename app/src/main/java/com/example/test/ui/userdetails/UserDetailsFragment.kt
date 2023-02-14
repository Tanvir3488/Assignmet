package com.example.test.ui.userdetails

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.FragmentUserDetailsBinding
import com.example.test.table.User


class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null
    lateinit var user: User

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userDetailsViewModel =
            ViewModelProvider(this).get(UserDetailsViewModel::class.java)

        createNotificationChannel()
        (requireActivity() as MainActivity).supportActionBar!!.title="User Details"
        user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments()!!.getParcelable("user", User::class.java)!!
        } else {
            requireArguments()!!.getParcelable<User>("user")!!
        }


        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main)

        binding.edit.setOnClickListener {
            navController.popBackStack()
            navController.navigate(R.id.add_user, bundleOf("user" to user))

        }

        binding.sendNotification.setOnClickListener {
           sendnotificationn()
        }


        binding.user = user
        return root
    }

    private fun sendnotificationn() {
        var builder = NotificationCompat.Builder(requireContext(), "101")
            .setSmallIcon(R.drawable.add)
            .setContentTitle("${user.first_name} ${user.last_name}")
            .setContentText(user.email)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager =
            requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager!!.notify(Math.random().toInt(), builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "test"
            val descriptionText = "assignment"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("101", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}