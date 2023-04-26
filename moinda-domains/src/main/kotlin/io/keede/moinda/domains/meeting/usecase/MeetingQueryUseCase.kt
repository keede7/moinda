package io.keede.moinda.domains.meeting.usecase

import io.keede.moinda.domains.meeting.domain.Meeting
import io.keede.moinda.mapper.Paginator
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * @author keede
 * Created on 2023-04-19
 */
interface MeetingQueryUseCase {

    fun getById(query: Query): Meeting

    fun getMeetings(): List<Meeting>

    fun getMeetings(pageQuery: PageQuery): Paginator<Meeting>

    fun getInParticipatingMeetingsByMemberId(memberId: Long): List<Meeting>

    data class Query(
        val meetingId: Long
    )

    data class PageQuery(
        val page: Int,
        val size: Int?,
        val orderBy: String? = null,
    ) {
        fun ofPageable(): Pageable = PageRequest.of(
            this.page - 1,
            this.size ?: 10,
            Sort.by(this.orderBy ?: "id").descending()
        )
    }

}