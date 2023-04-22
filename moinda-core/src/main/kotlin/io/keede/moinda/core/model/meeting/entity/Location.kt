package io.keede.moinda.core.model.meeting.entity

import io.keede.moinda.common.meeting.CreateMeeting
import org.hibernate.annotations.Comment
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 *
 *  Kakao Map API 에 맞추는 것으로 변경
 * @author keede
 * Created on 2023-04-18
 */
@Embeddable
data class Location(

    @Column(name = "primary_address", length = 100, nullable = false)
    @Comment("기본 주소")
    var primaryAddress: String,

    @Column(name = "place_name", length = 100, nullable = false)
    @Comment("장소명칭")
    var placeName: String
) {
}
