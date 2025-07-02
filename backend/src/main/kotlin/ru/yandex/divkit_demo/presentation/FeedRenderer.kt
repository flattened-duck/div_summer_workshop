package ru.yandex.divkit_demo.presentation

import divkit.dsl.Div
import divkit.dsl.Url
import divkit.dsl.action
import divkit.dsl.asList
import divkit.dsl.bold
import divkit.dsl.border
import divkit.dsl.center
import divkit.dsl.color
import divkit.dsl.container
import divkit.dsl.edgeInsets
import divkit.dsl.fixedSize
import divkit.dsl.matchParentSize
import divkit.dsl.medium
import divkit.dsl.row
import divkit.dsl.scope.DivScope
import divkit.dsl.solidBackground
import divkit.dsl.stroke
import divkit.dsl.text
import divkit.dsl.wrapContentSize

object FeedRenderer {

    fun DivScope.render(data: FeedViewModel): Div {
        return container(
            height = matchParentSize(),
            width = matchParentSize(),
            items = listOf(
                renderToolbar(data.title),
            )
        )
    }

    private fun DivScope.renderToolbar(title: String) = row(
        height = fixedSize(56),
        width = matchParentSize(),
        contentAlignmentVertical = center,
        border = border(
            hasShadow = true,
        ),
        paddings = edgeInsets(
            left = 16,
            right = 16,
        ),
        items = listOf(
            renderTitle(title),
            renderTrailingButton()
        )
    )

    private fun DivScope.renderTitle(title: String) = text(
        text = title,
        height = fixedSize(56),
        width = matchParentSize(),
        textAlignmentVertical = center,
        fontSize = 24,
        fontWeight = bold,
    )

    private fun DivScope.renderTrailingButton() = text(
        text = "Обновить",
        height = wrapContentSize(),
        width = wrapContentSize(),
        fontSize = 16,
        fontWeight = medium,
        textColor = color("#FFFFFF"),
        textAlignmentVertical = center,
        textAlignmentHorizontal = center,
        background = solidBackground(color = color("#bb0707")).asList(),
        paddings = edgeInsets(
            left = 10,
            top = 8,
            right = 10,
            bottom = 8,
        ),
        border = border(
            cornerRadius = 16,
            stroke = stroke(
                color = color("#fc0"),
                width = 2.0,
            )
        ),
        actions = listOf(
            action(
                logId = "update_page",
                url = Url.create("sample-action://update"),
            )
        )
    )
}
