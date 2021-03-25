package com.extreme.zebra.view.adapters

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.extreme.zebra.NewsAppApplication
import com.extreme.zebra.R
import com.extreme.zebra.db.VideoDatabase
import com.extreme.zebra.model.NewsDataModel
import com.extreme.zebra.view.activities.VideoDetailsActivity


class NewsListAdapter(
    private val mContext: Context,
    private val videoList: ArrayList<NewsDataModel>
) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val videoName: TextView = itemView.findViewById(R.id.videoName)
        val videoDesc: TextView = itemView.findViewById(R.id.videoDesc)
        val videoImage: ImageView = itemView.findViewById(R.id.videoImage)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.video_list_itemview, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val video = videoList[position]
        if (!TextUtils.isEmpty(video.title))
            holder.videoName.text = video.title
        else
            holder.videoName.text = "NA"
        if (!TextUtils.isEmpty(video.description))
            holder.videoDesc.text = video.description
        else
            holder.videoDesc.text = "NA"
        Glide.with(NewsAppApplication.mInstance?.applicationContext!!)
            .load(video.imageHref)
            .into(holder.videoImage)

        holder.itemView.setOnClickListener {
            mContext.startActivity(
                Intent(mContext, VideoDetailsActivity::class.java)
                    .putExtra("VideoName", video.title)
            )
            InsertTask(mContext, video).execute()
//            insertData(video)
        }
    }

    private class InsertTask(val mContext: Context, val video: NewsDataModel) :
        AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?): Boolean {
            val chapterDatabase: VideoDatabase? = VideoDatabase.getDatabase(mContext)
            val arrayList = chapterDatabase?.videoDao()?.getHistoryVideo()!!
            if (arrayList.isNullOrEmpty() || !arrayList.contains(video)) {
                chapterDatabase?.videoDao()?.saveHistoryVideo(video)
            }
            return true
        }

        override fun onPostExecute(bool: Boolean?) {
            if (bool!!) {
                Toast.makeText(mContext, "Added to Database", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}