package com.example.grocessaryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocessaryapp.R
import com.example.grocessaryapp.adapters.CategoryAdapter
import com.example.grocessaryapp.adapters.SliderAdapter
import com.example.grocessaryapp.models.Category
import com.example.grocessaryapp.models.CategoryResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sliderAdapter: SliderAdapter
    lateinit var catAdapter: CategoryAdapter
    var mList : ArrayList<Category> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()


    }

    private fun init(){

        getData()
        catAdapter = CategoryAdapter(this)
        recycler_view.adapter = catAdapter
        recycler_view.layoutManager = GridLayoutManager(this,2)

//        button_user.setOnClickListener{
//            var intent = Intent(applicationContext,LoginActivity::class.java)
//            startActivity(intent)
//        }
        button_cart.setOnClickListener{
            var intent = Intent(this,CartActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            "http://apolis-grocery.herokuapp.com/api/category",
            {
                progress_bar.visibility = View.GONE

                var gson = Gson()
                var categoryResponse = gson.fromJson(it, CategoryResponse::class.java)

                catAdapter.setData(categoryResponse.data!!)

            },
            {
                progress_bar.visibility = View.GONE
                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
            }
        )
        requestQueue.add(request)
    }
}
