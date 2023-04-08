package io.keede.moinda.presentation.member.fixture

import io.keede.moinda.common.member.CreateMember

fun ofCreateMember(
    name: String,
    email: String,
    introduce: String?
): CreateMember = CreateMember(
    name,
    email,
    introduce
)