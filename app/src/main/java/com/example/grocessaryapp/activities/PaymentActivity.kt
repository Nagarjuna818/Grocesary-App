package com.example.grocessaryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grocessaryapp.OrderSummary
import com.example.grocessaryapp.R
import com.example.grocessaryapp.database.DbHelper
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    lateinit var dbHelper: DbHelper
    var order_summary: OrderSummary? = null
    val userId = "62b1a9c35f652a0017b0fc20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        dbHelper = DbHelper(this)
        order_summary = dbHelper.getOrderSummary()
        supportActionBar?.hide()
        init()
    }
    private fun init(){
        dbHelper.getOrderSummary()
        text_view_description.text = "Total Amount:"+"$"+order_summary?.totalMrp.toString()+"\n"+
                "Delivery :"+"$"+order_summary?.deliveryCharges +"\n"+
                "Discount :"+"$"+order_summary?.discount +"\n"+
                "Total :"+"$"+order_summary?.orderAmount


        text_view_payable_amount.text = "Total Payable Amount :"+"$"+order_summary?.orderAmount
        button_order.setOnClickListener{
            var intent = Intent(this,OrderConfirmActivity::class.java)
            startActivity(intent)
        }
    }
}