package com.samar.convertmycurrency.presentation.currency

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.samar.convertmycurrency.data.db.entity.CurrencyType
import com.samar.convertmycurrency.data.remote.dto.toCurrency
import com.samar.convertmycurrency.domain.model.ScreenState
import com.samar.convertmycurrency.domain.use_cases.getCurrency.GetCurrencyUseCase
import com.samar.convertmycurrency.util.BaseViewModel
import com.samar.convertmycurrency.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.NumberFormat
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel
@Inject constructor(
    private val currencyUseCase: GetCurrencyUseCase,
    private val currencies: LiveData<List<CurrencyType>>
): BaseViewModel<LiveData<List<CurrencyType>>>() {

    private var _convertState = mutableStateOf(ScreenState<Float>(receivedResponse = -1f))
    var convertState : State<ScreenState<Float>> = _convertState

    val from = mutableStateOf(0)
    val to = mutableStateOf(0)
    val amount = mutableStateOf("")
    init {
        _state.value = ScreenState(receivedResponse = currencies)
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000L)
            getAllCurrencies()
        }
    }

    private fun getAllCurrencies(){
        if(currencies.value.isNullOrEmpty()){
            currencyUseCase.invoke().onEach {
                when(it){
                    is Resource.Success->{
                        it.data?.let {currencyData->
                            insertAllCurrencyList(currencyData.toCurrency())
                        }
                        _state.value = ScreenState(receivedResponse = currencies)
                        from.value = 0
                        to.value = 1
                    }
                    is Resource.Error->{
                        _state.value = ScreenState(error = it.message?:"Some Error")
                    }
                    is Resource.Loading->{
                        _state.value = ScreenState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
        else{
            from.value = 0
            to.value = 1
        }
    }

    private suspend fun insertAllCurrencyList(currencies: ArrayList<CurrencyType>){
        viewModelScope.launch(Dispatchers.IO) {
            currencyUseCase.insertCurrenciesToDb(currencies)
        }
    }

    fun switchConvertType(){
        val temp = from.value
        from.value = to.value
        to.value = temp
        convertCurrency()
    }


    fun takeFromBack(){
        if(from.value-1 > 0){
           if(from.value-1!=to.value){
               from.value--
               convertCurrency()

           }else{
               if(from.value-2 >= 0){
                   from.value = from.value-2
                   convertCurrency()
               }
           }
        }
    }

    fun takeFromForward(){
        if(from.value+1 < currencies.value!!.size){
            if(from.value+1!=to.value){
                from.value++
                convertCurrency()
            }else{
                if(from.value+2 < currencies.value!!.size){
                    from.value = from.value+2
                    convertCurrency()
                }
            }
        }
    }

    fun takeToBack(){
        if(to.value-1 > 0){
            if(to.value-1!=from.value){
                to.value--
                convertCurrency()
            }else{
                if(to.value-2 >= 0){
                    to.value = to.value-2
                    convertCurrency()
                }
            }
        }
    }

    fun takeToForward(){
        if(to.value+1 < currencies.value!!.size){
            if(to.value+1!=from.value){
                to.value++
                convertCurrency()
            }else{
                if(to.value+2 < currencies.value!!.size){
                    to.value = to.value+2
                    convertCurrency()
                }
            }
        }
    }

    fun resetConvertedAmount(){
        if (amount.value.isEmpty()){
            _convertState.value = ScreenState(receivedResponse = -1f)
            return
        }
    }

    fun convertCurrency(){
        val toType = _state.value.receivedResponse?.value?.get(to.value)?.currencyType?:""
        val fromType = _state.value.receivedResponse?.value?.get(from.value)?.currencyType?:""

        if(toType.isNotEmpty() && fromType.isNotEmpty() && amount.value.isNotEmpty()){
            currencyUseCase.convertCurrency(from = fromType, to = toType, amount = amount.value)
                .onEach {
                    when(it){
                        is Resource.Success->{
                            val finalRes = it.data?.times(amount.value.toFloat())
                            var shortEnd = 0f
                            if (finalRes!=null || finalRes!=0f){
                                shortEnd = String.format("%.2f", finalRes).toFloat()
                            }
                            _convertState.value = ScreenState(receivedResponse = shortEnd)
                        }
                        is Resource.Error->{
                            _convertState.value = ScreenState(error = it.message?:"Some Error", receivedResponse = -1f)
                        }
                        is Resource.Loading->{

                            _convertState.value = ScreenState(isLoading = true, receivedResponse = -1f)
                        }
                    }
                }.launchIn(viewModelScope)

        }
    }



}