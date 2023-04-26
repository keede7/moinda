package io.keede.moinda.mapper

import org.springframework.data.domain.Page


/**
 * 페이징 객체를 만들때 사용.
 * @author keede
 * Created on 2023-04-27
 */
abstract class Paginator<DOMAIN>(
    val pageSize: Int, // 페이지당 데이터 개수
    val totalCount: Int, // 총 데이터 개수
    val domains: List<DOMAIN>, // 전체 데이터
//    protected val pagination: List<Int>,
    open val keyword: String? = null, // 키워드
)

data class PaginatedDomain<EN, DOMAIN>(
    val entitiesByPaging: Page<EN>,
    override val keyword: String? = null,
    val fn: (EN) -> DOMAIN,
) : Paginator<DOMAIN>(
    domains = entitiesByPaging.map(fn).toList(),
    totalCount = entitiesByPaging.size,
    pageSize = entitiesByPaging.number + 1,
    keyword = keyword
)