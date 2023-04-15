package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.member.domain.Member
import io.keede.moinda.domains.member.usecase.LoginUseCase
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-04-15
 */
@Service
class LoginManager(
    private val memberQueryAdapter: MemberQueryAdapter,
) : LoginUseCase {

    override fun login(command: LoginUseCase.Command): Member {
        val entity = memberQueryAdapter.findByEmail(command.email);

        // TODO: 추후 암호화된 패스워드가 들어오게 될 것이므로 디코딩된 패스워드를 넣어야 한다.
        entity.isMatchPassword(command.password)

        return Member(entity)
    }
}