package ru.xpendence.jooq.repository

import ru.xpendence.jooq.domain.Restaurant
import java.util.UUID

interface RestaurantRepository {

    fun getAllByUserOrdered(userId: UUID): List<Restaurant>
}