package io.keede.moinda.presentation.member.fixture

import io.keede.moinda.common.member.CreateMember

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