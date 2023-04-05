package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-04-06
 */
@Service
@Transactional
internal class MemberCommand(
    private val memberCommandAdapter: MemberCommandAdapter
) : MemberCommandUseCase {

    override fun signup(command: MemberCommandUseCase.Command): Member {
        val entity = memberCommandAdapter.save(command.createMember)
        return Member(entity)
    }
}