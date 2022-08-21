package com.samar.convertmycurrency.presentation.currency.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.samar.convertmycurrency.R
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.presentation.common.AutoSizeText
import com.samar.convertmycurrency.presentation.currency.CurrencyViewModel
import com.samar.convertmycurrency.ui.theme.LightestRed

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContentFromCurrency(viewModel: CurrencyViewModel, listData: List<CurrencyType>) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightestRed)
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.3f)
                        .padding(15.dp),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(34.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            if (viewModel.from.value > 0) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeFromBack()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(34.dp),
                                        painter = painterResource(id = R.drawable.back),
                                        contentDescription = null
                                    )
                                }
                            }
                            else{
                                Spacer(modifier = Modifier.size(34.dp))
                            }

                            AutoSizeText(
                                text = listData[viewModel.from.value].currencyType,
                                textStyle = TextStyle(
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            )

                            if (viewModel.from.value < listData.size) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeFromForward()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(34.dp),
                                        painter = painterResource(id = R.drawable.next),
                                        contentDescription = null
                                    )
                                }
                            }
                            else{
                                Spacer(modifier = Modifier.size(34.dp))
                            }

                        }

                        TextField(
                            modifier = Modifier.fillMaxSize(),
                            value = viewModel.amount.value,
                            onValueChange = {
                                if(it == "." || it.isDigitsOnly()){
                                    viewModel.amount.value = it
                                }
                            },
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 48.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults
                                .textFieldColors(
                                    textColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    cursorColor = Color.Black,
                                ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() }
                            ),
                            placeholder = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "From ${listData[viewModel.from.value].currencyType}",
                                    style = TextStyle(
                                        color = Color.Gray,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 48.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = LightestRed)
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current

                Card(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .fillMaxHeight()
                        .padding(15.dp),
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(14.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(34.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {


                            if (viewModel.from.value > 0) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeFromBack()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(34.dp),
                                        painter = painterResource(id = R.drawable.back),
                                        contentDescription = null
                                    )
                                }
                            }
                            else{
                                Spacer(modifier = Modifier.size(34.dp))
                            }

                            AutoSizeText(
                                text = listData[viewModel.from.value].currencyType,
                                textStyle = TextStyle(
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                            )

                            if (viewModel.from.value < listData.size) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeFromForward()
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(34.dp),
                                        painter = painterResource(id = R.drawable.next),
                                        contentDescription = null
                                    )
                                }
                            }
                            else{
                                Spacer(modifier = Modifier.size(34.dp))
                            }

                        }

                        TextField(
                            modifier = Modifier.fillMaxSize(),
                            value = viewModel.amount.value,
                            onValueChange = {
                                if(it == "." || it.isDigitsOnly()){
                                    viewModel.amount.value = it
                                }
                            },
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults
                                .textFieldColors(
                                    textColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    cursorColor = Color.Black,
                                ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { keyboardController?.hide() }
                            ),
                            placeholder = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "From ${listData[viewModel.from.value].currencyType}",
                                    style = TextStyle(
                                        color = Color.Gray,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 24.sp,
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}