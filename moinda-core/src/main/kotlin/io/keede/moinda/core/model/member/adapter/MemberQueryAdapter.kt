package io.keede.moinda.core.model.member.adapter

import io.keede.moinda.core.model.member.entity.MemberJpaEntity

interface MemberQueryAdapter {

    fun findById(memberId: Long) : MemberJpaEntity

}