package com.example.nbc_closer.contact

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc_closer.detail.DetailActivity
import com.example.nbc_closer.contact.floating_action.LoadInfoDialogFragment
import com.example.nbc_closer.R
import com.example.nbc_closer.contact.adapter.ContactGridAdapter
import com.example.nbc_closer.contact.adapter.ContactRecyclerAdapter
import com.example.nbc_closer.contact.adapter.OnItemClick
import com.example.nbc_closer.contact.floating_action.SaveInfoDialogFragment
import com.example.nbc_closer.data.UserData
import com.example.nbc_closer.databinding.FragmentContactBinding
import com.example.nbc_closer.data.datalist
import com.example.nbc_closer.contact.floating_action.notification.NotificationDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var isFloatingButtonClick : Boolean = false
var addSavedButtonClicked : Boolean = false
const val GRID = 1
const val LIST = 0
var recyclerViewType : Int = 0
class ContactFragment : Fragment() {
    lateinit var binding : FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(layoutInflater)
        //스와이프 기능
//        swipeToCall()
        //스와이프 기능
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initFragment()
        addData()
        swipeToCall()
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }

    //R.menu.app_bar_menu에 해당하는 리소스를 해당 contact 프래그먼트 기능에 만들기
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //R.menu.app_bar_menu_에 해당하는 리소스를 클릭시 작동할 기능 관련 메소드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.show_grid -> {
                    Log.d("D", "그리드 형식 클릭")
                    initGrid()
                    recyclerViewType = GRID
                   }
                else -> {
                    Log.d("D", "리스트 형식 클릭")
                    initFragment()
                    recyclerViewType = LIST
                        }
        }
        return super.onOptionsItemSelected(item)
    }
    //초기 설정을 함수로 내렸습니다, onViewCreated를 깔끔하게 하기 위해
    private fun initFragment() {
        binding.contactRecyclerView.adapter = ContactRecyclerAdapter(datalist, object : OnItemClick {
            override fun onDataClick(data: UserData) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("detail", data)
                startActivity(intent)
            }
        })
        binding.contactRecyclerView.layoutManager = LinearLayoutManager(this.context)
        //플로팅 버튼 설정 관련 메소드
        binding.contactFloatingBtn.setOnClickListener {
            if(!isFloatingButtonClick){
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_x)
                binding.contactFloatingAdd.visibility = View.VISIBLE
                binding.contactFloatingAlarm.visibility = View.VISIBLE
                binding.contactFloatingLoad.visibility = View.VISIBLE
                isFloatingButtonClick = true
            }
            else {
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_plus)
                binding.contactFloatingAdd.visibility = View.GONE
                binding.contactFloatingAlarm.visibility = View.GONE
                binding.contactFloatingLoad.visibility = View.GONE
                isFloatingButtonClick = false
            }
        }

        binding.contactFloatingLoad.setOnClickListener {
            Log.d("확인", "로드오픈")
            openLoadDialog()
        }
        
        binding.contactFloatingAdd.setOnClickListener {
            Log.d("확인", "디이얼로그오픈")
            openAddDialog()
        }
        binding.contactFloatingAlarm.setOnClickListener{
            Log.d("확인", "알람오픈")
            openAlarmDialog()
        }

    }
    private fun initGrid(){
        binding.contactRecyclerView.adapter = ContactGridAdapter(datalist,object : OnItemClick {
            override fun onDataClick(data: UserData) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra("detail", data)
                startActivity(intent)
            }
        })
        binding.contactRecyclerView.layoutManager = GridLayoutManager(this.context,2)
        //플로팅 버튼 설정 관련 메소드
        binding.contactFloatingBtn.setOnClickListener {
            if(!isFloatingButtonClick){
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_x)
                binding.contactFloatingAdd.visibility = View.VISIBLE
                binding.contactFloatingAlarm.visibility = View.VISIBLE
                binding.contactFloatingLoad.visibility = View.VISIBLE
                isFloatingButtonClick = true
            }
            else {
                binding.contactFloatingBtn.setImageResource(R.drawable.icon_plus)
                binding.contactFloatingAdd.visibility = View.GONE
                binding.contactFloatingAlarm.visibility = View.GONE
                binding.contactFloatingLoad.visibility = View.GONE
                isFloatingButtonClick = false
            }
        }

        binding.contactFloatingLoad.setOnClickListener {
            Log.d("확인", "로드오픈")
            openLoadDialog()
        }

        binding.contactFloatingAdd.setOnClickListener {
            Log.d("확인", "디이얼로그오픈")
            openAddDialog()
        }
        binding.contactFloatingAlarm.setOnClickListener{
            Log.d("확인", "알람오픈")
            openAlarmDialog()
        }

    }

    //다이얼로그 오픈
    private fun openAddDialog() {
        val dialog = SaveInfoDialogFragment()
        dialog.isCancelable = false
        dialog.show(requireFragmentManager(), "openDialog")
    }
    private fun openAlarmDialog(){
        val dialog = NotificationDialog()
        dialog.isCancelable = false
        dialog.show(requireFragmentManager(), "openAlarmDialog")
    }

    private fun openLoadDialog() {
        val dialog = LoadInfoDialogFragment()
        dialog.isCancelable = false
        dialog.show(requireFragmentManager(), "openLoadDialog")
    }

    //코루틴을 활용하여 datalist 변화를 계속 감지하는 메소드
    @SuppressLint("NotifyDataSetChanged")
    private fun addData() {
        lifecycleScope.launch {
            while(true){
                delay(500)
                if(addSavedButtonClicked){
                    (binding.contactRecyclerView.adapter as ContactRecyclerAdapter).notifyDataSetChanged()
                    addSavedButtonClicked = false
                }
            }
        }
    }

    //ItemTouchHelper 구현 완료.
    private fun swipeToCall(){
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false //드래그 사용 안함, 그치만 아예 빼면 오류남
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val callIntent = Intent( Intent.ACTION_DIAL, Uri.parse("tel:" + datalist[position].number) )
                startActivity(callIntent)
            }
            //기존 뷰 다시 죽이고 init? 아니면 그냥 init 부르기?
            //앱 다시 시작될 때???
            //https://stackoverflow.com/questions/30820806/adding-a-colored-background-with-text-icon-under-swiped-row-when-using-androids
            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
               if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                   val itemView = viewHolder.itemView
                   val p = Paint()
                   if (dX > 0) { //좌 -> 우 슬라이드
                       //setColor에 바로 hex값을 넣으니 오류가 떠서 parseColor로 한 번 감싸줌.
                       p.setColor(Color.parseColor("#242251"))
                       c.drawRoundRect(itemView.left.toFloat(),
                           itemView.top.toFloat(), dX, itemView.bottom.toFloat(), 20F, 20F,p)
                   }
                   super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
               }
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.contactRecyclerView)
    }

    override fun onResume() { //프래그먼트 생명주기에서 재시작 타임에 initFragment를 다시금 해 줌. -> 전화 걸고 와도 안사라짐
        super.onResume()
        if(recyclerViewType == LIST)
            initFragment()
        else
            initGrid()
    }
    //ItemTouchHelper 여기까지
}
