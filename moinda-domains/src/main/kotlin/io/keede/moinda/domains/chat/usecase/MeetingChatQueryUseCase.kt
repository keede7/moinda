package io.keede.moinda.domains.chat.usecase

import io.keede.moinda.domains.chat.domain.MeetingChat

/**
 * @author keede
 * Created on 2023-05-14
 */
interface MeetingChatQueryUseCase {

    // 특정 모임의 메세지들을 조회한다.
    fun getChatting(meetingId: Long): List<MeetingChat>

}