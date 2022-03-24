package org.sjhstudio.viewmodelexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sjhstudio.viewmodelexample.database.Example
import org.sjhstudio.viewmodelexample.repository.ExampleRepository

class ExampleViewModel(application: Application) : AndroidViewModel(application) {

    private val exampleRepository = ExampleRepository(application)
    private val items = exampleRepository.getAll()

    fun getAll(): LiveData<List<Example>> {
        return items
    }

    fun insert(example: Example) {
        exampleRepository.insert(example)
    }

    fun delete(example: Example) {
        exampleRepository.delete(example)
    }

}