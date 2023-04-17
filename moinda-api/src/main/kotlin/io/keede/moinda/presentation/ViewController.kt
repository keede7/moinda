package io.keede.moinda.presentation

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody




@Controller
class ViewController {
    @GetMapping("/")
    fun main(): String {
        return "main"
    }

    @GetMapping("/get-load")
    @ResponseBody
    fun load(): String? {
        return "<h1>Hello, htmx</h1>"
    }
}