package ru.xpendence.jooq.repository.impl

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.xpendence.jooq.domain.Restaurant
import ru.xpendence.jooq.repository.RestaurantRepository
import ru.xpendence.jooq.repository.entity.tables.Dishes
import ru.xpendence.jooq.repository.entity.tables.Orders
import ru.xpendence.jooq.repository.entity.tables.Restaurants
import java.util.UUID

@Repository
class RestaurantRepositoryImpl(
    private val dsl: DSLContext
) : RestaurantRepository {

    override fun getAllByUserOrdered(userId: UUID): List<Restaurant> =
        dsl
            .select(Restaurants.RESTAURANTS.ID, Restaurants.RESTAURANTS.NAME)
            .from(Restaurants.RESTAURANTS)
            .rightJoin(Dishes.DISHES).on(Dishes.DISHES.RESTAURANT_ID.eq(Restaurants.RESTAURANTS.ID))
            .rightJoin(Orders.ORDERS).on(Orders.ORDERS.DISH_ID.eq(Dishes.DISHES.ID))
            .where(Orders.ORDERS.USER_ID.eq(userId))
            .fetchInto(Restaurant::class.java)
}