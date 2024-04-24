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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    //메소드 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    //초기 다이얼로그 설정
    private fun initView() {
        binding.dialogCancellation.setOnClickListener {
            binding.dialogName.text.clear()
            binding.dialogEmail.text.clear()
            binding.dialogPhoneNumber.text.clear()
            dismiss()
        }
        binding.dialogSave.setOnClickListener {
            val name = binding.dialogName.text.toString()
            val email = binding.dialogEmail.text.toString()
            val phoneNumber = binding.dialogPhoneNumber.text.toString()
            val data = UserData(R.drawable.user_img_jin, name, email, phoneNumber, false)
            datalist.add(data)
            binding.dialogName.text.clear()
            binding.dialogEmail.text.clear()
            binding.dialogPhoneNumber.text.clear()
            addSavedButtonClicked = true
            dismiss()
        }
    }
}