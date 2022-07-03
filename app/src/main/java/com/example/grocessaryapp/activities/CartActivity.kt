package com.example.grocessaryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.grocessaryapp.OrderSummary
import com.example.grocessaryapp.R
import com.example.grocessaryapp.adapters.CartAdapter
import com.example.grocessaryapp.database.DbHelper
import com.example.grocessaryapp.models.Product
import kotlinx.android.synthetic.main.activity_cart.*

class CartActivity : AppCompatActivity(), CartAdapter.OnFragmentInteraction {

    lateinit var addToCartAdapter: CartAdapter
    lateinit var dbHelper: DbHelper

    var mList: ArrayList<Product> = ArrayList()
    var order_summary: OrderSummary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.hide()
        dbHelper = DbHelper(this)
        addToCartAdapter = CartAdapter(this)

        mList = dbHelper.getALlItemPresentInCart()
        init()
    }

    private fun init(){
        updateUI()


        addToCartAdapter.setFragmentInteraction(this)
        recycler_view.adapter = addToCartAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)
        addToCartAdapter.setData(mList)

        button_next.setOnClickListener{
            Toast.makeText(this@CartActivity,"Clicked", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@CartActivity,AddressActivity::class.java))
        }
    }

    private fun updateUI(){
        mList = dbHelper.getALlItemPresentInCart()
        addToCartAdapter.notifyDataSetChanged()
        order_summary = dbHelper.getOrderSummary()
        text_view_number_of_items.text = order_summary?.totalItems.toString()
        text_view_price_of_items.text = "$"+order_summary?.totalPrice.toString()
    }

    override fun deleteItem(product: Product, view: View, position:Int) {
        Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
        dbHelper.deleteItemFromCart(product._id!!)
        mList.removeAt(position)
        addToCartAdapter.notifyItemRemoved(position)
        updateUI()
    }

    override fun addItem(product: Product, view: View) {
        Toast.makeText(this, "added: "+ product.quantity, Toast.LENGTH_SHORT).show()
        var qty: Int = product.quantity!! + 1
        product.quantity = qty
        dbHelper.updateQuantityOfItem(product)
        updateUI()

    }

    override fun minusItem(product: Product, view: View) {
        //Toast.makeText(this, "minus: "+ product.quantity, Toast.LENGTH_SHORT).show()
        var qty : Int = product.quantity!!
        if(qty > 1){
            //product.quantity!! - 1
            qty = qty - 1
            product.quantity = qty
            dbHelper.updateQuantityOfItem(product)
        }else{
            //dbHelper.deleteItemFromCart(product._id!!)
            Toast.makeText(this@CartActivity,"Item can't be less than 1",Toast.LENGTH_SHORT).show()
        }
        updateUI()
    }
}
