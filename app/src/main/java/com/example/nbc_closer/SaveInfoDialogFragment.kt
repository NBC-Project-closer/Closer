package com.example.nbc_closer


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.nbc_closer.databinding.FragmentSaveInfoBinding


class SaveInfoDialogFragment :DialogFragment() {

    private  var _binding: FragmentSaveInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.dialogCancellation.setOnClickListener {
            dismiss()
        }

        binding.dialogSave.setOnClickListener {
            dismiss()
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveInfoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}