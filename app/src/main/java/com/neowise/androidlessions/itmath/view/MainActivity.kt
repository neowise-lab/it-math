package com.neowise.androidlessions.itmath.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.neowise.androidlessions.itmath.databinding.ActivityMainBinding
import com.neowise.androidlessions.itmath.utils.string

class MainActivity : AppCompatActivity() {
    
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener {
            val name = binding.nameEdit.string
            val difficult = binding.difficult.progress
            if (name.isNotEmpty()) {
                showQuestions(name, difficult)
            }
            else {
                Snackbar.make(binding.root, "Введите свое имя!", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun showQuestions(name: String, difficult: Int) {
        val intent = Intent(this, QuestionsActivity::class.java).apply {
            putExtra(EXTRA_NAME, name)
            putExtra(EXTRA_DIFFICULT, difficult)
        }
        startActivity(intent)
        finish()
    }
}