package io.keede.moinda.domains.chat.usecase

import io.keede.moinda.common.chat.CreateMeetingChat
import io.keede.moinda.domains.chat.domain.MeetingChat

/**
 * @author keede
 * Created on 2023-05-09
 */
interface MeetingChatCommandUseCase {

    fun create(command: Command): MeetingChat

    data class Command(
        val createMeetingChat: CreateMeetingChat,
    )

}