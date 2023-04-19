package io.keede.moinda.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @author keede
 * Created on 2023-04-19
 */

object DateFormatter {
    const val FULL_PATTERN = "yyyy-MM-dd HH:mm"
}

fun LocalDateTime.toFullPattern(): String  =
    this.format(DateTimeFormatter.ofPattern( DateFormatter.FULL_PATTERN ))