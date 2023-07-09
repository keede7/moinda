package io.keede.moinda.core.model.group.port.adapter

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.group.port.GroupQueryPort
import io.keede.moinda.core.model.group.entity.GroupJpaEntity
import io.keede.moinda.core.model.group.entity.QGroupJpaEntity
import io.keede.moinda.core.model.group.entity.QGroupJpaEntity.groupJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author keede
 * Created on 2023-03-25
 */
@Service
@Transactional(readOnly = true)
internal class GroupQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : GroupQueryPort, QuerydslRepositorySupport(QGroupJpaEntity::class.java) {

    override fun findById(groupId: Long): GroupJpaEntity {
        return jpaQueryFactory
            .selectFrom(groupJpaEntity)
            .where(
                groupJpaEntity.id.eq(groupId)
                    .and(groupJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException()
    }

    override fun findGroups(): List<GroupJpaEntity> {
        return jpaQueryFactory
            .selectFrom(groupJpaEntity)
            .where(groupJpaEntity.deleteStatus.isFalse)
            .fetch()
    }
}

