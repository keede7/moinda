package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberCommandAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-06
 */
@UseCaseTest
internal class MemberCommandTest {

    @MockK
    private lateinit var memberCommandAdapter: MemberCommandAdapter

    private lateinit var sut : MemberCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberCommand(memberCommandAdapter)
    }

    @Test
    fun 사용자_회원가입_성공() {

        val command = mockk<MemberCommandUseCase.Command>()

        // Model(createMember) 를 할당해서 사용시에 실패한다. 이유는?
        every { memberCommandAdapter.save(command.createMember) } returns mockk(relaxed = true)

        sut.signup(command)

        verify { memberCommandAdapter.save(command.createMember) }

    }

}