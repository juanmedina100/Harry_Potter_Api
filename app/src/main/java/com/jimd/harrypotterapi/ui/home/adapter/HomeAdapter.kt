package com.jimd.harrypotterapi.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jimd.harrypotterapi.R
import com.jimd.harrypotterapi.domain.model.HPCharacterModel

class HomeAdapter(private val lista:List<HPCharacterModel>,
    private val onStudent:(HPCharacterModel)->Unit):RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_home,parent,false)
        return HomeViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = lista[position]
        holder.render(item,onStudent)
    }
}