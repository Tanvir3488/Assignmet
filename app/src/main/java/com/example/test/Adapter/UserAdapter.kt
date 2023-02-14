package com.example.test.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.table.User
import com.example.test.databinding.RowUserBinding


/**
 * Created by Tanvir3488 on 2/14/2023.
 */


class UserAdapter (
    val user_list:ArrayList<User>,
    private val clickListener: (User) -> Unit
): RecyclerView.Adapter<UserHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val  layoutInflater = LayoutInflater.from(parent.context)
        val binding:RowUserBinding = RowUserBinding.inflate(
            layoutInflater,
            parent,false
        )

        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(user_list[position],clickListener)


    }


    override fun getItemCount(): Int {
        return user_list.size
    }


}




class UserHolder(val binding: RowUserBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(
        user_item: User,
        clickListener: (User) -> Unit,
    ){


      binding.user=user_item
        binding.root.setOnClickListener {

            clickListener(user_item)
        }




    }



}
