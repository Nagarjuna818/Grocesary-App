package com.example.grocessaryapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.grocessaryapp.R
import com.example.grocessaryapp.adapters.MyFragmentAdapter
import com.example.grocessaryapp.app.Endpoints
import com.example.grocessaryapp.models.SubCatResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sub_category.*

class SubCategoryActivity : AppCompatActivity() {

    lateinit var myFragmentAdapter: MyFragmentAdapter

    var cat_id = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_category)

        cat_id = intent.getIntExtra("CATID",1)

        supportActionBar?.setTitle("Sub Category")

        init()
    }

    private fun init()
    {
        getData()
        myFragmentAdapter = MyFragmentAdapter(supportFragmentManager)
    }

    private fun getData() {
        var requestQueue = Volley.newRequestQueue(this)
        var request = StringRequest(
            Request.Method.GET,
            Endpoints.getSubCategoryByCatId(cat_id),
            {
                var gson = Gson()
                var subCategoryResponse = gson.fromJson(it, SubCatResponse::class.java)
                for(i in 0 until subCategoryResponse.data.size){
                    myFragmentAdapter.addFragment(subCategoryResponse.data[i])
                }
                view_pager.adapter = myFragmentAdapter
                tab_layout.setupWithViewPager(view_pager)
            },
            {

            }
        )
        requestQueue.add(request)
    }


}