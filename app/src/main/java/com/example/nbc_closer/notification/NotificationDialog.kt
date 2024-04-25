package com.example.nbc_closer.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.nbc_closer.MainActivity
import com.example.nbc_closer.databinding.DialogNotificationBinding
import com.example.nbc_closer.notification.Constant.Companion.NOTIFICATION_ID
import com.example.nbc_closer.notification.Constant.Companion.notiTitle

class NotificationDialog :DialogFragment(){
    private var _binding:DialogNotificationBinding? = null
    private val binding get() = _binding!!
    private var alarm = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogNotificationBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val alarmManager = binding.root.context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(binding.root.context, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(binding.root.context, NOTIFICATION_ID ,intent, PendingIntent.FLAG_MUTABLE)

        binding.notiRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if(i == binding.notiRadioBt1.id) alarm = 5000
            else if(i == binding.notiRadioBt2.id) alarm = 10000
            else alarm = 150000
        }
        binding.notiCancelBtn.setOnClickListener {
            dismiss()
        }
        binding.notiSaveBtn.setOnClickListener {
            var title = binding.notiMessageEt.text.toString()
            notiTitle = title
            intent.putExtra("title", title)
            Log.d("sendtitle", "${title}")
            val triggerTime = (SystemClock.elapsedRealtime() + alarm)
            alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}