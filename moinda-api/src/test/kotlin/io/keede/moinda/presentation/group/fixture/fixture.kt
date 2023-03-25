package io.keede.moinda.presentation.group.fixture

import io.keede.moinda.presentation.group.CreateGroupDto

fun ofCreateGroupDto(
    name: String,
    description: String?,
    capacity: Int
) =  CreateGroupDto(
    name,
    description,
    capacity
)
