package com.neowise.androidlessions.itmath.viewmodel

import androidx.lifecycle.*
import com.neowise.androidlessions.itmath.model.*

class QuestionsViewModel(maxProblems: Int) : ViewModel() {

    private val _progressLiveData = MutableLiveData(Progress(0, maxProblems))
    private val _problemLiveData = MutableLiveData<Problem>()
    private val _resultLiveData = MutableLiveData<UserResult>()

    val progress = _progressLiveData as LiveData<Progress>
    val problem = _problemLiveData as LiveData<Problem>
    val result = _resultLiveData as LiveData<UserResult>

    private val userResult = MutableUserResult(0, 0, 0)
    private var userAnswer = 0
    
    fun selectedVariant(answer: Int) {
        this.userAnswer = answer
    }
    
    fun skipped() {
        userResult.addSkipped()
        nextProblem()
    }

    fun answered() {
        val problem = _problemLiveData.value!!
        if (problem.isCorrect(userAnswer)) {
            userResult.addRight()
        }
        else {
            userResult.addWrong()
        }
        nextProblem()
    }

    fun nextProblem() {
        val progress = _progressLiveData.value!!
        if (progress.isReached) {
            _resultLiveData.postValue(userResult)
        }
        else {
            val problem = generateProblem(10, 100)

            _problemLiveData.postValue(problem)
            _progressLiveData.postValue(progress.inc())
        }
    }
}