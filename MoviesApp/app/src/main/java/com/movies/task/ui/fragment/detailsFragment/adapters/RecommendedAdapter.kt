package com.movies.task.ui.fragment.detailsFragment.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.movies.task.BuildConfig
import com.movies.task.R
import com.movies.task.common.loadImage
import com.movies.task.data.model.detailsModel.RecommendedMovie
import kotlinx.android.synthetic.main.cell_item.view.*
import java.util.*

class RecommendedAdapter(private val recommendedList: ArrayList<RecommendedMovie>) : RecyclerView.Adapter<RecommendedAdapter.ViewHolder>(){
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cell_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecommendedAdapter.ViewHolder, position: Int) {
        holder.bindItems(recommendedList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return recommendedList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: RecommendedMovie) {
            itemView.castName.text=user.title
            itemView.castImageView.loadImage(BuildConfig.IMAGE_BASE_URL+user.poster_path)
        }
    }
}
