package com.example.dihilt

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dihilt.ui.theme.DIHiltTheme
import com.example.dihilt.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var viewModel: DogViewModel

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        viewModel.getRandomDogs()
        setUpObserver()
        setContent {
            DIHiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    run {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MyUI("https://images.dog.ceo/breeds/doberman/n02107142_10952.jpg")
                       }
                    }
            }
        }
    }
//     setUpObserver()
}

    private fun setUpObserver() {


        with(viewModel) {
            dogResponse.observe(this@MainActivity) {
                Log.e(TAG, "setUpObserver 1 : "+ it.message)

            }

            viewModel.error.observe(this@MainActivity) {

                Log.e(TAG, "setUpObserver 2 : " + it )
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_LONG).show()
            }


        }


    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[DogViewModel::class.java]
    }
    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MyUI(url : String) {
        GlideImage(model = url, contentDescription =stringResource(id = R.string.dog) )
    }
}