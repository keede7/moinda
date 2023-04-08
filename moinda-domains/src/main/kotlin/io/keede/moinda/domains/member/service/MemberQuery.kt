package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
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
    private val memberQueryAdapter: MemberQueryAdapter
) : MemberQueryUseCase {

    override fun findById(query: MemberQueryUseCase.Query): Member {
        val memberJpaEntity = memberQueryAdapter.findById(query.memberId)

        return Member(memberJpaEntity)
    }
}

