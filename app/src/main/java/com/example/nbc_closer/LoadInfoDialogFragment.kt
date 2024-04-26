package com.example.nbc_closer

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.bumptech.glide.Glide
import com.example.nbc_closer.databinding.FragmentLoadInfoBinding
import com.example.nbc_closer.databinding.FragmentSaveInfoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadInfoDialogFragment : DialogFragment() {
    var emailCheck = false
    private var uri: Uri? = null
    private lateinit var email : EditText
    private var _binding: FragmentLoadInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkPermission() {
        val permission = ContextCompat.checkSelfPermission(requireContext(), "android.permission.READ_CONTACTS")
        if (permission != PackageManager.PERMISSION_GRANTED) {
            dismiss()
            Toast.makeText(getActivity(), "연락처 접근 권한이 필요합니다.\n허용하시고 다시 눌러주세요.", Toast.LENGTH_LONG).show()
            getPermission()
        } else if (permission == PackageManager.PERMISSION_GRANTED) {
            initView()
        } else {
            dismiss()
        }
    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf<String>("android.permission.READ_CONTACTS"),
            0
        )
    }

    private fun initView() {

        val contentResolver = requireActivity().contentResolver
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val cursor = contentResolver.query(
                        it.data!!.data!!,
                        arrayOf<String>(
                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ),
                        null,
                        null,
                        null
                    )

                    if (cursor!!.moveToFirst()) {
                        val name = cursor.getString(0)
                        val phone = cursor.getString(1)
                        binding.loadDialogName.setText("$name")
                        binding.loadDialogPhoneNumber.setText("$phone")
                    }
                }
            }

        val intent = Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI)
        resultLauncher.launch(intent)

        binding.loadDialogSave.isEnabled = false
        binding.loadDialogSave.setBackgroundResource(R.drawable.dialog_button_deactivate)
        binding.loadDialogCancellation.setOnClickListener {
            binding.loadDialogName.text.clear()
            binding.loadDialogEmail.text.clear()
            binding.loadDialogPhoneNumber.text.clear()
            dismiss()
        }

        binding.loadDialogSave.setOnClickListener {
            val name = binding.loadDialogName.text.toString()
            val email = binding.loadDialogEmail.text.toString()
            val phoneNumber = binding.loadDialogPhoneNumber.text.toString()
            val data = UserData(-1, name, email, phoneNumber, false, "잘 부탁드립니다!", uri)
            datalist.add(data)
            Toast.makeText(this.context, "연락처가 추가되었습니다.", Toast.LENGTH_SHORT).show()
            binding.loadDialogName.text.clear()
            binding.loadDialogEmail.text.clear()
            binding.loadDialogPhoneNumber.text.clear()
            addSavedButtonClicked = true
            dismiss()
        }

        email = binding.loadDialogEmail
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

        lifecycleScope.launch {
            whenStarted {
                while (true){
                    availableSavedButton()
                }
            }
        }
    }

    private suspend fun availableSavedButton() {
        delay(500)
         if(emailCheck){
            binding.loadDialogSave.isEnabled = true
            binding.loadDialogSave.setBackgroundResource(R.drawable.dialog_button)
        }
        else {
            binding.loadDialogSave.isEnabled = false
            binding.loadDialogSave.setBackgroundResource(R.drawable.dialog_button_deactivate)
        }
    }
}
