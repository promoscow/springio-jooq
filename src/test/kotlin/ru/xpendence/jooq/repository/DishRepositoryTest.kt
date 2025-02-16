package ru.xpendence.jooq.repository

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import ru.xpendence.jooq.JooqApplicationTests
import kotlin.test.assertEquals

class DishRepositoryTest : JooqApplicationTests() {

    @Autowired
    private lateinit var repository: DishRepository

    @Test
    fun countOrderedByName() {
        repository.countOrderedByName("Харчо с изюминкой").also { assertEquals(2, it) }
    }
}