package net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.repository

import kotlinx.coroutines.flow.Flow
import net.virgis.tutorials.dictionaryapp.core.util.Resource
import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.model.WordInfo

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}