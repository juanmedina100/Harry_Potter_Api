package com.jimd.harrypotterapi.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.jimd.harrypotterapi.databinding.ActivityMainBinding
import com.jimd.harrypotterapi.domain.model.HPCharacterModel
import com.jimd.harrypotterapi.ui.HPViewModel
import com.jimd.harrypotterapi.ui.student.StudentActivity
import com.jimd.harrypotterapi.ui.home.adapter.HomeAdapter
import com.jimd.harrypotterapi.utils.HomeUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    val viewModel:HPViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

    }
    fun init(){
        viewModel.getAllStudent()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.character.collect{chapter->
                    when(chapter){
                        is HomeUiState.failure -> {
                            Alerta()
                            binding.progressBarHome.isVisible = false
                        }
                        HomeUiState.loading -> {
                            binding.progressBarHome.isVisible = true
                        }
                        is HomeUiState.success -> {
                            binding.RVHome.layoutManager = LinearLayoutManager(this@MainActivity)
                            binding.RVHome.adapter = HomeAdapter(chapter.charactersList,{onStudent(it)})
                            binding.progressBarHome.isVisible = false
                        }
                    }
                }
            }
        }
    }
    fun onStudent(hpCharacterModel: HPCharacterModel){
        val student = Intent(this, StudentActivity::class.java)
        student.putExtra("id",hpCharacterModel.id)
        startActivity(student)
    }
    fun Alerta(){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("ERROR DE CARGA")
        alert.setMessage("No es posible tener la información desde el servidor HP-API")
        alert.setPositiveButton("CERRAR",null)
        alert.show()
    }
}