package ru.xpendence.jooq.repository

import ru.xpendence.jooq.domain.Restaurant
import java.util.UUID

/**
 * Репозиторий для работы с записями ресторанов.
 */
interface RestaurantRepository {

    /**
     * Сохранение записи о ресторане (способ инициализации колонок - по полям).
     *
     * @param restaurant - ресторан для сохранения
     * @return сохранённый ресторан
     */
    fun insertAsFields(restaurant: Restaurant): Restaurant

    /**
     * Сохранение записи о ресторане (способ инициализации колонок - запись целиком).
     *
     * @param restaurant - ресторан для сохранения
     * @return сохранённый ресторан
     */
    fun insertAsRecord(restaurant: Restaurant): Restaurant

    /**
     * Обновление записи о ресторане.
     *
     * @param restaurant - ресторан для обновления
     */
    fun update(restaurant: Restaurant)

    /**
     * Поиск ресторана по идентификатору.
     *
     * @param id - идентификатор ресторана
     * @return найденный ресторан
     */
    fun findById(id: UUID): Restaurant?

    /**
     * Получение списка ресторанов, в которых пользователь делал заказ.
     *
     * @param userId - идентификатор пользователя
     * @return список найденных ресторанов
     */
    fun getAllByUserOrdered(userId: UUID): List<Restaurant>

    /**
     * Получение ресторана по идентификатору.
     *
     * @param id - идентификатор ресторана
     * @return найденный ресторан
     */
    fun getById(id: UUID): Restaurant

    /**
     * Удаление ресторана.
     *
     * @param id - идентификатор ресторана для удаления
     */
    fun delete(id: UUID)
}