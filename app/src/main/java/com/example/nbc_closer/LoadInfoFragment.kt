package com.example.nbc_closer

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.nbc_closer.databinding.FragmentLoadInfoBinding

class LoadInfoDialogFragment : DialogFragment() {

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

//    private fun checkPermissionAgain() {
//        val permission =
//            ContextCompat.checkSelfPermission(requireContext(), "android.permission.READ_CONTACTS")
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            dismiss() //Me : 토스트 메시지 띄우고 싶다
//        } else {
//            initView()
//        }
//    }

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

//    private fun seekPermission() {
//        val builder = AlertDialog.Builder(requireContext())
//
//        builder.setTitle("연락처 접근 허용")
//        builder.setMessage("핸드폰 연락처에서도 사람을 추가할 수 있습니다. 연락처 접근을 허용하시겠습니까?")
//        builder.setIcon(R.drawable.icon_load)
//
//        val listener = object : DialogInterface.OnClickListener {
//            override fun onClick(p0: DialogInterface?, p1: Int) {
//                when (p1) {
//                    DialogInterface.BUTTON_POSITIVE -> getPermission()
//                    DialogInterface.BUTTON_NEGATIVE -> return
//                }
//            }
//        }
//
//        builder.setPositiveButton("허용", listener)
//        builder.setNegativeButton("거부", listener)
//
//        builder.show()
//    }

    private fun getPermission() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf<String>("android.permission.READ_CONTACTS"),
            0
        )
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            requestCode -> {
//                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initView()
//                } else {
//                    dismiss()
//                }
//            }
//        }
//    }

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
            val data =
                UserData(R.drawable.user_img_jin, name, email, phoneNumber, false, uri = null)
            datalist.add(data)
            binding.loadDialogName.text.clear()
            binding.loadDialogEmail.text.clear()
            binding.loadDialogPhoneNumber.text.clear()
            addSavedButtonClicked = true
            dismiss()
        }
    }
}