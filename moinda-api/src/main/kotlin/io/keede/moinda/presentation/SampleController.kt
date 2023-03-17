package io.keede.moinda.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class SampleController{

    @GetMapping("/sample")
    fun sample(): String {
        return "sample";
    }
}