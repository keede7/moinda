package io.keede.moinda.common.group

/**
 * 그룹을 생성할 때 도메인 모델로 사용된다.
 * @author keede
 * Created on 2023-03-18
 */
data class CreateGroup(
    val name: String,
    val description: String?,
    val capacity: Int
)
