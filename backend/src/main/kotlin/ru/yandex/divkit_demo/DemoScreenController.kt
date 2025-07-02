package ru.yandex.divkit_demo

import divkit.dsl.Divan
import divkit.dsl.Url
import divkit.dsl.action
import divkit.dsl.data
import divkit.dsl.divan
import divkit.dsl.stringVariable
import divkit.dsl.timer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.yandex.divkit_demo.presentation.FeedRenderer
import ru.yandex.divkit_demo.presentation.FeedViewModel
import ru.yandex.divkit_demo.presentation.SearchItemViewModel

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
        val items = fetchSearchItems()
        val data = FeedViewModel(
            title = message,
            items = items
        )
        return divan {
            data(
                logId = "demo",
                div = with(FeedRenderer) {
                    render(data)
                },
                variables = listOf(
                    stringVariable(
                        name = FeedRenderer.SEARCH_TEXT_VARIABLE,
                        value = "",
                    )
                ),
                timers = listOf(
                    timer(
                        id = "update_timer",
                        tickInterval = 3000,
                        tickActions = listOf(
                            action(
                                logId = "update_page",
                                url = Url.create("sample-action://update"),
                            )
                        )
                    )
                )
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

    private fun fetchSearchItems(): List<SearchItemViewModel> {
        return listOf(
            SearchItemViewModel(
                description = "Облако с ногами бежит под дождем",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/15067795/img_0d163c3a4d4111f0899a2aa11dc6dfe1/orig",
                postUrl = "https://shedevrum.ai/post/0d163c3a4d4111f0899a2aa11dc6dfe1/"
            ),
            SearchItemViewModel(
                description = "Волнистый попугайчик, сине-зеленого окраса, в костюме зайчика, нарисованный масляными красками, а на фоне облака из карамели",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/14919379/img_020e50fd50e111f0ae9dda33849e98dc/orig",
                postUrl = "https://shedevrum.ai/post/020e50fd50e111f0ae9dda33849e98dc/"
            ),
            SearchItemViewModel(
                description = "Beautiful landscape, unusual, in pastel colors, minimalistic, scalaria fish, quiet",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/14797701/img_04a236604c6a11f0bb03724f6c58f87a/orig",
                postUrl = "https://shedevrum.ai/post/04a236604c6a11f0bb03724f6c58f87a/"
            ),
            SearchItemViewModel(
                description = "Маленький прозрачный монстрик из капельки воды на пеньке, серый фон, вид снизу, фентези, сияние, алмазная пыль, мультяшно, мило",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/14887244/img_00edf0734eaf11f0a695e2eafd4d8d77/orig",
                postUrl = "https://shedevrum.ai/post/00edf0734eaf11f0a695e2eafd4d8d77/"
            ),
            SearchItemViewModel(
                description = "Пейзаж вытекает на стол из упавшей банки краски на белом фоне",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/15067795/img_581a57d250f611f0bf5de62a8f27b445/orig",
                postUrl = "https://shedevrum.ai/post/581a57d250f611f0bf5de62a8f27b445/"
            ),
            SearchItemViewModel(
                description = "Очень красивый зонтик, солнечный, солнечные цветы, чашка с блюдцем, 5D формат, как солнце, большого размера, с красивым рисунком, цифровая графика",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/15240847/img_07e2f7c24e1211f09c645a1b9081af39/orig",
                postUrl = "https://shedevrum.ai/post/07e2f7c24e1211f09c645a1b9081af39/"
            ),
            SearchItemViewModel(
                description = "Эндре Пеновац, пушистик ветер",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/16449972/img_0734827c4c8b11f0a3d1564bb4657f04/orig",
                postUrl = "https://shedevrum.ai/post/0734827c4c8b11f0a3d1564bb4657f04/"
            ),
            SearchItemViewModel(
                description = "Вязанная пушистая кошка летит на воздушном пушистом шаре",
                imgUrl = "https://avatars.mds.yandex.net/get-shedevrum/16295926/img_077cf9c1513411f081ac5e65fce3d180/orig",
                postUrl = "https://shedevrum.ai/post/077cf9c1513411f081ac5e65fce3d180/"
            )
        )
    }
}
