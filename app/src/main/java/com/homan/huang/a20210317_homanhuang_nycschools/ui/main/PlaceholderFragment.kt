package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.background.helper.lgd
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A placeholder fragment scooh information by tab title
 */
@AndroidEntryPoint
class PlaceholderFragment : Fragment() {

//    private val pageViewModel: PageViewModel by activityViewModels()
    private val pageViewModel: PageViewModel by viewModels()

    // databinding
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    // recyclerView clickListener
    private val schoolListener = SchoolClickListener {
        lgd("You click on dbn: $it")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

//        val textView: TextView = binding.sectionLabel
        val recView: RecyclerView = binding.recyclerView
//        val pageNum = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

//        recView.visibility = View.GONE

        // preset data
        pageViewModel.apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }

        // observer
        pageViewModel.text.observe(viewLifecycleOwner, {
            binding.sectionLabel.text = it
        })
//        binding.sectionLabel.visibility = View.GONE

        pageViewModel.schoollist.observe(viewLifecycleOwner,  {
            val mText = binding.sectionLabel.text
            val mSec = mText.last() //find the section#

            lgd("Section # at ${mSec}")
            if (it.size > 0) {
                var mList = listOf<School>()

                // divide school list into sections
                when (mSec) {
                    '1'-> { // ABCDE
                        mList = it.sortedBy { it.schoolName }
                            .filter { it.schoolName[0] < 'F' }
                    }
                    '2'-> { // FGHIJ
                        mList = it.sortedBy { it.schoolName }
                            .filter {  'E' < it.schoolName[0] && it.schoolName[0] < 'K'}
                    }
                    '3'-> { // KLMNO
                        mList = it.sortedBy { it.schoolName }
                            .filter {  'J' < it.schoolName[0] && it.schoolName[0] < 'P'}
                    }
                    '4'-> { // PQRST
                        mList = it.sortedBy { it.schoolName }
                            .filter {  'O' < it.schoolName[0] && it.schoolName[0] < 'U'}
                    }
                    else -> { // U-Z0-9
                        mList = it.sortedBy { it.schoolName }
                            .filter { it.schoolName[0] > 'T'}
                    }
                }
                recView.adapter = SchoolItemAdapter(mList, schoolListener)
            }

        })
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
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}