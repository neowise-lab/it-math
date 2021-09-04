package com.neowise.androidlessions.itmath.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neowise.androidlessions.itmath.databinding.ActivityResultsBinding
import com.neowise.androidlessions.itmath.model.UserResult

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.retryButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        intent?.getParcelableExtra<UserResult>(EXTRA_RESULT)?.let { result ->

            binding.rating.text = "${result.rights} из 10"
            binding.right.text = "${result.rights} правильно"
            binding.skiped.text = "${result.skipped} пропущено"
            binding.wrong.text = "${result.wrong} неправильно"

            binding.message.text = when {
                result.rights > 8 -> "Нормально :)"
                result.rights > 5 -> "Что-то может получиться"
                result.rights > 0 -> "Печально, что ты тупой :("
                else -> "Диагноз - тебе 0 лет"
            }
        }
    }
}