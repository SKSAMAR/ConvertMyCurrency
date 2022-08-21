package com.samar.convertmycurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samar.convertmycurrency.presentation.currency.CurrencyMainScreen
import com.samar.convertmycurrency.presentation.currency.CurrencyViewModel
import com.samar.convertmycurrency.ui.theme.ConvertMyCurrencyTheme
import com.samar.convertmycurrency.ui.theme.LightestRed
import com.samar.convertmycurrency.R
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.presentation.common.AutoSizeText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyActivity : ComponentActivity() {

    private val viewModel by viewModels<CurrencyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertMyCurrencyTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CurrencyMainScreen(viewModel = viewModel)
                }
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ConvertMyCurrencyTheme {

        }
    }


}
