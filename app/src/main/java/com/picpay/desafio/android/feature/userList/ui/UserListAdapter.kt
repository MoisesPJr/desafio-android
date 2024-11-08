package com.picpay.desafio.android.feature.userList.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.feature.userList.domain.model.UserDomain
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class UserListAdapter : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {

    var users = emptyList<UserDomain>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: UserDomain = users[position]

        holder.name.text = user.name ?: "Nome indisponível"

        holder.username.text = user.username ?: "Usuário desconhecido"

        holder.progressBar.visibility = View.VISIBLE

        if (user.img != null) {
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(holder.picture, object : Callback {
                    override fun onSuccess() {
                        holder.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        holder.progressBar.visibility = View.GONE
                    }
                })

        } else {
            holder.picture.setImageResource(R.drawable.ic_round_account_circle)
            holder.progressBar.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = users.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var username: TextView = itemView.findViewById(R.id.username)
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
        var picture: ImageView = itemView.findViewById(R.id.picture)
    }


}