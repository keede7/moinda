package io.keede.moinda.domains.chat.service

import io.keede.moinda.core.model.chat.adapter.MeetingChatQueryAdapter
import io.keede.moinda.domains.chat.domain.MeetingChat
import io.keede.moinda.domains.chat.usecase.MeetingChatQueryUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-05-14
 */
@Service
@Transactional(readOnly = true)
internal class MeetingChatQuery(
    private val meetingChatQueryAdapter: MeetingChatQueryAdapter
) : MeetingChatQueryUseCase {

    // 특정 모임의 메세지들을 조회한다.
    override fun getChatting(meetingId: Long): List<MeetingChat> {
        return meetingChatQueryAdapter.findChatsByMeetingId(meetingId)
            .stream()
            .map(::MeetingChat)
            .toList()
    }
}