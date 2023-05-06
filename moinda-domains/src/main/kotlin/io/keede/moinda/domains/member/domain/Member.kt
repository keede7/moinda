package io.keede.moinda.domains.member.domain

import io.keede.moinda.core.model.member.entity.MemberJpaEntity

/**
 * @author keede
 * Created on 2023-04-06
 */
data class Member(
    val memberJpaEntity: MemberJpaEntity
) {
    val memberId: Long? = memberJpaEntity.id
    val name: String = memberJpaEntity.name
    val email: String = memberJpaEntity.email
    val password: String = memberJpaEntity.password
    val introduce: String? = memberJpaEntity.introduce
    val participatingMeetingId: Long? = memberJpaEntity.participatingMeetingId()
}