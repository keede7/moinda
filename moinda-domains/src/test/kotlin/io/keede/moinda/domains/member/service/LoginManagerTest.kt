package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.LoginUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-15
 */
@UseCaseTest
class LoginManagerTest {

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter


    private lateinit var sut: LoginManager

    @BeforeEach
    fun init() {
        this.sut = LoginManager(this.memberQueryAdapter)
    }

    @Test
    fun 사용자_로그인에_성공한다() {
        // Given
        val command = mockk<LoginUseCase.Command>(relaxed = true)
        val memberJpaEntity = mockk<MemberJpaEntity>(relaxed = true)

        every { memberQueryAdapter.findByEmail(command.email) } returns mockk(relaxed = true)
        every { memberJpaEntity.isMatchPassword(any()) } answers { true }

        // When
        this.sut.login(command)

        // Then
        every { memberQueryAdapter.findByEmail(any()) }
        every { memberJpaEntity.isMatchPassword(any()) }
    }

}