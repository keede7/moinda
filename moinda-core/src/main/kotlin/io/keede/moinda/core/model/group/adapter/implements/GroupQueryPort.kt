package io.keede.moinda.core.model.group.adapter.implements

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.group.adapter.GroupQueryAdapter
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
) : GroupQueryAdapter, QuerydslRepositorySupport(QGroupJpaEntity::class.java) {

    override fun findById(groupId: Long): GroupJpaEntity {
        return jpaQueryFactory.selectFrom(groupJpaEntity)
            .where(groupJpaEntity.id.eq(groupId))
            .fetchOne() ?: throw RuntimeException()
    }
}

