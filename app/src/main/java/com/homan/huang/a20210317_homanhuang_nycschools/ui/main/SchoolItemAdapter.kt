package com.homan.huang.a20210317_homanhuang_nycschools.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homan.huang.a20210317_homanhuang_nycschools.data.entity.School
import com.homan.huang.a20210317_homanhuang_nycschools.databinding.SchoolItemBinding


/*
    Display each school with title
 */
class SchoolItemAdapter(private val dataSet: List<School>) :
    RecyclerView.Adapter<SchoolItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: SchoolItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder =
        ViewHolder(
            SchoolItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    // Replace the contents of a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.schoolName.text = dataSet[position].toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
