package com.example.grocessaryapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocessaryapp.R
import com.example.grocessaryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row_design.view.*

class SliderAdapter (var mContext: Context): RecyclerView.Adapter<SliderAdapter.ViewHolder>()  {

    var mList: ArrayList<Product> = ArrayList()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {

            Picasso
                .get().load("http://rjtmobile.com/grocery/images/"+product.image)
                .placeholder(R.drawable.ic_baseline_local_grocery_store_24)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.image_view)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.slider_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var slider = mList[position]
        holder.bind(slider)
    }

    override fun getItemCount(): Int {
        return mList.size


    }

    fun setData(list: ArrayList<Product>) {
        mList = list
        notifyDataSetChanged()
    }
}