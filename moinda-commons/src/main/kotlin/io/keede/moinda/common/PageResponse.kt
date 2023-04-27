package io.keede.moinda.common

data class PageResponse<DTO>(
     val pageSize: Int, // 페이지당 데이터 개수
     val totalCount: Int, // 총 데이터 개수
     val domains: List<DTO>, // 전체 데이터
     val keyword: String? = null, // 키워드
)

