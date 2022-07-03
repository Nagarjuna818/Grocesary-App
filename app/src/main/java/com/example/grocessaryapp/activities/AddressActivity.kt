package com.example.grocessaryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocessaryapp.OrderSummary
import com.example.grocessaryapp.R
import com.example.grocessaryapp.adapters.AddressAdapter
import com.example.grocessaryapp.app.Endpoints
import com.example.grocessaryapp.database.DbHelper
import com.example.grocessaryapp.models.AddressResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_address.*

class AddressActivity : AppCompatActivity() {
    lateinit var addressAdapter: AddressAdapter
    lateinit var dbHelper: DbHelper

    var order_summary: OrderSummary? = null
    val userId = "62b1a9c35f652a0017b0fc20"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        dbHelper = DbHelper(this)
        order_summary = dbHelper.getOrderSummary()
        supportActionBar?.hide()

        init()

    }

    private fun init() {

        dbHelper.getOrderSummary()
        text_view_price_of_items.text = "Total Payable Amount:"+"$"+order_summary?.totalMrp.toString()
        text_view_delivery.text = "Delivery :"+"$"+order_summary?.deliveryCharges
        text_view_discount.text = "Discount :"+"$"+order_summary?.discount
        text_view_total.text = "Total :"+"$"+order_summary?.orderAmount

        getData()
        button_address_add.setOnClickListener {
            startActivity(Intent(this, AddAddressActivity::class.java))
        }

        addressAdapter = AddressAdapter(this)
        recycler_view.adapter = addressAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)


    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getAddressByUserId(userId),
            Response.Listener {
                var gson = Gson()
                var addressResponse = gson.fromJson(it, AddressResponse::class.java)
                addressAdapter.setData(addressResponse.data)
            },
            Response.ErrorListener {

            }
        )
        requestQueue.add(request)
    }
}