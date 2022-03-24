package org.sjhstudio.viewmodelexample.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sjhstudio.viewmodelexample.R
import org.sjhstudio.viewmodelexample.databinding.ItemFollowListBinding
import org.sjhstudio.viewmodelexample.model.FollowingData
import org.sjhstudio.viewmodelexample.viewmodel.FollowViewModel

interface OnFollowListCallback {
    fun onFollowing(pos: Int)
    fun onUnFollowing(pos: Int)
}

class FollowListAdapter(vm: FollowViewModel): RecyclerView.Adapter<FollowListAdapter.ViewHolder>() {

    var items = vm.following.value
    private var callback: OnFollowListCallback? = null

    fun setOnFollowListCallback(callback: OnFollowListCallback) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFollowListBinding.bind(itemView)

        init {
            binding.followBtn.setOnClickListener {
                if(binding.followBtn.text == "팔로잉") {   // 언팔로우
                    callback?.onUnFollowing(adapterPosition)
                    setFollowingBtn(false)
                } else {    // 팔로우
                    callback?.onFollowing(adapterPosition)
                    setFollowingBtn(true)
                }
            }
        }

        fun setBind(data: FollowingData) {
            binding.idTv.text = data.id
            binding.nameTv.text = data.name
            if(data.unFollowing) setFollowingBtn(false)
            else setFollowingBtn(true)
        }

        private fun setFollowingBtn(isFollowing: Boolean) {
            if(isFollowing) {
                binding.followBtn.text = "팔로잉"
                binding.followBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.followBtn.setTextColor(Color.parseColor("#000000"))
            } else {
                binding.followBtn.text = "팔로우"
                binding.followBtn.setBackgroundColor(Color.parseColor("#2196F3"))
                binding.followBtn.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_follow_list, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items?.let {
            holder.setBind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}