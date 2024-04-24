package com.example.nbc_closer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.nbc_closer.databinding.DialogNotificationBinding

class NotificationDialog :DialogFragment(){
    private var _binding:DialogNotificationBinding?=null
    private val binding get() = _binding!!
    private var alarm = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogNotificationBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.notiRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if(i == binding.notiRadioBt1.id) alarm = 5000
            else if(i == binding.notiRadioBt2.id) alarm = 10000
            else alarm = 150000
        }
        binding.notiCancelBtn.setOnClickListener {
            dismiss()
        }
        binding.notiSaveBtn.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}