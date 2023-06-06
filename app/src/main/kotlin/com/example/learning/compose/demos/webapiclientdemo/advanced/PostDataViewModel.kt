package com.example.learning.compose.demos.webapiclientdemo.advanced

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learning.compose.demos.webapiclientdemo.advanced.webapiclient.blogs.BlogWebApiClient
import com.example.learning.compose.demos.webapiclientdemo.model.PostData
import com.example.learning.compose.demos.webapiclientdemo.model.PostDataEntity
import kotlinx.coroutines.launch

class PostDataViewModel(
    private val webApiClient: BlogWebApiClient,
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
                    for (x in it.body ?: listOf()) {
                        model.add(x)
                    }
                    postDataList.postValue(model)
                }
            } catch (e: Exception) {
                Log.e("PostDataViewModel", "Error: ${e.message}", e)
            }
        }
    }
}
