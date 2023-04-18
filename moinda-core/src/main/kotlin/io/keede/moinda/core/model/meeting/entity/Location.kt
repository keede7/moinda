package io.keede.moinda.core.model.meeting.entity

import io.keede.moinda.common.meeting.CreateMeeting
import org.hibernate.annotations.Comment
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * @author keede
 * Created on 2023-04-18
 */
@Embeddable
data class Location(
    @Column(name = "post_code", length = 10, nullable = false)
    @Comment("우편 번호")
    var postCode: String,

    @Column(name = "primary_address", length = 100, nullable = false)
    @Comment("기본 주소")
    var primaryAddress: String,

    @Column(name = "detail_address", length = 100, nullable = false)
    @Comment("상세 주소")
    var detailAddress: String
) {
}
