package com.neowise.androidlessions.itmath.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.neowise.androidlessions.itmath.databinding.ActivityQuestionsBinding
import com.neowise.androidlessions.itmath.model.UserResult
import com.neowise.androidlessions.itmath.viewmodel.QuestionsViewModel
import com.neowise.androidlessions.itmath.viewmodel.QuestionsViewModelFactory

class QuestionsActivity : AppCompatActivity() {

    private lateinit var viewModel: QuestionsViewModel
    private lateinit var binding: ActivityQuestionsBinding
    private lateinit var variantGroup: VariantButtonGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val maxProblems = intent.getIntExtra(EXTRA_DIFFICULT, 1) * 10
        viewModel = ViewModelProvider(this, QuestionsViewModelFactory(maxProblems)).get()

        initName()
        initButtons()

        viewModel.progress.observe(this) { progress ->
            binding.progressBar.setProgress(progress.current * 10, true)
            binding.step.text = "${progress.current} из ${progress.max}"
        }

        viewModel.result.observe(this) { result ->
            showResults(result)
        }

        viewModel.problem.observe(this) { problem ->
            binding.math.text = "$problem?"
            variantGroup.clear()
            variantGroup.initVariants(problem.variants)
        }

        viewModel.nextProblem()
    }

    private fun initName() {
        val name = intent.getStringExtra(EXTRA_NAME)!!
        binding.question.text = "$name, сколько будет"
    }

    private fun initButtons() {
        variantGroup = VariantButtonGroup { _, variant ->
            viewModel.selectedVariant(variant)
        }

        variantGroup += binding.button1
        variantGroup += binding.button2
        variantGroup += binding.button3
        variantGroup += binding.button4
        variantGroup += binding.button5
        variantGroup += binding.button6
        variantGroup += binding.button7
        variantGroup += binding.button8
        variantGroup += binding.button9

        binding.answerButton.setOnClickListener {
            viewModel.answered()
        }
        binding.skipButton.setOnClickListener {
            viewModel.skipped()
        }
    }


    private fun showResults(userResult: UserResult) {
        val intent = Intent(this, ResultsActivity::class.java).apply {
            putExtra(EXTRA_RESULT, userResult)
        }
        startActivity(intent)
        finish()
    }
}
