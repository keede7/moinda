package io.keede.moinda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoindaApplication

fun main(args: Array<String>) {
    runApplication<MoindaApplication>(*args)
}
