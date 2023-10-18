package com.jimd.harrypotterapi.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jimd.harrypotterapi.R
import com.jimd.harrypotterapi.databinding.ActivityStudentBinding
import com.jimd.harrypotterapi.ui.HPViewModel
import com.jimd.harrypotterapi.utils.StudentUiState
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StudentActivity : AppCompatActivity() {
    lateinit var binding:ActivityStudentBinding
    val viewModel: HPViewModel by viewModels()
    var id=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        id = bundle?.getString("id").toString()

        ini()
    }
    fun ini(){
        viewModel.getStudent(id)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.student.collect{
                    when(it){
                        is StudentUiState.failure -> {
                            Log.i("HARRYTT"," Mensaje: ${it.error.message} - Causa : ${it.error.cause}")
                        }
                        StudentUiState.loading -> {
                            binding.progressBarStudent.isVisible = true
                        }
                        is StudentUiState.success -> {
                            Log.i("HARRYTT"," SUCCESS: ${it.student}")
                            if (it.student.image.isNullOrEmpty()){
                                Picasso.get().load(R.drawable.reliquias).placeholder(R.drawable.reliquias).into(binding.imgStudent)
                            }else{
                                Picasso.get().load(it.student.image).placeholder(R.drawable.reliquias).into(binding.imgStudent)
                            }
                            binding.txtNombreStudent.text = it.student.name
                            binding.txtAnsestroStudent.text = it.student.ancestry
                            binding.txtAnioNacimientoStudent.text = it.student.yearOfBirth.toString()
                            binding.txtFechaNacimientoStudent.text = it.student.dateOfBirth
                            binding.txtCasaStudent.text = it.student.house
                            binding.txtEspecieStudent.text=it.student.species
                            binding.txtGeneroStudent.text=it.student.gender
                            binding.txtColordeOjosStudent.text=it.student.eyeColour
                            binding.txtColordeCabelloStudent.text=it.student.hairColour
                            binding.txtNombreActorStudent.text=it.student.actor
                            if (it.student.alive==true){
                                binding.txtConVidaStudent.text="Con vida"
                            }else{
                                binding.txtConVidaStudent.text="Sin vida"
                            }
                            binding.progressBarStudent.isVisible = false
                        }
                    }
                }
            }
        }
    }

}