package io.keede.moinda.presentation.group

import io.keede.moinda.domains.group.domain.Group
import io.keede.moinda.domains.group.usecase.GroupCommandUseCase
import io.keede.moinda.domains.group.usecase.GroupQueryUseCase
import io.keede.moinda.domains.member.usecase.MemberCommandUseCase
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author keede
 * Created on 2023-03-25
 */
@RestController
@RequestMapping("/api/group")
class GroupRestController(
    private val groupCommandUseCase: GroupCommandUseCase,
    private val groupQueryUseCase: GroupQueryUseCase,
    private val memberCommandUseCase: MemberCommandUseCase
) {

    /**
     * 반환타입 메서드로 let을 통해 여러 문법으로 변환이 가능하다.
     * .let(Group::toGroupResponseDto) -> Import시 가능.
     * .let { group -> GroupResponseDto(group....) }
     * .toGroupResponseDto() -> Import시 가능.
     */
    @PostMapping
    fun create(
        @RequestBody @Valid createGroupDto: CreateGroupDto
    ): GroupResponseDto =
        groupCommandUseCase.create(
            GroupCommandUseCase.Command(createGroupDto.toDomain())
        ).let(Group::toGroupResponseDto)

    @GetMapping("/{groupId}")
    fun getOne(
        @PathVariable groupId: Long
    ): GroupResponseDto =
        groupQueryUseCase.findById(
            GroupQueryUseCase.Query(groupId)
        ).let(Group::toGroupResponseDto)

    @GetMapping
    fun getList(): List<GroupResponseDto>
    = toGroupResponseDtoList(groups = groupQueryUseCase.getGroups())

    @PostMapping("/participate")
    fun participate(
        @RequestBody @Valid participateDto: ParticipateDto
    ) = memberCommandUseCase.participate(
        MemberCommandUseCase.Participate(
            participateDto.groupId,
            participateDto.memberId
        )
    )

}