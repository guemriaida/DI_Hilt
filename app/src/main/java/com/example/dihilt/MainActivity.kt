package com.example.dihilt

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dihilt.ui.theme.DIHiltTheme
import com.example.dihilt.viewmodel.DogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            MyUI()
                        }
                    }
            }
        }
    }
        setUpViewModel()
        viewModel.getRandomDogs()
        setUpObserver()
}
    @Composable
    fun MyUI() {
        Image(

            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(id = R.string.dog) ,

        )
    }


//    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: DogViewModel

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        setUpViewModel()
//        initViews()
//        setUpObserver()
//    }

    private fun setUpObserver() {
        with(viewModel) {
            dogResponse.observe(this@MainActivity) {
                Log.e(TAG, "setUpObserver 1 : "+ it.message)
//                Glide.with(this@MainActivity)
//                    .load(it.message)
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .error(android.R.drawable.ic_dialog_alert)
//                    .into(Id.dog)
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

//    private fun initViews() {
//        binding.btnNewDog.setOnClickListener {
//            viewModel.getRandomDogs()
//        }
//    }
}
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.dihilt.ui.theme.DIHiltTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            DIHiltTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//            text = "Hello $name!",
//            modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    DIHiltTheme {
//        Greeting("Android")
//    }
//}