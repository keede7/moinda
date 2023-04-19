package io.keede.moinda.util

import java.util.function.Function

/**
 * @author keede
 * Created on 2023-04-19
 */

object ListExtensions

fun <DOMAIN, DTO> List<DOMAIN>.toResponseDtoList(
    fn: Function<DOMAIN, DTO>
): List<DTO> =
    this.stream()
        .map(fn)
        .toList()
