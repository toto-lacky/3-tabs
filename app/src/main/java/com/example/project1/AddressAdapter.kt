package com.example.project1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AddressAdapter(val context: Context, val addrList: ArrayList<Addr_Profile>) :
RecyclerView.Adapter<AddressAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_address_block, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return addrList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(addrList[position], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Photo = itemView?.findViewById<ImageView>(R.id.elem_icon)
        val Name = itemView?.findViewById<TextView>(R.id.elem_name)
        val Addr = itemView?.findViewById<TextView>(R.id.elem_addr)

        fun bind (prof: Addr_Profile, context: Context) {
            /* 이미지의 id를 파일명(String)으로 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
            if (prof.photo != "") {
                val resourceId = context.resources.getIdentifier(prof.photo, "drawable", context.packageName)
                Photo?.setImageResource(resourceId)
            } else {
                Photo?.setImageResource(R.mipmap.ic_launcher)
            }
            /* 나머지 TextView와 String 데이터를 연결한다. */
            Name?.text = prof.name
            Addr?.text = prof.addr
        }
    }
}