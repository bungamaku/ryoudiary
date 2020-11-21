package id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.viewmodel

import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.data.Step
import id.ac.ui.cs.mobileprogramming.bungaamalia.ryoudiary.repository.StepRepository
import kotlinx.coroutines.launch

class StepViewModel(private val repository: StepRepository) : ViewModel() {

    val allStepsForRecipe: LiveData<List<Step>> =
        repository.allStepsForRecipe.asLiveData()

    fun insertStep(step: Step) = viewModelScope.launch {
        repository.insert(step)
    }
}

class StepViewModelFactory(private val repository: StepRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StepViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StepViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}