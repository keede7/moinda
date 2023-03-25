package io.keede.moinda.domains.group.service

import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.group.usecase.GroupQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@UseCaseTest
internal class GroupQueryTests {

    @MockK
    private lateinit var groupQueryAdapter: GroupQueryAdapter

    private lateinit var sut: GroupQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = GroupQuery(this.groupQueryAdapter)
    }

    @Test
    fun 그룹_조회_성공() {
        // Given
        val query = mockk<GroupQueryUseCase.Query>(relaxed = true)

        every { groupQueryAdapter.findById(query.groupId) } returns mockk(relaxed = true)
        // When
        this.sut.findById(query)
        // Then
        verify { groupQueryAdapter.findById(query.groupId) }

    }

}