package io.keede.moinda.common.meeting

import java.time.LocalDateTime

/**
 * @author keede
 * Created on 2023-04-18
 */
data class CreateMeeting(
    val name: String,
    val primaryAddress: String,
    val placeName: String,
    var description: String?,
    val capacity: Int,
    var startAt: LocalDateTime,
    var endAt: LocalDateTime,
)