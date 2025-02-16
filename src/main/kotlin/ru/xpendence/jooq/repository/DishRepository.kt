package ru.xpendence.jooq.repository

interface DishRepository {

    fun countOrderedByName(name: String): Long
}