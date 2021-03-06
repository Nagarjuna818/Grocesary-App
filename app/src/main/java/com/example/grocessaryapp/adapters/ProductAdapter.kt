package com.example.grocessaryapp.adapters

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.grocessaryapp.R
import com.example.grocessaryapp.activities.ProductDetailActivity
import com.example.grocessaryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row_design.view.*

class ProductAdapter (var mContext: FragmentActivity?): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    var mList: ArrayList<Product> = ArrayList()

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){

        fun bind(product: Product){

            itemView.text_view_title.text = product.productName
            itemView.text_view_price.text = "$"+product.price.toString()
            itemView.text_view_mrp.text = "$"+product.mrp.toString()
            itemView.text_view_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            Picasso
                .get().load("http://rjtmobile.com/grocery/images/"+product.image)
                .placeholder(R.drawable.ic_baseline_local_grocery_store_24)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.image_view)

            itemView.card_view.setOnClickListener {
                var intent = Intent(mContext, ProductDetailActivity::class.java)
                intent.putExtra(Product.KEY_PRODUCT,product)
                mContext?.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.single_row_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }
}