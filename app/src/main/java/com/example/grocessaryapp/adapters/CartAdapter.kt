package com.example.grocessaryapp.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocessaryapp.R
import com.example.grocessaryapp.app.Config
import com.example.grocessaryapp.models.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_cart_design.view.*

class CartAdapter(var mContext: Context): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var mList : ArrayList<Product> = ArrayList()
    var onFragmentInteraction: OnFragmentInteraction? = null

    interface OnFragmentInteraction{
        fun deleteItem(product: Product, view: View, position : Int)
        fun addItem(product: Product, view: View)
        fun minusItem(product: Product, view: View)
    }

    fun setFragmentInteraction(onFragmentInteraction: OnFragmentInteraction){
        this.onFragmentInteraction = onFragmentInteraction
    }

    inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        fun bind(product: Product, position: Int){
            itemView.text_view_title.text = product.productName
            itemView.text_view_price.text = "$"+product.price.toString()
            itemView.text_view_quantity.text = product.quantity.toString()
            itemView.text_view_mrp.text = "$"+product.mrp.toString()
            itemView.text_view_mrp.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

            Picasso.get()
                .load(Config.IMAGE_URL+product.image)
                .placeholder(R.drawable.ic_baseline_local_grocery_store_24)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.image_view_cart)

            itemView.text_view_add.setOnClickListener {
                onFragmentInteraction?.addItem(product, it)
            }

            itemView.text_view_sub.setOnClickListener {
                onFragmentInteraction?.minusItem(product, it)
            }

            itemView.button_delete.setOnClickListener{
                onFragmentInteraction?.deleteItem(product, it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_cart_design,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var product = mList[position]
        holder.bind(product, position)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list : ArrayList<Product>){
        mList = list
        notifyDataSetChanged()
    }
}
