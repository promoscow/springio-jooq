package ru.xpendence.jooq.repository.impl

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.xpendence.jooq.domain.Restaurant
import ru.xpendence.jooq.repository.RestaurantRepository
import ru.xpendence.jooq.repository.entity.tables.Dishes
import ru.xpendence.jooq.repository.entity.tables.Orders
import ru.xpendence.jooq.repository.entity.tables.Restaurants
import ru.xpendence.jooq.repository.mapper.toRestaurant
import java.util.UUID

@Repository
class RestaurantRepositoryImpl(
    private val dsl: DSLContext
) : RestaurantRepository {

    override fun insertAsFields(restaurant: Restaurant): Restaurant =
        dsl
            .insertInto(Restaurants.RESTAURANTS)
            .set(Restaurants.RESTAURANTS.ID, restaurant.id)
            .set(Restaurants.RESTAURANTS.NAME, restaurant.name)
            .returning()
            .map { it.toRestaurant() }
            .single()

    override fun insertAsRecord(restaurant: Restaurant): Restaurant =
        dsl
            .insertInto(Restaurants.RESTAURANTS)
            .set(dsl.newRecord(Restaurants.RESTAURANTS, restaurant))
            .returning()
            .map { it.toRestaurant() }
            .single()

    override fun update(restaurant: Restaurant) {
        dsl
            .update(Restaurants.RESTAURANTS)
            .set(Restaurants.RESTAURANTS.NAME, restaurant.name)
            .where(Restaurants.RESTAURANTS.ID.eq(restaurant.id))
            .execute()
    }

    override fun findById(id: UUID): Restaurant? =
        dsl
            .selectFrom(Restaurants.RESTAURANTS)
            .where(Restaurants.RESTAURANTS.ID.eq(id))
            .fetchInto(Restaurant::class.java)
            .singleOrNull()

    override fun getAllByUserOrdered(userId: UUID): List<Restaurant> =
        dsl
            .select(Restaurants.RESTAURANTS.ID, Restaurants.RESTAURANTS.NAME)
            .from(Restaurants.RESTAURANTS)
            .rightJoin(Dishes.DISHES).on(Dishes.DISHES.RESTAURANT_ID.eq(Restaurants.RESTAURANTS.ID))
            .rightJoin(Orders.ORDERS).on(Orders.ORDERS.DISH_ID.eq(Dishes.DISHES.ID))
            .where(Orders.ORDERS.USER_ID.eq(userId))
            .fetchInto(Restaurant::class.java)

    override fun getById(id: UUID): Restaurant =
        dsl
            .selectFrom(Restaurants.RESTAURANTS)
            .where(Restaurants.RESTAURANTS.ID.eq(id))
            .map { it.toRestaurant() }
            .single()

    override fun delete(id: UUID) {
        dsl
            .deleteFrom(Restaurants.RESTAURANTS)
            .where(Restaurants.RESTAURANTS.ID.eq(id))
            .execute()
    }
}