package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.port.MemberQueryPort
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-06
 */
@Service
@Transactional(readOnly = true)
internal class MemberQuery(
    private val memberQueryPort: MemberQueryPort
) : MemberQueryUseCase {

    override fun getById(query: MemberQueryUseCase.Query): Member {
        val memberJpaEntity = memberQueryPort.findById(query.memberId)
        return Member(memberJpaEntity)
    }

    // 모임 상세 조회시 참여한 사용자 목록을 표시할때 사용한다.
    override fun getParticipateInMeetMembers(participateMemberByMeetingId: MemberQueryUseCase.ParticipateMemberByMeetingId): List<Member> {

        val entities = memberQueryPort.findParticipateInMeetMembers(
            participateMemberByMeetingId.meetingId
        )

        return entities
            .map { Member(it) }
            .toList()
    }
}

