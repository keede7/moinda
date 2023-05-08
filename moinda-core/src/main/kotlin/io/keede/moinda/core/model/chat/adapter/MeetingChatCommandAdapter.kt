package io.keede.moinda.core.model.chat.adapter

import io.keede.moinda.core.model.chat.entity.MeetingChatJpaEntity

/**
 * @author keede
 * Created on 2023-05-08
 */
interface MeetingChatCommandAdapter {

    // TODO : 특정 모델을 받아서 변환하여 등록한다.
    fun save(): MeetingChatJpaEntity

}