package io.keede.moinda.mapper

import org.springframework.data.domain.Page
import java.util.stream.IntStream
import kotlin.math.ceil


/**
 * 페이징 객체를 만들때 사용.
 * @author keede
 * Created on 2023-04-27
 */
abstract class Paginator<DOMAIN>(
    val page: Int, // 페이지당 데이터 개수
    val size: Int,
    val totalCount: Long, // 총 데이터 개수
    val totalPage: Int,
    val domains: List<DOMAIN>, // 전체 데이터
    open val keyword: String? = null, // 키워드
) {
    private val tempValue = ceil(this.page * 0.1).toInt() * 10
    val start = this.tempValue - 9
    val end = if(this.totalPage > this.tempValue) this.tempValue else this.totalPage
    val prev = this.start > 1
    val next = this.totalPage > this.tempValue
    val pagination: List<Int>? = IntStream.rangeClosed(this.start, this.end).boxed().toList()
}

data class PaginatedDomain<EN, DOMAIN>(
    val entitiesByPaging: Page<EN>,
    override val keyword: String? = null,
    val fn: (EN) -> DOMAIN,
) : Paginator<DOMAIN>(
    domains = entitiesByPaging.map(fn).toList(),
    totalCount = entitiesByPaging.totalElements,
    totalPage = entitiesByPaging.totalPages,
    page = entitiesByPaging.number + 1,
    size = entitiesByPaging.pageable.pageSize,
    keyword = keyword,
)