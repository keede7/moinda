package io.keede.moinda.core.model.member.entity

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author keede
 * Created on 2023-04-05
 */
internal interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long> {
}