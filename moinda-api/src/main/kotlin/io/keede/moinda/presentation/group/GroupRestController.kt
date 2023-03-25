package io.keede.moinda.presentation.group

import io.keede.moinda.domains.group.domain.Group
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import io.keede.moinda.presentation.group.*

/**
 * @author keede
 * Created on 2023-03-25
 */
@RestController
@RequestMapping("/api/group")
class GroupRestController(
    private val groupCommandUseCase: GroupCommandUseCase
) {

    /**
     * 반환타입 메서드로 let을 통해 여러 문법으로 변환이 가능하다.
     * .let(Group::toGroupResponseDto) -> Import시 가능.
     * .let { group -> GroupResponseDto(group....) }
     * .toGroupResponseDto() -> Import시 가능.
     */
    @PostMapping
    fun create(
        @RequestBody createGroupDto: CreateGroupDto
    ): GroupResponseDto =
        groupCommandUseCase.create(
            GroupCommandUseCase.Command(createGroupDto.toDomain())
        ).let(Group::toGroupResponseDto)

}