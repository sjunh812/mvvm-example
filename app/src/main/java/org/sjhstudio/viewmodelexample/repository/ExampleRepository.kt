package org.sjhstudio.viewmodelexample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import org.sjhstudio.viewmodelexample.database.AppDatabase
import org.sjhstudio.viewmodelexample.database.Example
import org.sjhstudio.viewmodelexample.database.ExampleDao

/**
 * Repository
 *
 * DB에서 가져온 데이터를 가공.
 */
class ExampleRepository(application: Application) {

    private val exampleDao: ExampleDao
    private val exampleList: LiveData<List<Example>>

    init {
        val db = AppDatabase.getInstance(application)
        exampleDao = db!!.exampleDao()
        exampleList = db.exampleDao().getAll()
    }

    fun getAll(): LiveData<List<Example>> {
        return exampleDao.getAll()
    }

    fun insert(example: Example) {
        exampleDao.insert(example)
    }

    fun delete(example: Example) {
        exampleDao.delete(example)
    }
}