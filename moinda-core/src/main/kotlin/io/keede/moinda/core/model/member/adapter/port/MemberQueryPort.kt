package io.keede.moinda.core.model.member.adapter.port

import com.querydsl.jpa.impl.JPAQueryFactory
import io.keede.moinda.core.model.member.adapter.MemberQueryAdapter
import io.keede.moinda.core.model.member.entity.MemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity
import io.keede.moinda.core.model.member.entity.QMemberJpaEntity.memberJpaEntity
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Service

/**
 * @author keede
 * Created on 2023-04-05
 */
@Service
internal class MemberQueryPort(
    private val jpaQueryFactory: JPAQueryFactory
) : MemberQueryAdapter, QuerydslRepositorySupport(QMemberJpaEntity::class.java) {

    override fun findById(memberId: Long): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .where(
                memberJpaEntity.id.eq(memberId)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException("존재하지 않는 사용자입니다.")
    }

    // 연관관계 정보를 가져와서 사용할떄 쓴다.
    override fun findWithFetch(memberId: Long): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .leftJoin(memberJpaEntity.meetingJpaEntity).fetchJoin()
            .where(
                memberJpaEntity.id.eq(memberId)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException("존재하지 않는 사용자입니다.")
    }

    // TODO : 용도에 따라서 비슷한 메서드를 만들 수 도 있다.
    override fun findByEmail(email: String): MemberJpaEntity {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .leftJoin(memberJpaEntity.meetingJpaEntity).fetchJoin()
            .where(
                memberJpaEntity.email.eq(email)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetchOne() ?: throw RuntimeException("등록된 이메일이 아닙니다.")
    }

    // 회원 가입 시 이메일 중복검증에 사용한다.
    override fun existMemberByEmail(email: String): Boolean {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .where(
                memberJpaEntity.email.eq(email)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetch()
            .isNotEmpty()
            .also {
                if(it) throw RuntimeException("이미 등록된 이메일입니다.")
            }
    }

    // 특정 모임에 참여하고 있는 참여자들 목록 조회할 때 사용한다. (상세 모임 조회 하단)
    override fun findParticipateInMeetMembers(meetingId: Long): List<MemberJpaEntity> {
        return jpaQueryFactory
            .selectFrom(memberJpaEntity)
            .leftJoin(memberJpaEntity.meetingJpaEntity).fetchJoin()
            .where(
                memberJpaEntity.meetingJpaEntity.id.eq(meetingId)
                    .and(memberJpaEntity.deleteStatus.isFalse)
            )
            .fetch()
    }
}