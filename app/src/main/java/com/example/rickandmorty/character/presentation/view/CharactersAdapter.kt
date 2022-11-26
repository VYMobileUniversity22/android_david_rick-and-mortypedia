package com.example.rickandmorty.character.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.R
import com.example.rickandmorty.character.domain.model.Character

/*
* Override
* public
* private
* */
class CharactersAdapter(
    private val data: MutableList<Character> = mutableListOf()
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val rootView: View = LayoutInflater.from(parent.context).inflate(R.layout.characters_adapter_row ,parent,false)
        return CharactersViewHolder(itemView = rootView)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bindData(data[position])
    }


    override fun getItemCount(): Int = data.size

    fun updateData(newData: List<Character>){
        data.clear()
        data.addAll(newData.toMutableList())
        notifyDataSetChanged()
    }

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imgProfile: ImageView by lazy { itemView.findViewById(R.id.img_profile) }
        private val tvName: TextView by lazy { itemView.findViewById(R.id.tv_name) }
        private val tvExtra: TextView by lazy { itemView.findViewById(R.id.tv_extras) }

        fun bindData(character: Character) {
            imgProfile.load(character.image)
            tvName.text = character.name
            tvExtra.text = "${character.species}\n${character.status}"

        }

    }

}