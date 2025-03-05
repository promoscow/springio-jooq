package ru.xpendence.jooq.repository.impl

import org.apache.commons.lang3.RandomStringUtils
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.jooq.JooqApplicationTests
import ru.xpendence.jooq.domain.Restaurant
import ru.xpendence.jooq.repository.RestaurantRepository
import java.util.UUID
import kotlin.test.assertNotNull
import kotlin.test.assertNull

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

    @Test
    @DisplayName("update(): успешное обновление поля name")
    fun update() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insertAsRecord(restaurant)
        //when
        val newName = RandomStringUtils.secure().nextAlphanumeric(32)
        saved.copy(name = newName).also { repository.update(it) }
        //then
        repository.findById(saved.id!!)
            .also { kotlin.test.assertEquals(newName, it!!.name) }
        //after
        repository.delete(saved.id!!)
    }

    @Test
    @DisplayName("findById(): сохранённый ресторан успешно найден")
    fun findById() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insertAsRecord(restaurant)
        //when
        repository.findById(saved.id!!)
            .also {
                //then
                assertNotNull(it)
            }
        //after
        repository.delete(saved.id!!)
    }

    @Test
    @DisplayName("delete(): успешно удаляет ресторан")
    fun delete() {
        //given
        val restaurant = Restaurant(
            name = RandomStringUtils.secure().nextAlphanumeric(32)
        )
        val saved = repository.insertAsRecord(restaurant)
        //when
        repository.delete(saved.id!!)
        //then
        repository.findById(saved.id!!).also { assertNull(it) }
    }
}