package io.keede.moinda.common

data class PageResponse<DTO>(
     val page: Int, // 페이지당 데이터 개수
     val size: Int,
     val totalPage: Int,
     val totalCount: Long, // 총 데이터 개수
     val keyword: String? = null, // 키워드
     val start: Int,
     val end: Int,
     val prev: Boolean,
     val next: Boolean,
     val domains: List<DTO>, // 전체 데이터
     val pagination: List<Int>?
)

