package com.example.littletreetest.pages.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity

class JetpackComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            LittleTreeTestTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting()
//                }
//            }
//        }
    }
}



//@Composable
//fun Greeting() {
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text("A day in Shark Fin Cove")
//        Text("Davenport, California")
//        Text("December 2018")
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    LittleTreeTestTheme {
//        Greeting()
//    }
//}