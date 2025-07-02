package ru.yandex.divkit_demo.presentation

import divkit.dsl.Container
import divkit.dsl.Template
import divkit.dsl.Url
import divkit.dsl.Visibility
import divkit.dsl.asList
import divkit.dsl.aspect
import divkit.dsl.border
import divkit.dsl.center
import divkit.dsl.color
import divkit.dsl.container
import divkit.dsl.core.reference
import divkit.dsl.defer
import divkit.dsl.dimension
import divkit.dsl.edgeInsets
import divkit.dsl.fixedSize
import divkit.dsl.image
import divkit.dsl.point
import divkit.dsl.shadow
import divkit.dsl.solidBackground
import divkit.dsl.template
import divkit.dsl.text
import divkit.dsl.top
import divkit.dsl.vertical

object SearchItemTemplate {

    val imageUrlRef = reference<Url>("imageUrlRef")
    val descriptionRef = reference<String>("descriptionRef")
    val visibilityRef = reference<Visibility>("visibilityRef")

    val template: Template<Container> by lazy {
        template(name = "contentFeedTemplatedInvertedAdvertisingPost") {
            container(
                orientation = vertical,
                margins = edgeInsets(
                    left = 12,
                    right = 12,
                    top = 12,
                    bottom = 12,
                ),
                background = solidBackground(color = color("#FFF")).asList(),
                width = fixedSize(150),
                border = border(
                    cornerRadius = 14,
                    shadow = shadow(
                        offset = point(
                            x = dimension(1.0),
                            y = dimension(2.0),
                        ),
                        color = color("#000"),
                    ),
                    hasShadow = true,
                ),
                items = listOf(
                    image(
                        aspect = aspect(
                            ratio = 0.75,
                        ),
                        contentAlignmentVertical = top,
                    ).defer(
                        imageUrl = imageUrlRef
                    ),
                    text(
                        margins = edgeInsets(
                            left = 12,
                            right = 12,
                            top = 12,
                            bottom = 12,
                        ),
                        maxLines = 3,
                        fontSize = 12,
                        textColor = color("#333"),
                        textAlignmentHorizontal = center,
                    ).defer(
                        text = descriptionRef,
                    )
                )
            ).defer(
                visibility = visibilityRef,
            )
        }
    }
}
