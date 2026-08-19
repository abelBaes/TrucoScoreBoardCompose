package br.edu.ifsp.scl.sc3039307.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.ifsp.scl.sc3039307.trucoscoreboardcompose.ui.theme.TrucoScoreBoardComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrucoScoreBoardComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScoreScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreScreen(modifier: Modifier = Modifier){

    var teamAPoints by remember { mutableIntStateOf(0) }
    var teamBPoints by remember { mutableIntStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Placar de Truco",
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp),
        )
        TeamCard(
            "Equipe A",
            teamAPoints,
            {points -> teamAPoints += points},
            Modifier
        )
    }
}

@Composable
fun TeamCard(
    teamName: String,
    teamPoints: Int,
    addPoints: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = teamName,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = "$teamPoints",
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .padding(5.dp),
            onClick = {addPoints(1)},

        ){
            Text(
                text = "+1 Ponto",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
        Button(
            modifier = Modifier
                .padding(5.dp),
            onClick = {addPoints(3)},
            ){
            Text(
                text = "+3 Pontos",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}