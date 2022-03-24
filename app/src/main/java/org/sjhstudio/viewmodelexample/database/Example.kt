package org.sjhstudio.viewmodelexample.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Example(
    var contents: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}