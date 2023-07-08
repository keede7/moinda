package io.keede.moinda.core.model.member.port

import io.keede.moinda.core.model.member.entity.MemberJpaEntity
/**
 * @author keede
 * Created on 2023-04-05
 */
interface MemberQueryPort {

    fun findById(memberId: Long?) : MemberJpaEntity

    fun findByEmail(email: String) : MemberJpaEntity

    // TODO : Naming -> Security 전용 UseCase를 따로 생성해서 처리
    fun findOAuth2ByEmail(email: String) : MemberJpaEntity?

    fun existMemberByEmail(email: String): Boolean

    fun findParticipateInMeetMembers(meetingId: Long) : List<MemberJpaEntity>

    fun findWithFetch(memberId: Long?) : MemberJpaEntity
}