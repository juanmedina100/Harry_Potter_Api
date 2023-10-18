package com.jimd.harrypotterapi.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jimd.harrypotterapi.R
import com.jimd.harrypotterapi.databinding.ItemHomeBinding
import com.jimd.harrypotterapi.domain.model.HPCharacterModel
import com.squareup.picasso.Picasso

class HomeViewHolder(itemView: View):ViewHolder(itemView) {
    val binding=ItemHomeBinding.bind(itemView)
    fun render(hpCharacterModel: HPCharacterModel,onStudent:(HPCharacterModel)->Unit){
        binding.txtNombreHome.text = hpCharacterModel.name
        binding.txtGeneroHome.text= hpCharacterModel.gender
        binding.txtEspecieHome.text = hpCharacterModel.species
        if (hpCharacterModel.image.isNullOrEmpty()){
            Picasso.get().load(R.drawable.reliquias)
                .into(binding.imgHome)
        }else {
            Picasso.get().load(hpCharacterModel.image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background).into(binding.imgHome)
        }
        itemView.setOnClickListener { onStudent(hpCharacterModel) }
    }
}