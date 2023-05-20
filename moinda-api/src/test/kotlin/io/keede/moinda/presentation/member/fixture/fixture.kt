package io.keede.moinda.presentation.member.fixture

import io.keede.moinda.common.member.CreateMember
import io.keede.moinda.presentation.member.LoginRequestDto

/**
 * 05-23 ~ 미사용
 * @author keede
 * Created on 2023-04-08
 */
fun ofCreateMember(
    name: String,
    email: String,
    password: String,
    introduce: String? = null
): CreateMember = CreateMember(
    name,
    email,
    password,
    introduce
)

fun ofLoginRequestDto(
    email: String,
    password: String,
) : LoginRequestDto = LoginRequestDto(
    email,
    password
)