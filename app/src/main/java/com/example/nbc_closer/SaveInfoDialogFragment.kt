package com.example.nbc_closer


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.example.nbc_closer.databinding.FragmentSaveInfoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SaveInfoDialogFragment :DialogFragment() {
    var nameCheck = false
    var numberCheck = false
    var emailCheck = false
    private var imageCheck = false
    private  var _binding: FragmentSaveInfoBinding? = null
    private lateinit var addMemberResult: ActivityResultLauncher<Intent>
    private var uri: Uri? = null
    private lateinit var name : EditText
    private lateinit var email : EditText
    private lateinit var number : EditText
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
        name = binding.dialogName
        email = binding.dialogEmail
        number = binding.dialogPhoneNumber
        binding.dialogSave.isEnabled = false
        binding.dialogSave.setBackgroundResource(R.drawable.dialog_button_deactivate)
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
            val data = UserData(-1, name, email, phoneNumber, false, uri)
            datalist.add(data)
            Toast.makeText(this.context, "연락처가 추가되었습니다.", Toast.LENGTH_SHORT).show()
            binding.dialogName.text.clear()
            binding.dialogEmail.text.clear()
            binding.dialogPhoneNumber.text.clear()
            addSavedButtonClicked = true
            dismiss()
        }
        // 이미지 추가 기능
        binding.apply {
            dialogProfile.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type = "image/*"
                addMemberResult.launch(intent)
            }
        }

        addMemberResult = registerForActivityResult(
            ActivityResultContracts
                .StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                uri = it.data!!.data
                Glide.with(this)
                    .load(uri)
                    .into(binding.dialogProfile)
                imageCheck = true
            }
        }
        //editText관련
        name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val englishPattern = Regex("^[a-zA-Z]{5,}\$")
                val koreanPattern = Regex("^[가-힣]{3,}\$")
                if (englishPattern.matches(name.text) || koreanPattern.matches(name.text)) {
                    name.error = null
                    nameCheck = true
                } else {
                    name.error = "이름은 5글자 이상(영어) or 3글자 이상(한글) 입력"
                    nameCheck = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val phonePattern = Regex("^\\d{11}$")
                if (phonePattern.matches(number.text)) {
                    number.error = null
                    numberCheck = true
                } else {
                    number.error = "올바르지 않은 전화번호 형식"
                    numberCheck = false
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val emailPattern = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3}+)$")

                if (emailPattern.matches(email.text)) {
                    email.error = null
                    emailCheck = true
                } else {
                    email.error = ("올바르지 않는 이메일 형식")
                    emailCheck = false
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        //버튼 활성화 관련 코루틴 이용
        lifecycleScope.launch {
            whenStarted {
                while (true){
                    availableSavedButton()
                }
            }
        }
    }
    //버튼 활성화 관련, suspend 함수 활용
    private suspend fun availableSavedButton() {
        delay(500)
        Log.d("d", "함수 작동 중")
        Log.d("확인", "$imageCheck, $nameCheck, $emailCheck, $numberCheck")
        if(imageCheck && nameCheck && emailCheck && numberCheck){
            binding.dialogSave.isEnabled = true
            Log.d("firm", "버튼 활성화")
            binding.dialogSave.setBackgroundResource(R.drawable.dialog_button)
        }
        else {
            binding.dialogSave.isEnabled = false
            Log.d("firm", "버튼 비활성화")
            binding.dialogSave.setBackgroundResource(R.drawable.dialog_button_deactivate)
        }
    }
}