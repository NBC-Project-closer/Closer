package com.example.nbc_closer


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.nbc_closer.databinding.FragmentSaveInfoBinding


@Suppress("UNREACHABLE_CODE")
class SaveInfoDialogFragment :DialogFragment() {

    private  var _binding: FragmentSaveInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveInfoBinding.inflate(inflater, container, false)


        var nameCheck = false
        var numberCheck = false
        var emailCheck = false
        var imageCheck = false

        var savelist = ArrayList<UserData>()

        val name = binding.dialogName
        val number = binding.dialogPhoneNumber
        val email = binding.dialogEmail

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
        binding.dialogCancellation.setOnClickListener {
            dismiss()
        }

        binding.dialogSave.setOnClickListener {
            if(nameCheck && numberCheck && emailCheck && imageCheck) {
                val inputName = name.text.toString()
                val inputNumber = number.text.toString()
                val inputEmail = email.text.toString()

                dismiss()
            } else {
            Toast.makeText(context, "형식에 맞지 않은 정보가 존재합니다.", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}