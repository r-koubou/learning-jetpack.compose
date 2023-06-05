package com.example.learning.compose.demos.webapiclientdemo.simple

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import com.example.learning.compose.demos.webapiclientdemo.model.PostDataEntity
import com.example.learning.compose.demos.webapiclientdemo.simple.webapiclient.DefaultWebApiService
import kotlinx.coroutines.launch

class PostDataViewModel(
    private val webApiClient: DefaultWebApiService,
    private val model: PostDataEntity,
) : ViewModel(),
    MutableList<PostData> by model
{
    val postDataList = MutableLiveData<MutableList<PostData>>(model)

    fun updatePostData()
    {
        viewModelScope.launch {
            try {
                val result = webApiClient.getBlogPosts()
                result.let {
                    model.clear()
                    model.addAll(it)
                    postDataList.postValue(model)
                }
            } catch (e: Exception) {
                Log.e("PostDataViewModel", "Error: ${e.message}", e)
            }
        }
    }
}
