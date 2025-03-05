package ru.xpendence.jooq.domain

import ru.xpendence.jooq.domain.type.RoleType
import java.util.UUID

class Role(
    val id: UUID? = null,
    val name: RoleType? = null
)