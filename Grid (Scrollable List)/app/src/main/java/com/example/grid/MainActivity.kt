package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThemenListe()
                }
            }
        }
    }
}


@Composable
fun ThemenListe(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.background(Color.White)
        .padding(8.dp)) {
        items(30) {
            ThemenPunkt(modifier)
        }
    }


}

@Composable
fun ThemenPunkt(modifier: Modifier = Modifier) {



    Surface(modifier = modifier.fillMaxWidth()
        .wrapContentHeight(),
            shape = RoundedCornerShape(20),

    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(R.drawable.gender_fluid),
                contentDescription = "bild",
                modifier = Modifier.padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "What Is Agenda",
                    fontSize = 24.sp
                )
                Row() {
                    Text(
                        text = "::",
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.padding(8.dp))
                    Text(
                        text = "32",
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GridPreview(modifier: Modifier = Modifier) {
    ThemenListe()
}