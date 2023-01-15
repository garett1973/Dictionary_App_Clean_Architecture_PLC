package net.virgis.tutorials.dictionaryapp.feature_dictionary.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.virgis.tutorials.dictionaryapp.core.util.Resource
import net.virgis.tutorials.dictionaryapp.feature_dictionary.data.local.WordInfoDao
import net.virgis.tutorials.dictionaryapp.feature_dictionary.data.remote.DictionaryApi
import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.model.WordInfo
import net.virgis.tutorials.dictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>  = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Could not reach server.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}