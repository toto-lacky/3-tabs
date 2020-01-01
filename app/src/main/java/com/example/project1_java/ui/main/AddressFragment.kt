package com.example.project1_java.ui.main

import android.Manifest
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1_java.Addr_Profile
import com.example.project1_java.AddressAdapter
import com.example.project1_java.MainActivity
import com.example.project1_java.R
import kotlinx.android.synthetic.main.fragment_address.*

/**
 * A fragment containing a view for contacts.
 */

class AddressFragment : Fragment() {

    var addrList: ArrayList<Addr_Profile?>? = ArrayList()

    /* 기기의 연락처 데이터를 불러와서 AddressAdapter에 연결
    *  onCreateView 이후에 실행하기 위해 onStrat로 옮겨옴*/
    @ExperimentalStdlibApi
    override fun onStart(){
        super.onStart()
        addrList = getContactList()
        // 전화를 거는 부분
        val mAdapter = AddressAdapter(requireContext(), addrList) { prof ->
            call(prof)
        }

        mRecyclerView.adapter = mAdapter

        val lm = LinearLayoutManager(requireContext())
        mRecyclerView.layoutManager = lm
        mRecyclerView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_address, container, false)

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): AddressFragment {
            return AddressFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    /* 기기로부터 연락처 목록을 가져오는 메소드 */
    @ExperimentalStdlibApi
    fun getContactList(): ArrayList<Addr_Profile?>? {
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.Contacts.PHOTO_ID,
            ContactsContract.Contacts._ID
        )
        val selectionArgs: Array<String>? = null
        val sortOrder = (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC")
        val cursor: Cursor? = requireContext().contentResolver.query(uri, projection, null, selectionArgs, sortOrder)
        val hashlist =
            LinkedHashSet<Addr_Profile>()
        if (cursor?.moveToFirst() == true) {
            do {
                val name = cursor.getString(0)
                val photo_id = cursor.getLong(2)
                val addr = cursor.getString(1)
                val person_id = cursor.getLong(3)
                val newProfile = Addr_Profile(photo_id, person_id, addr, name)
                hashlist.add(newProfile)
                //Log.d("dalfkj", "ldakfj" + cursor.count)
            } while (cursor.moveToNext())
        }
        val contactItems: ArrayList<Addr_Profile?> = ArrayList(hashlist)
        for (i in contactItems.indices) {
            contactItems[i]?.id = i
        }
        cursor?.close()
        return contactItems
    }

    /* prof에서 읽은 번호로 전화를 거는 함수 */
    fun call(prof: Addr_Profile){
        val callIntent : Intent = Intent(Intent.ACTION_CALL)
        callIntent.setData(Uri.parse("tel:${prof.addr}"))
        val perm = Array(1) {Manifest.permission.CALL_PHONE}
        MainActivity.setPermission(requireContext(), perm)
        startActivity(callIntent)
        //Log.d("phone number",prof.addr)
    }

    /* 입력받은 데이터로 새 연락처를 만드는 함수 */
    fun createAddr(name: String, addr: String){
    }

}
