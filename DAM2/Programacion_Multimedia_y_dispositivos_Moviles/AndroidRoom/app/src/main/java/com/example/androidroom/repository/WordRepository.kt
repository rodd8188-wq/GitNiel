package com.example.androidroom.repository

import androidx.annotation.WorkerThread
import com.example.androidroom.database.WordDAO
import com.example.androidroom.domain.Word

class WordRepository(private val wordDAO: WordDAO) {

    val allWords = wordDAO.getAlphabetizedWords()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDAO.insert(word)
    }

}