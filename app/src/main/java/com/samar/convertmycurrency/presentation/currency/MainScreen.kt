package com.samar.convertmycurrency.presentation.currency

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.samar.convertmycurrency.domain.model.ScreenState
import com.samar.convertmycurrency.presentation.currency.component.ContentFromCurrency
import com.samar.convertmycurrency.presentation.currency.component.ContentToCurrency
import com.samar.convertmycurrency.ui.theme.LightestRed
import com.samar.convertmycurrency.util.BasicAnimation

@Composable
fun CurrencyMainScreen(viewModel: CurrencyViewModel) {

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightestRed),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val contentState = viewModel.state
                if (contentState.value.isLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        BasicAnimation(
                            modifier = Modifier
                                .fillMaxSize(.3f)
                                .align(Alignment.Center)
                        )
                    }
                }

                contentState.value.receivedResponse?.let { currState ->
                    val currencyList = currState.observeAsState(initial = emptyList())
                    if (currencyList.value.isNotEmpty()) {
                        LaunchedEffect(key1 = viewModel.amount.value) {
                            viewModel.convertCurrency()
                        }
                        viewModel.resetConvertedAmount()
                        ContentFromCurrency(viewModel, currencyList.value)
                        IconButton(
                            onClick = { viewModel.switchConvertType() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.CurrencyExchange,
                                contentDescription = "exchange"
                            )
                        }
                        if (viewModel.convertState.value.isLoading) {
                            LinearProgressIndicator()
                        }
                        if (viewModel.convertState.value.error.isNotEmpty()) {
                            Text(
                                text = viewModel.convertState.value.error,
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.h6
                            )
                        }
                        ContentToCurrency(viewModel, currencyList.value)
                    }
                }

                if (contentState.value.error.isNotEmpty()) {
                    Text(
                        text = contentState.value.error,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }


            }

        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightestRed),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val contentState = viewModel.state
                if (contentState.value.isLoading) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        BasicAnimation(
                            modifier = Modifier
                                .fillMaxSize(.3f)
                                .align(Alignment.Center)
                        )
                    }
                }

                contentState.value.receivedResponse?.let { currState ->
                    val currencyList = currState.observeAsState(initial = emptyList())
                    if (currencyList.value.isNotEmpty()) {
                        LaunchedEffect(key1 = viewModel.amount.value) {
                            viewModel.convertCurrency()
                        }
                        viewModel.resetConvertedAmount()
                        ContentFromCurrency(viewModel, currencyList.value)
                        IconButton(
                            onClick = { viewModel.switchConvertType() }
                        ) {
                            Icon(
                                imageVector = Icons.Default.CurrencyExchange,
                                contentDescription = "exchange"
                            )
                        }
                        if (viewModel.convertState.value.isLoading) {
                            LinearProgressIndicator()
                        }
                        if (viewModel.convertState.value.error.isNotEmpty()) {
                            Text(
                                text = viewModel.convertState.value.error,
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.h6
                            )
                        }
                        ContentToCurrency(viewModel, currencyList.value)
                    }
                }

                if (contentState.value.error.isNotEmpty()) {
                    Text(
                        text = contentState.value.error,
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }


            }
        }
    }



}

