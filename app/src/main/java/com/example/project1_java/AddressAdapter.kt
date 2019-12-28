package com.example.project1_java

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream

class AddressAdapter(val context: Context, val addrList: ArrayList<Addr_Profile?>?) :
            RecyclerView.Adapter<AddressAdapter.Holder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_address_block, parent, false)
                return Holder(view)
    }

    override fun getItemCount(): Int {
        if(addrList == null)
            return -1
        return addrList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(addrList?.get(position), context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Photo = itemView?.findViewById<ImageView>(R.id.elem_icon)
        val Name = itemView?.findViewById<TextView>(R.id.elem_name)
        val Addr = itemView?.findViewById<TextView>(R.id.elem_addr)

        fun bind (prof: Addr_Profile?, context: Context) {
            /* 이미지의 id를 찾고,
            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
            if (prof?.photoid != R.drawable.def_icon.toLong() && prof?.photoid != null) {
                val photoid = prof.photoid
                val personid = prof.personId
                Photo?.setImageBitmap(loadContactPhoto(context.contentResolver, personid, photoid))
                //Photo?.setImageResource(resourceId.toInt())
            } else {
                Photo?.setImageResource(R.drawable.def_icon)
            }
            /* 나머지 TextView와 String 데이터를 연결한다. */
            Name?.text = prof?.name
            Addr?.text = prof?.addr
        }
    }

    /* 이미지 파일을 불러오는 메소드 */
    fun loadContactPhoto(cr : ContentResolver, id : Long, photo_id : Long) : Bitmap? {
        val uri : Uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id)
        val input : InputStream? = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri)
        if (input != null)
            return resizingBitmap(BitmapFactory.decodeStream(input))
        else
            Log.d("PHOTO","first try failed to load photo")
        var photoBytes : ByteArray? = null
        val photoUri : Uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, photo_id)
        val c : Cursor? = cr.query(photoUri, arrayOf(ContactsContract.CommonDataKinds.Photo.PHOTO),null,null,null)

        if(c?.moveToFirst() == true)
            photoBytes = c.getBlob(0)
        c?.close()

        if (photoBytes != null)
            return resizingBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.size))
        else
            Log.d("PHOTO", "second try also failed")
        return null
    }

    /* 이미지 파일을 resizing하는 메소드 */
    fun resizingBitmap(oBitmap: Bitmap) : Bitmap? {
        if (oBitmap == null)
            return null
        var width: Int = oBitmap.getWidth()
        var height: Int = oBitmap.getHeight()
        val resizing_size = 120
        var rBitmap : Bitmap? = null
        if (width > resizing_size) {
            val mWidth : Int = width / 100
            val fScale : Int = resizing_size / mWidth
            width *= (fScale / 100)
            height *= (fScale / 100)
        } else if (height > resizing_size) {
            val mHeight: Int = height / 100
            val fScale : Int = resizing_size / mHeight
            width *= (fScale / 100)
            height *= (fScale / 100)
        }
        Log.d("BitmapLog","rBitmap : " + width + ", " + height)
        rBitmap = Bitmap.createScaledBitmap(oBitmap, width, height, true)
        return rBitmap
    }
}