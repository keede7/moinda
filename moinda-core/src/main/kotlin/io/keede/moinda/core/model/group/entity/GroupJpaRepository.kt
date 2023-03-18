package io.keede.moinda.core.model.group.entity

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author keede
 * Created on 2023-03-18
 */
internal interface GroupJpaRepository : JpaRepository<GroupJpaEntity, Long> {
}