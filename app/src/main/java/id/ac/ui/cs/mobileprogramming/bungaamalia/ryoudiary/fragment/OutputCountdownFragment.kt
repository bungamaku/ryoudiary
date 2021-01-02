package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.R
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.databinding.FragmentOutputCountdownBinding
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.util.cancelNotifications
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.util.sendNotification
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel.CountdownViewModel

class OutputCountdownFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel
    private lateinit var binding: FragmentOutputCountdownBinding
    var countDownOnGoing = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_output_countdown, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        binding.viewModel = viewModel

        createChannel(
            getString(R.string.notification_channel_id),
            getString(R.string.notification_channel_name)
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPrevious.setOnClickListener {
            if (countDownOnGoing) {
                Toast.makeText(activity?.applicationContext,
                    "Countdown is still on going!", Toast.LENGTH_SHORT).show()
            } else {
                findNavController()
                    .navigate(R.id.action_OutputCountdownFragment_to_homeFragment)
            }
        }

        var value = viewModel.currentValue.value?.toLong()
        binding.textViewTitleSecond.text = getString(R.string.textview_title_second_text, value)

        if (value != null) {
            value *= 1000
            startCountdown(value)
        }
    }

    private fun startCountdown(seconds: Long) {
        object : CountDownTimer(seconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val notificationManager =
                    ContextCompat.getSystemService(
                        requireContext(),
                        NotificationManager::class.java
                    ) as NotificationManager
                notificationManager.cancelNotifications()
                countDownOnGoing = true
                binding.textViewSeconds.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                val notificationManager = ContextCompat.getSystemService(
                    requireContext(),
                    NotificationManager::class.java
                ) as NotificationManager

                notificationManager.sendNotification(
                    context?.getText(R.string.notification_channel_description).toString(),
                    requireContext()
                )
                countDownOnGoing = false
            }
        }.start()
    }

    private fun createChannel(channelId: String, channelName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_channel_description)

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}