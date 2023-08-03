package com.example.jctools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jctools.ui.theme.JctoolsTheme
import com.example.jctools.view.ScreenSyntax
import com.example.jctools.view.ScreenUI

/*
    Author: Joey yang
    Date: 2023/08/03
    Purpose: Jetpack UI and Syntax test environment
    Reference website:
      1. https://play.kotlinlang.org/byExample/overview
      2. https://foso.github.io/Jetpack-Compose-Playground/

    Program reference:
      PR-01: https://developer.android.com/jetpack/compose/navigation?hl=zh-tw

    Attention:
      1. 研究: 即要進行研究,了解內容後再上傳
 */

sealed class Destination(val route: String) {
    object Syntax: Destination("syntax")
    object UI: Destination("ui")
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JctoolsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController() //看上方 PR-01
                    ToolsScaffold(navController = navController) //重要,是在 MainActivity 外面

                }
            }
        }
    }
}

@Composable
fun ToolsScaffold(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState() //研究

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {}
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Destination.UI.route) {
            //重要: 有一個 Interface NavHost does not have constructors的錯誤,解法要加上 import androidx.navigation.compose.NavHost
            composable(Destination.UI.route) {
                ScreenUI()
            }
            composable(Destination.Syntax.route) {
                ScreenSyntax()
            }
        }
    }
}