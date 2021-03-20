package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.background.helper.lgd
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.sectionLabel
        val recView: RecyclerView = binding.recyclerView
        val pageNum = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

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

            lgd("page #: $pageNum at ${mText.last()}")
            if (it.size > 0) {
                val mList = it.sortedBy { it.schoolName }
                recView.adapter = SchoolItemAdapter(mList)
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
        private const val ARG_TITLE = "section_title"

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