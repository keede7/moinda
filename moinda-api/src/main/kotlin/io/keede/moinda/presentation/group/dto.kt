package io.keede.moinda.presentation.group

import io.keede.moinda.common.group.CreateGroup
import io.keede.moinda.domains.group.domain.Group

/**
 * @author keede
 * Created on 2023-03-25
 */
data class GroupResponseDto(
    val groupId: Long,
    val name: String,
    val description: String?,
    val capacity: Int
)

data class CreateGroupDto(
    val name: String,
    val description: String?,
    val capacity: Int
)

/**
 * Class.Method() = ReturnType
 * Class로 선언한 값으로 ReturnType의 객체를 생성해낼 수 있다.
 * Class의 Property를 이용해서 넣어줄 수 있다.
 * 아래와 동일한 결과물이다.
 * internal fun Group.toGroupResponseDto(): GroupResponseDto {
        return GroupResponseDto(
        groupId,
        name,
        description,
        capacity
        )
    }
 *
 */
internal fun Group.toGroupResponseDto() = GroupResponseDto(
    groupId,
    name,
    description,
    capacity
)

internal fun CreateGroupDto.toDomain() = CreateGroup(
    name,
    description,
    capacity
)