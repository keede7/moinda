package io.keede.moinda.domains.group.service

import io.keede.moinda.core.model.group.port.GroupQueryPort
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.group.usecase.GroupQueryUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-03-25
 */
@UseCaseTest
internal class GroupQueryTests {

    @MockK
    private lateinit var groupQueryPort: GroupQueryPort

    private lateinit var sut: GroupQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = GroupQuery(this.groupQueryPort)
    }

    @Test
    fun 그룹_조회_성공() {
        // Given
        val query = mockk<GroupQueryUseCase.Query>(relaxed = true)

        every { groupQueryPort.findById(query.groupId) } returns mockk(relaxed = true)
        // When
        this.sut.getGroupById(query)
        // Then
        verify { groupQueryPort.findById(query.groupId) }

    }

    @Test
    fun 그룹_전체_조회() {
        // Given
        every { groupQueryPort.findGroups() } returns mockk(relaxed = true)

        // When
        this.sut.getGroups()

        // Then
        verify { groupQueryPort.findGroups() }

    }

}