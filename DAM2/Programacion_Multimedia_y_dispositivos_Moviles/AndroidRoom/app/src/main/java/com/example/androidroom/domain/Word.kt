package com.example.androidroom.domain

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "word_table")
data class Word (
    @PrimaryKey @ColumnInfo(name = "word")
    val word: String
)
