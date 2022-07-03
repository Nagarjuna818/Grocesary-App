package com.example.grocessaryapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.grocessaryapp.R
import com.example.grocessaryapp.activities.PaymentActivity
import com.example.grocessaryapp.models.Address
import kotlinx.android.synthetic.main.row_address_adapter.view.*

class AddressAdapter(var mContext: Context): RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    var mList : ArrayList<Address> = ArrayList()

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(address: Address){

            itemView.text_view_address.text = "${address.houseNo}, ${address.location}"
            itemView.text_view_streetname.text = address.streetName
            itemView.text_view_city.text = address.city
            itemView.text_view_pincode.text = address.pincode.toString()

            itemView.setOnClickListener {
                var intent = Intent(mContext, PaymentActivity::class.java)
                intent.putExtra("address1","${address.houseNo}, ${address.location}")
                intent.putExtra("address2", address.streetName,)
                intent.putExtra("address3",address.city,)
                intent.putExtra("address4",address.pincode.toString())
                mContext.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_address_adapter,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var address = mList[position]
        holder.bind(address)
    }

    override fun getItemCount(): Int {

        return mList.size
    }

    fun setData(list: ArrayList<Address>){
        mList = list
        notifyDataSetChanged()
    }
}
