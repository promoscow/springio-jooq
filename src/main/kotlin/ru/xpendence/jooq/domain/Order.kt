package ru.xpendence.jooq.domain

import java.time.LocalDate
import java.util.UUID

class Order(
    val id: UUID? = null,
    var date: LocalDate? = null,
    val user: User? = null,
    var dish: Dish? = null
)