package net.virgis.tutorials.dictionaryapp.feature_dictionary.presentation

import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
