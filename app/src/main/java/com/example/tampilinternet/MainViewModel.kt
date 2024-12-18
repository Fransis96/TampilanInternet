package com.example.tampilinternet

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _posts = mutableStateOf<List<Post>>(emptyList())
    val posts: State<List<Post>> = _posts

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                // Mengambil data dari API
                val postList = RetrofitInstance.api.getPosts()
                _posts.value = postList
            } catch (e: Exception) {
                // Tangani kesalahan (misalnya dengan menampilkan pesan error)
                _posts.value = emptyList()
            }
        }
    }

}