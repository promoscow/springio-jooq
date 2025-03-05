package ru.xpendence.jooq.domain

import java.math.BigDecimal
import java.util.UUID

class Dish(
    val id: UUID? = null,
    var name: String? = null,
    var price: BigDecimal? = null,
    var active: Boolean? = null,
    var version: Int? = null,
    val restaurant: Restaurant? = null,
    val orders: List<Order> = listOf()
)
