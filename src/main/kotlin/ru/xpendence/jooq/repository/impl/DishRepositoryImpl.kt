package ru.xpendence.jooq.repository.impl

import org.jooq.DSLContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Repository
import ru.xpendence.jooq.repository.DishRepository
import ru.xpendence.jooq.repository.entity.tables.Dishes
import ru.xpendence.jooq.repository.entity.tables.Orders
import ru.xpendence.jooq.repository.entity.tables.Restaurants
import ru.xpendence.jooq.repository.entity.tables.Users

@Repository
class DishRepositoryImpl(
    private val dsl: DSLContext
) : DishRepository {

    override fun countOrderedByName(name: String): Long =
        dsl
            .selectCount()
            .from(Dishes.DISHES)
            .innerJoin(Restaurants.RESTAURANTS).on(Restaurants.RESTAURANTS.ID.eq(Dishes.DISHES.RESTAURANT_ID))
            .innerJoin(Orders.ORDERS).on(Orders.ORDERS.RESTAURANT_ID.eq(Restaurants.RESTAURANTS.ID))
            .innerJoin(Users.USERS).on(Users.USERS.ID.eq(Orders.ORDERS.USER_ID))
            .where(Dishes.DISHES.NAME.eq(name))
            .single()
            .value1()
            .toLong()
}