package br.edu.ifsp.scl.sc3039307.trucoscoreboardcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

    val hasZeroScore = teamAPoints == 0 && teamBPoints == 0
    val teamAHasGamePoint = teamAPoints == 11
    val teamBHasGamePoint = teamBPoints == 11
    val winnerMessage = when {
        teamAPoints == 12 -> "Equipe A venceu a partida!"
        teamBPoints == 12 -> "Equipe B venceu a partida!"
        else -> ""
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Placar de Truco",
            fontSize = 30.sp,
            modifier = Modifier.padding(10.dp),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            TeamCard(
                "Equipe A",
                teamAPoints,
                {points -> teamAPoints += points},
                Modifier.weight(1f)
            )
            TeamCard(
                "Equipe B",
                teamBPoints,
                {points -> teamBPoints += points},
                Modifier.weight(1f)
            )
        }
        Button(
            onClick = {teamAPoints = 0
                       teamBPoints = 0},
            enabled = !hasZeroScore,
            modifier = Modifier
                .wrapContentSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Reiniciar Pontuação",
                fontSize = 20.sp
            )
        }
        AnimatedVisibility(visible = teamAHasGamePoint) {
            Text(
                text = "Equipe A alcançou a mão de 11!",
                fontSize = 25.sp,
                color = Color.Blue,
                modifier = Modifier
                    .padding(10.dp))
        }
        AnimatedVisibility(visible = teamBHasGamePoint) {
            Text(
                text = "Equipe B alcançou a mão de 11!",
                fontSize = 25.sp,
                color = Color.Red,
                modifier = Modifier
                    .padding(10.dp))
        }
        Text(
            text = winnerMessage,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp)
        )

    }
}

@Composable
fun TeamCard(
    teamName: String,
    teamPoints: Int,
    addPoints: (Int) -> Unit,
    modifier: Modifier
){
    Column(
        modifier = modifier
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