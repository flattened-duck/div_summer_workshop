package ru.yandex.divkit_demo

import divkit.dsl.Divan
import divkit.dsl.data
import divkit.dsl.divan
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.yandex.divkit_demo.presentation.FeedRenderer
import ru.yandex.divkit_demo.presentation.FeedViewModel

@RestController
@RequestMapping("/demo") // Listening at localhost:8080/demo
class DemoScreenController {

    @GetMapping
    fun getDemoScreen(
        @RequestHeader(name = "Accept-Language") lang: String?,
        @RequestParam(name = "username") username: String?,
    ): ResponseEntity<Divan> {
        val translations = Translations.fromLang(lang)
        return ResponseEntity(
            renderDemoScreen(translations, username),
            HttpStatus.OK
        )
    }

    private fun renderDemoScreen(translations: Translations, username: String?): Divan {
        val message = mapMessage(username, translations)
        val data = FeedViewModel(
            title = message,
        )
        return divan {
            data(
                logId = "demo",
                div = with(FeedRenderer) {
                    render(data)
                },
            )
        }
    }

    private fun mapMessage(
        username: String?,
        translations: Translations,
    ): String {
        return if (username != null) {
            translations["hello"].format(username)
        } else {
            translations["who"]
        }
    }
}
