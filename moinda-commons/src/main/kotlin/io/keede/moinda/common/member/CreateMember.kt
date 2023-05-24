package io.keede.moinda.common.member

/**
 * @author keede
 * Created on 2023-04-05
 */
data class CreateMember(
    val name: String,
    val email: String,
    val password: String,
    val introduce: String?,
) {
    constructor(
        name: String,
        email: String,
    ) : this(name, email, "1212", "테스트")
}