package com.example.grocessaryapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocessaryapp.R
import com.example.grocessaryapp.activities.SubCategoryActivity
import com.example.grocessaryapp.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_category_adapter.view.*

class CategoryAdapter (var mContext: Context): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    var mList: ArrayList<Category> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cat: Category) {

            itemView.text_view_title.text = cat.catName
            itemView.text_view_desc.text = cat.catDescription

            Picasso
                .get()
                .load("http://rjtmobile.com/grocery/images/"+cat.catImage)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.image_view)

            itemView.card_one.setOnClickListener{
                var intent = Intent(mContext, SubCategoryActivity::class.java)
                intent.putExtra("CATID",cat.catId)
                mContext.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.row_category_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var gallery = mList[position]
        holder.bind(gallery)
    }

    override fun getItemCount(): Int {
        return mList.size


    }

    fun setData(list: ArrayList<Category>) {
        mList = list
        notifyDataSetChanged()
    }
}