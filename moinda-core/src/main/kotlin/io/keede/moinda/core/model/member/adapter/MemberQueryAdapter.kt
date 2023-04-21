package io.keede.moinda.core.model.member.adapter

import io.keede.moinda.core.model.member.entity.MemberJpaEntity
/**
 * @author keede
 * Created on 2023-04-05
 */
interface MemberQueryAdapter {

    fun findById(memberId: Long) : MemberJpaEntity

    fun findByEmail(email: String) : MemberJpaEntity

    fun findParticipateInMeetMembers(meetingId: Long) : List<MemberJpaEntity>

}