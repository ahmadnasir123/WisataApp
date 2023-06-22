package com.sirdev.wisataappfinal.data

import com.sirdev.wisataappfinal.model.OrderWisata
import com.sirdev.wisataappfinal.model.WisataData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val wisatan = mutableListOf<OrderWisata>()

    init {
        if (wisatan.isEmpty()) {
            WisataData.wisatadata.forEach{
                wisatan.add(OrderWisata(it,0))
            }
        }
    }
    fun getWisata(): Flow<MutableList<OrderWisata>> = flowOf(wisatan)



    fun getOrderById(wisataId: Long): OrderWisata {
        return wisatan.first() {
            it.wisata.wisataId == wisataId
        }
    }

}