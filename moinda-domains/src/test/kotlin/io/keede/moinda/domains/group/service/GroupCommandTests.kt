package io.keede.moinda.domains.group.service

import io.keede.moinda.core.model.group.adapter.GroupCommandAdapter
import io.keede.moinda.domains.config.UseCaseTest
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-03-18
 */
@UseCaseTest
class GroupCommandTests {

    @MockK
    private lateinit var commandAdapter: GroupCommandAdapter

    private lateinit var sut: GroupCommandUseCase

    @BeforeEach
    fun init() {
        this.sut = GroupCommand(commandAdapter)
    }

    @Test
    fun 그룹을_생성한다() {
        // Given
        val command = mockk<GroupCommandUseCase.Command>()

        every { commandAdapter.save(command.createGroup) } returns mockk(relaxed = true)

        // When
        val create = sut.create(command)

        // Then
        verify { commandAdapter.save(command.createGroup) }
    }

}