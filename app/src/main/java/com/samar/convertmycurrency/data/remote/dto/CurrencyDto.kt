package com.samar.convertmycurrency.data.remote.dto


import com.samar.convertmycurrency.data.db.entity.CurrencyType

class CurrencyDto : ArrayList<String>()

fun CurrencyDto.toCurrency(): ArrayList<CurrencyType>{
    return this.map {
        CurrencyType(id = it, currencyType = it)
    } as ArrayList<CurrencyType>
}
