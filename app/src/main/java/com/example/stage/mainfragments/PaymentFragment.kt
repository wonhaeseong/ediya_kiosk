package com.example.stage.mainfragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.stage.MainActivity
import com.example.stage.R
import com.example.stage.ServerConnection.*
import com.example.stage.mainfragments.mainRVAdapter.PaymentRVAdapter
import com.example.stage.mainfragments.maindialog.RecipeDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PaymentFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var basketList: ArrayList<Bundle>
    lateinit var paymentRecyclerView: RecyclerView
    lateinit var paymentRVAdapter: PaymentRVAdapter
    var totalCost = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainActivity = activity as MainActivity
        basketList = arguments?.getParcelableArrayList("basketList")!!
        totalCost = arguments?.getInt("totalCost")!!

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view: View = inflater.inflate(R.layout.payment_fragment,container,false)

        paymentRVAdapter = PaymentRVAdapter(mainActivity,basketList)
        paymentRecyclerView = view.findViewById<RecyclerView>(R.id.payment_recyclerview)
        paymentRecyclerView.adapter = paymentRVAdapter
        paymentRecyclerView.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뒤로가기
        var paymentBackBtn = view.findViewById<ImageButton>(R.id.payment_back_button)
        paymentBackBtn.setOnClickListener {
            mainActivity.removeFragment(this,"payment")
        }

        //결제하기
        var paymentPayBtn = view.findViewById<Button>(R.id.payment_pay_button)
        paymentPayBtn.text = (totalCost.toString()+"￦ "+getString(R.string.pay))
        paymentPayBtn.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch{
                //주문내역 넣기
                var orderList : MutableList<OrderItem> = mutableListOf()
                for(i in basketList.indices){
                    var orderItem : OrderItem = OrderItem(
                        basketList[i].getString("basketName")!!,
                        basketList[i].getString("basketMenuCost")!!.toInt(),
                        basketList[i].getString("basketMenuNum")!!.toInt()
                    )
                    orderList.add(orderItem)
                }

                var orderItemPackage = OrderItemPackage(mainActivity.userId,orderList.toList(),totalCost)
                mainActivity.requestOrderApi.postOrderSuspend(orderItemPackage)

                //영수증 띄우기
                //정보 가져오기
                var historyDatas : List<OrderHistoryDatas> = mainActivity.requestOrderApi.getOrderHistorySuspend(mainActivity.userId).data
                Log.d("ddd",historyDatas.toString())
                var newList = arrayListOf<ArrayList<OrderHistoryData>>()
                var newtotalCost = arrayListOf<Int>()
                for(i in historyDatas.indices){
                    var data : ArrayList<OrderHistoryData> = arrayListOf()
                    data.add(historyDatas[i].orderHistoryData)
                    newtotalCost.add(historyDatas[i].total_price)
                    newList.add(data)
                    Log.d("gg",newList.toString())
                }

                mainActivity.orderStorage = newList  //[0,
                Log.d("gg2",newList.toString())
                var recipeDialog = RecipeDialogFragment()
                var bundle = Bundle()
                bundle.putInt("seq",historyDatas.size-1)
                recipeDialog.arguments = bundle
                Log.d("이거실행","ㅇ")
                recipeDialog.show(mainActivity.supportFragmentManager,"recipeDialog")
                mainActivity.basketService.resetBasket()
                mainActivity.basketService.updateTotalMenuNum()

            }


        }
    }
}