package io.keede.moinda.domains.member.service

import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.member.usecase.MemberQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-04-08
 */
@UseCaseTest
internal class MemberQueryTest {

    @MockK
    private lateinit var memberQueryAdapter: MemberQueryAdapter

    private lateinit var sut: MemberQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MemberQuery(this.memberQueryAdapter)
    }

    @Test
    fun 사용자_단일_조회_성공한다() {

        val query = mockk<MemberQueryUseCase.Query>(relaxed = true)

        every { memberQueryAdapter.findById(query.memberId) } returns mockk(relaxed = true)

        this.sut.getById(query)

        verify(exactly = 1) { memberQueryAdapter.findById(query.memberId) }

    }
}