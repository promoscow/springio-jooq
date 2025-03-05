package ru.xpendence.jooq.repository.mapper

import org.jooq.Record
import ru.xpendence.jooq.domain.Restaurant
import ru.xpendence.jooq.repository.entity.tables.Restaurants

fun Record.toRestaurant(): Restaurant = Restaurant(
    id = this[Restaurants.RESTAURANTS.ID],
    name = this[Restaurants.RESTAURANTS.NAME]
)