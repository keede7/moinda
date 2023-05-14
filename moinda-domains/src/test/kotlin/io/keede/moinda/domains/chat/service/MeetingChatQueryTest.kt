package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatQueryAdapter
import io.keede.moinda.domains.chat.usecase.MeetingChatQueryUseCase
import io.keede.moinda.domains.config.UseCaseTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author keede
 * Created on 2023-05-14
 */
@UseCaseTest
internal class MeetingChatQueryTest {

    @MockK
    private lateinit var meetingChatQueryAdapter: MeetingChatQueryAdapter

    private lateinit var sut: MeetingChatQueryUseCase

    @BeforeEach
    fun init() {
        this.sut = MeetingChatQuery(this.meetingChatQueryAdapter)
    }

    @Test
    fun 채팅_메세지_목록_조회를_성공한다() {

        val meetingId = 1L

        every { meetingChatQueryAdapter.findChatsByMeetingId(any()) } returns mockk(relaxed = true)

        this.sut.getChatting(meetingId)

        verify(exactly = 1) { meetingChatQueryAdapter.findChatsByMeetingId(meetingId) }

    }

}