package io.keede.moinda.core.config

import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "is_delete", nullable = false)
    var deleteStatus : Boolean = false

    @Column(updatable = false)
    lateinit var createdAt: LocalDateTime

    lateinit var lastModifiedAt: LocalDateTime

    @PrePersist
    fun prePersist() {
        val now = getNow()
        createdAt = now
        lastModifiedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        lastModifiedAt = getNow()
    }

    fun remove() {
        this.deleteStatus = true
    }

    private fun getNow(): LocalDateTime {
        return LocalDateTime.now().withNano(0)
    }
}