package ru.yandex.divkit_demo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/demo") // Listening at localhost:8080/demo
class DemoScreenController {

    @GetMapping
    fun getDemoScreen(): ResponseEntity<String> {
        return ResponseEntity(
            "Test",
            HttpStatus.OK
        )
    }
}
