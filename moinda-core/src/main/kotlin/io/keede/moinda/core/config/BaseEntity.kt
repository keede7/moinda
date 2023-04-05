package io.keede.moinda.core.config

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "is_delete", nullable = false)
    var deleteStatus : Boolean = false

    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    lateinit var lastModifiedAt: LocalDateTime

    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        lastModifiedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        lastModifiedAt = LocalDateTime.now()
    }

}