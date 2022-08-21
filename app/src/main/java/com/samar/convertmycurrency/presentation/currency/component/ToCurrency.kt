package com.samar.convertmycurrency.presentation.currency.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samar.convertmycurrency.R
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.presentation.common.AutoSizeText
import com.samar.convertmycurrency.presentation.currency.CurrencyViewModel
import com.samar.convertmycurrency.ui.theme.LightestRed

@Composable
fun ContentToCurrency(viewModel: CurrencyViewModel, listData: List<CurrencyType>) {

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = LightestRed)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(.5f)
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


                            if (viewModel.to.value > 0) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeToBack()
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
                                text = listData[viewModel.to.value].currencyType,
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

                            if (viewModel.to.value < listData.size) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeToForward()
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
                            value = if(viewModel.convertState.value.receivedResponse==-1f)"" else viewModel.convertState.value.receivedResponse.toString(),
                            onValueChange = {},
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = if(viewModel.convertState.value.receivedResponse!! >=99999f)48.sp else 24.sp,
                                textAlign = TextAlign.Center
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults
                                .textFieldColors(
                                    textColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    cursorColor = Color.Transparent,
                                    placeholderColor = Color.Gray
                                ),
                            enabled = false,
                            placeholder = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "To ${listData[viewModel.to.value].currencyType}",
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

                Card(
                    modifier = Modifier
                        .fillMaxWidth(.75f)
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


                            if (viewModel.to.value > 0) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeToBack()
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
                                text = listData[viewModel.to.value].currencyType,
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

                            if (viewModel.to.value < listData.size) {
                                IconButton(
                                    onClick = {
                                        viewModel.takeToForward()
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
                            value = if(viewModel.convertState.value.receivedResponse==-1f)"" else viewModel.convertState.value.receivedResponse.toString(),
                            onValueChange = {},
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
                                    cursorColor = Color.Transparent,
                                    placeholderColor = Color.Gray
                                ),
                            enabled = false,
                            placeholder = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "To ${listData[viewModel.to.value].currencyType}",
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