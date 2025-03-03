package ru.xpendence.jooq.repository.impl

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.jooq.JooqApplicationTests
import ru.xpendence.jooq.domain.Restaurant
import ru.xpendence.jooq.repository.RestaurantRepository
import java.util.UUID

class RestaurantRepositoryTest : JooqApplicationTests() {

    @Autowired
    private lateinit var repository: RestaurantRepository

    @Test
    fun getRestaurants() {
        val userId = UUID.fromString("1031f963-957c-425f-962e-767080a699cb")
        val restaurants = repository.getAllByUserOrdered(userId)

        kotlin.test.assertTrue { restaurants.map { it.name }.contains("Три поросёнка") }
    }

    @Test
    fun insertAsFields() {
        //given
        val restaurant = Restaurant(
            id = UUID.randomUUID(),
            name = "123"
        )
        //when
        repository.insertAsFields(restaurant)
            .also {
                //then
                assertEquals(restaurant.id, it.id)
                assertEquals(restaurant.name, it.name)
            }
    }

    @Test
    fun insertAsRecord() {
        val restaurant = Restaurant(
            id = UUID.randomUUID(),
            name = "123"
        )
        repository.insertAsFields(restaurant)
            .also {
                assertEquals(restaurant.id, it.id)
                assertEquals(restaurant.name, it.name)
            }
    }
}