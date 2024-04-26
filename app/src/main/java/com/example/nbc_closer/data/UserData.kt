package com.example.nbc_closer.data

import android.net.Uri
import android.os.Parcelable
import com.example.nbc_closer.R
import kotlinx.parcelize.Parcelize


@Parcelize // 추후 데이터 전달을 위해 Parcelize 설정
data class UserData(
    val img : Int,
    val name : String,
    val email : String,
    val number : String,
    var like : Boolean,
    val message : String,
    var uri: Uri?,
) : Parcelable
    val datalist = mutableListOf<UserData>(
        UserData(R.drawable.user_img_iu, "아이유", "dkdldb_IU@naver.com", "010-1234-1234", false, "오늘 날씨 맑음\n기분 최고\n한강 갈 사람?",null),
        UserData(R.drawable.user_img_wonyung, "장원영", "wonyoung@gmail.com", "010-5678-5678", false, "다들 생일 축하 고마워요\nHappy Birthday",null),
        UserData(R.drawable.user_img_suji, "수지","likeapple@gmail.com", "010-5468-3543",false,"5/3 ~ 5/7 일본 여행~\n전화, 문자 힘들어요." ,null),
        UserData(R.drawable.user_img_yujin, "안유진", "bluecar@naver.com","010-9874-3216",false, "신곡 많은 사랑 부탁드려요!",null),
        UserData(R.drawable.user_img_jiwon, "김지원", "queenjiwon@gmail.com", "010-7441-1035", false, "눈물의 여왕 많은 관심 부탁드려요\n본방사수!",null),
        UserData(R.drawable.user_img_songgang,"송강", "sweethome@naver.com", "010-0045-9648",false, "스위트홈 시즌3 제작중.\n좋은 작품으로 찾아뵐게요.\n오늘 날씨는 맑음",null),
        UserData(R.drawable.user_img_juhuck,"남주혁", "darkhero@gmail.com", "010-1174-6451",false, "사과가 먹고 싶어요.\n요즘 사과가 왜이리 비싸죠?",null),
        UserData(R.drawable.user_img_suhyun,"김수현", "ailenkim@naver.com","010-9656-8545",false, "오늘의 책 추천 : 꿀벌의 예언\n요즘 너무 재밌게 읽고 있어요.",null),
        UserData(R.drawable.user_img_dohyun, "이도현", "hateghost@gmail.com","010-4486-3432",false, "범죄도시4 재밌나요? 보신 분들 후기 좀 부탁드립니다. 아니면 요즘 재미있는 영화 추천도 받아요.",null),
        UserData(R.drawable.user_img_jin, "진", "dynamite@naver.com", "010-9654-4352",false, "넷플릭스 정주행 중..\n넷플릭스에 재밌는 컨텐츠 많이 올라와 있네요.",null)
    )

