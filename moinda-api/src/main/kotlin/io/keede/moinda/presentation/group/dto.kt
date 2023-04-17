package io.keede.moinda.presentation.group

import io.keede.moinda.common.group.CreateGroup
import io.keede.moinda.domains.group.domain.Group
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * TODO : Advice 작업 필요.
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
    @field:NotEmpty(message = "그룹명을 입력하세요.")
    val name: String,
    @field:NotNull(message = "그룹명을 입력하세요.")
    val description: String?,
    // TODO : 값이 없을 때 추가적인 Validation
    @field:Min(message = "최소 2명 이상 입력하세요.", value = 2)
    val capacity: Int
)

data class ParticipateDto(
    @field:NotNull(message = "그룹을 지정해야 합니다.")
    val groupId: Long,
    @field:NotNull(message = "가입자를 지정해야 합니다.")
    val memberId: Long,
)

data class LeaveGroupRequestDto(
    @field:NotNull(message = "사용자 번호가 필요합니다.")
    val memberId: Long,
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

internal fun toGroupResponseDtoList(
    groups: List<Group>
): List<GroupResponseDto> = groups
        .map(Group::toGroupResponseDto)
        .toList()

internal fun CreateGroupDto.toDomain() = CreateGroup(
    name,
    description,
    capacity
)