package com.example.grocessaryapp.activities

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.grocessaryapp.R
import com.example.grocessaryapp.app.Config
import com.example.grocessaryapp.database.DbHelper
import com.example.grocessaryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {
    var product: Product? = null
    lateinit var dbHelper: DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        dbHelper = DbHelper(this)

        product = intent.getSerializableExtra(Product.KEY_PRODUCT) as Product

        text_view_product_title.text = product?.productName
        text_view_product_mrp.text = product?.mrp.toString()
        text_view_product_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        text_view_product_desc.text = product?.description
        text_view_product_price.text = "$"+product?.price.toString()
        text_view_product_quantity.text = product?.quantity.toString()

        Picasso.get().load(Config.IMAGE_URL+ product!!.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(product_image)

        init()
    }

    private fun init(){
        button_add_cart.setOnClickListener{
            var name = product?.productName
            var price = product?.price
            var mrp = product?.mrp
            var imageurl = product?.image
            var quantity = product?.quantity
            var id = product?._id

            var product = Product(_id = id, productName = name, price = price, mrp = mrp, image = imageurl, quantity = quantity)

            dbHelper.addItemInCart(product)

            var intent = Intent(this,CartActivity::class.java)
            startActivity(intent)


        }
    }
}
