package ru.xpendence.jooq.repository

import ru.xpendence.jooq.domain.Restaurant
import java.util.UUID

interface RestaurantRepository {

    fun insertAsFields(restaurant: Restaurant): Restaurant

    fun insertAsRecord(restaurant: Restaurant): Restaurant

    fun getAllByUserOrdered(userId: UUID): List<Restaurant>

    fun getById(id: UUID): Restaurant
}