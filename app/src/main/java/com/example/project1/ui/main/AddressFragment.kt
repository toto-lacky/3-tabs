package com.example.project1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project1.Addr_Profile
import com.example.project1.AddressAdapter
import com.example.project1.R
import kotlinx.android.synthetic.main.fragment_address.*

/**
 * A placeholder fragment containing a simple view.
 */
class AddressFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    var addrList = arrayListOf<Addr_Profile>(
        Addr_Profile("def_icon","hihi","ieeeeeng"),
        Addr_Profile("def_icon","yo","aaaaa"),
        Addr_Profile("def_icon","heee","dfsadag"),
        Addr_Profile("def_icon","hohoho","madcamp")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        //setContentView(savedInstanceState)


    }

    override fun onStart(){
        super.onStart()
        val mAdapter = AddressAdapter(requireContext(), addrList)
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
}
