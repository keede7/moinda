package io.keede.moinda.presentation.group.fixture

import io.keede.moinda.presentation.group.CreateGroupDto
import io.keede.moinda.presentation.group.LeaveGroupRequestDto
import io.keede.moinda.presentation.group.ParticipateDto


/**
 * @author keede
 * Created on 2023-03-25
 */
fun ofCreateGroupDto(
    name: String,
    description: String?,
    capacity: Int
) =  CreateGroupDto(
    name,
    description,
    capacity
)

fun ofParticipateDto(
    groupId: Long,
    memberId: Long,
) = ParticipateDto(
    groupId,
    memberId
)

fun ofLeaveGroupRequestDto(
    memberId: Long,
) = LeaveGroupRequestDto(
    memberId
)
