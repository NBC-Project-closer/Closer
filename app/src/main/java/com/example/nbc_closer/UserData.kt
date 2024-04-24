package com.example.nbc_closer

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize // 추후 데이터 전달을 위해 Parcelize 설정
data class UserData(
    val img : Int,
    val name : String,
    val email : String,
    val number : String,
    var like : Boolean,
    var uri: Uri?,
) : Parcelable
    val datalist = mutableListOf<UserData>(
        UserData(R.drawable.user_img_iu, "아이유", "dkdldb_IU@naver.com", "010-1234-1234", false, null),
        UserData(R.drawable.user_img_wonyung, "장원영", "wonyoung@gmail.com", "010-5678-5678", false, null),
        UserData(R.drawable.user_img_suji, "수지","likeapple@gmail.com", "010-5468-3543",false, null),
        UserData(R.drawable.user_img_yujin, "안유진", "bluecar@naver.com","010-9874-3216",false, null),
        UserData(R.drawable.user_img_jiwon, "김지원", "queenjiwon@gmail.com", "010-7441-1035", false, null),
        UserData(R.drawable.user_img_songgang,"송강", "sweethome@naver.com", "010-0045-9648",false, null),
        UserData(R.drawable.user_img_juhuck,"남주혁", "darkhero@gmail.com", "010-1174-6451",false, null),
        UserData(R.drawable.user_img_suhyun,"김수현", "ailenkim@naver.com","010-9656-8545",false, null),
        UserData(R.drawable.user_img_dohyun, "이도현", "hateghost@gmail.com","010-4486-3432",false, null),
        UserData(R.drawable.user_img_jin, "진", "dynamite@naver.com", "010-9654-4352",false, null)
    )

