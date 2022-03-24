package org.sjhstudio.viewmodelexample.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExampleDao {

    @Query("SELECT * FROM Example")
    fun getAll(): LiveData<List<Example>>

    @Insert
    fun insert(example: Example)

    @Update
    fun update(example: Example)

    @Delete
    fun delete(example: Example)

}