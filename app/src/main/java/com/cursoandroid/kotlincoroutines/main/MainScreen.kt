package com.cursoandroid.kotlincoroutines.main

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.cursoandroid.kotlincoroutines.R
import com.cursoandroid.kotlincoroutines.ui.themes.CoroutinesBasicsTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat.Surface
import kotlinx.coroutines.Dispatchers
import org.w3c.dom.Text

@Composable
fun MainScreen(viewModel: MainViewModel){
    CoroutinesBasicsTheme {
        Scaffold(
            topBar = { TopAppBar (title = { Text(stringResource(R.string.app_name)) })
            }
        ) {
            MainUI(viewModel)
        }
    }
}

@Composable
fun MainUI(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        MainText(
            leftText = viewModel.leftData.toString(),
            rightText = viewModel.rightData.toString()
        )

        DefaultButton(buttonName = "Launch", onButtonClick = {
            viewModel.onButtonClick(useAsync = false)
        })
        Spacer(modifier = Modifier.height(10.dp))

        DefaultButton(buttonName = "Async", onButtonClick = {
            viewModel.onButtonClick(useAsync = true)
        })
        Spacer(modifier = Modifier.height(10.dp))

        DefaultButton(buttonName = "Cancelar", onButtonClick = viewModel::onCancelButtonClick)
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
private fun MainText(leftText: String, rightText: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        DefaultText(text = leftText, modifier = Modifier.fillMaxWidth().weight(1F))
        DefaultText(text = rightText, modifier = Modifier.fillMaxWidth().weight(1F))
    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun DefaultText(text: String, modifier: Modifier) {
    Surface(
        modifier = modifier.padding(all = 5.dp),
        color = Color.LightGray
    ) {
        Text(
            text = text,
            fontSize = 120.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun DefaultButton(
    buttonName: String,
    onButtonClick: () -> Unit) {

    Button(
        onClick = onButtonClick,
        modifier = Modifier.size(width = 150.dp, height = 60.dp)
    ) {
        Text(
            text = buttonName,
            fontSize = 30.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview(){
    val viewModel = MainViewModel(Dispatchers.Default)
    MainScreen(viewModel)
}

