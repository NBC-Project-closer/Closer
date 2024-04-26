package com.example.nbc_closer.mypage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.nbc_closer.databinding.FragmentMypageBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyPageFragment: Fragment() {
    private val binding by lazy { FragmentMypageBinding.inflate(layoutInflater) }
    private var nameCheck = false
    private var emailCheck = false
    private var phoneCheck = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        lifecycleScope.launch {
            whenStarted {
                while (true) {
                    delay(500)
                    availableSaveButton()
                }
            }
        }
    }

    private fun initView(){
        binding.mypageEdit.setOnClickListener {
            binding.mypagePhone.visibility = View.GONE
            binding.mypageEmail.visibility = View.GONE
            binding.mypageStatusMessage.visibility = View.GONE
            binding.mypageEdit.visibility = View.GONE
            binding.mypageNameBlock.visibility = View.GONE
            binding.mypageName.visibility = View.GONE
            binding.mypageLine1.visibility = View.GONE
            binding.mypageLine2.visibility = View.GONE
            binding.mypageEditName.visibility = View.VISIBLE
            binding.mypageEditPhone.visibility = View.VISIBLE
            binding.mypageEditEmail.visibility = View.VISIBLE
            binding.mypageEditStatusMessage.visibility = View.VISIBLE
            binding.mypageBtnModify.visibility = View.VISIBLE
        }
        binding.mypageBtnModify.setOnClickListener {
            binding.mypagePhone.visibility = View.VISIBLE
            binding.mypageEmail.visibility = View.VISIBLE
            binding.mypageStatusMessage.visibility = View.VISIBLE
            binding.mypageEdit.visibility = View.VISIBLE
            binding.mypageNameBlock.visibility = View.VISIBLE
            binding.mypageName.visibility = View.VISIBLE
            binding.mypageLine1.visibility = View.VISIBLE
            binding.mypageLine2.visibility = View.VISIBLE
            binding.mypageEditPhone.visibility = View.GONE
            binding.mypageEditEmail.visibility = View.GONE
            binding.mypageEditStatusMessage.visibility = View.GONE
            binding.mypageBtnModify.visibility = View.GONE
            binding.mypageEditName.visibility = View.GONE
            binding.mypageName.text = binding.mypageEditName.text
            binding.mypagePhone.text = binding.mypageEditPhone.text
            binding.mypageEmail.text = binding.mypageEditEmail.text
            binding.mypageStatusMessage.text = binding.mypageEditStatusMessage.text
            Log.d("d", "${binding.mypageName.text}")
            nameCheck = false
            phoneCheck = false
            emailCheck = false
        }
        binding.mypageEditName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val englishPattern = Regex("^[a-zA-Z]{5,}\$")
                val koreanPattern = Regex("^[가-힣]{3,}\$")
                if (englishPattern.matches(binding.mypageEditName.text) || koreanPattern.matches(binding.mypageEditName.text)) {
                    binding.mypageEditName.error = null
                    nameCheck = true
                } else {
                    binding.mypageEditName.error = "이름은 5글자 이상(영어) or 3글자 이상(한글) 입력"
                    nameCheck = false
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.mypageEditPhone.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val phonePattern = Regex("^\\d{11}$")
                if (phonePattern.matches(binding.mypageEditPhone.text)) {
                    binding.mypageEditPhone.error = null
                    phoneCheck = true
                } else {
                    binding.mypageEditPhone.error = "올바르지 않은 전화번호 형식"
                    phoneCheck = false
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.mypageEditEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val emailPattern = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3}+)$")

                if (emailPattern.matches(binding.mypageEditEmail.text)) {
                    binding.mypageEditEmail.error = null
                    emailCheck = true
                } else {
                    binding.mypageEditEmail.error = ("올바르지 않는 이메일 형식")
                    emailCheck = false
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
    private suspend fun availableSaveButton(){
        delay(500)
        Log.d("확인", "$nameCheck, $emailCheck, $phoneCheck ")
        if(nameCheck && emailCheck && phoneCheck){
            Log.d("확인", "수정 버튼 활성화")
            binding.mypageBtnModify.isEnabled = true
        }
        else {
            Log.d("확인", "수정 버튼 비활성화")
            binding.mypageBtnModify.isEnabled = false
        }
    }
}
