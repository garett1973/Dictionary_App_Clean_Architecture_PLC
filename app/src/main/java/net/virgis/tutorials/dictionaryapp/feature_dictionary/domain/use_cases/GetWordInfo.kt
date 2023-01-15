package net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.use_cases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.virgis.tutorials.dictionaryapp.core.util.Resource
import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.model.WordInfo
import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository


class GetWordInfo(
    private val repository: WordInfoRepository
) {

    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {}
        }
        return repository.getWordInfo(word)
    }
}