package com.example.tampilinternet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tampilinternet.ui.theme.TampilInternetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TampilInternetTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(mainViewModel: MainViewModel = viewModel()) {

    val posts by mainViewModel.posts

    Scaffold(
        topBar = { TopAppBar(title = { Text("Posts") }) },
        content = {
            if (posts.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
            } else {
                PostList(posts = posts, contentPadding = it)
            }
        }
    )
}

@Composable
fun PostList(posts: List<Post>, contentPadding: PaddingValues = PaddingValues(16.dp)) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 80.dp)) {
        items(posts){ post ->
            PostItem(post = post)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}