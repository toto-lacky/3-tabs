package com.example.project1_java

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_address_block.view.*
import java.io.InputStream

class AddressAdapter(val context: Context, val addrList: ArrayList<Addr_Profile?>?, val itemClick: (Addr_Profile) -> Unit) :
        RecyclerView.Adapter<AddressAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_address_block, parent, false)
        return Holder(view, itemClick)
    }

    override fun getItemCount(): Int {
        if(addrList == null)
            return -1
        return addrList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val listElem : Addr_Profile? = addrList?.get(position)
        if(listElem != null)
            holder?.bind(listElem, context)
    }

    inner class Holder(itemView: View, itemClick: (Addr_Profile) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val Photo = itemView?.findViewById<ImageView>(R.id.elem_icon)
        val Name = itemView?.findViewById<TextView>(R.id.elem_name)
        val Addr = itemView?.findViewById<TextView>(R.id.elem_addr)

        fun bind (prof: Addr_Profile, context: Context) {
            /* 이미지의 id를 찾고,
            이미지가 없는 경우 기본 아이콘을 표시한다.*/
            if (prof.photoid != 0.toLong()) {
                val photoid = prof.photoid
                val personid = prof.personId
                Photo?.setImageBitmap(loadContactPhoto(context.contentResolver, personid, photoid))
            } else {
                Photo?.setImageResource(R.drawable.def_icon)
            }
            /* 나머지 TextView와 String 데이터를 연결한다. */
            Name?.text = prof.name
            Addr?.text = prof.addr

            itemView.call_icon.setOnClickListener{itemClick(prof)}
        }
    }

    /* 이미지 파일을 불러오는 메소드 */
    fun loadContactPhoto(cr : ContentResolver, id : Long, photo_id : Long) : Bitmap? {
        val uri : Uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id)
        val input : InputStream? = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri)
        if (input != null)
            return Util.resizingBitmap(BitmapFactory.decodeStream(input))
        var photoBytes : ByteArray? = null
        val photoUri : Uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, photo_id)
        val c : Cursor? = cr.query(photoUri, arrayOf(ContactsContract.CommonDataKinds.Photo.PHOTO),null,null,null)

        if(c?.moveToFirst() == true)
            photoBytes = c.getBlob(0)
        c?.close()

        if (photoBytes != null)
            return Util.resizingBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size))
        return null
    }
}