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
import divkit.dsl.core.bind
import divkit.dsl.core.expression
import divkit.dsl.edgeInsets
import divkit.dsl.fixedSize
import divkit.dsl.gallery
import divkit.dsl.input
import divkit.dsl.matchParentSize
import divkit.dsl.medium
import divkit.dsl.render
import divkit.dsl.row
import divkit.dsl.scope.DivScope
import divkit.dsl.solidBackground
import divkit.dsl.stroke
import divkit.dsl.text
import divkit.dsl.vertical
import divkit.dsl.visibilityAction
import divkit.dsl.wrapContentSize

object FeedRenderer {

    const val SEARCH_TEXT_VARIABLE = "search_text"

    fun DivScope.render(data: FeedViewModel): Div {
        return container(
            height = matchParentSize(),
            width = matchParentSize(),
            items = listOf(
                renderToolbar(data.title),
                renderContent(data)
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

    private fun DivScope.renderContent(data: FeedViewModel) = container(
        contentAlignmentHorizontal = center,
        width = matchParentSize(),
        height = matchParentSize(),
        margins = edgeInsets(
            top = 8,
            bottom = 8,
            left = 8,
            right = 8,
        ),
        items = listOf(
            renderInput(),
            renderGallery(data.items)
        )
    )

    private fun DivScope.renderInput() = input(
        textVariable = SEARCH_TEXT_VARIABLE,
        height = fixedSize(56),
        width = matchParentSize(),
        alignmentHorizontal = center,
        alignmentVertical = center,
        fontSize = 16,
        fontWeight = medium,
        textColor = color("#000000"),
        hintColor = color("#888888"),
        textAlignmentVertical = center,
        lineHeight = 22,
        background = solidBackground(color = color("#fff")).asList(),
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
        hintText = "Отфильтровать результаты",
        visibilityActions = listOf(
            visibilityAction(
                logId = "on_page_visible",
                url = Url.create("div-action://timer?id=update_timer&action=start"),
            )
        )
    )

    private fun DivScope.renderGallery(items: List<SearchItemViewModel>) = gallery(
        width = matchParentSize(),
        height = matchParentSize(),
        columnCount = 2,
        orientation = vertical,
        items = items.map { item ->
            render(
                SearchItemTemplate.template,
                SearchItemTemplate.imageUrlRef bind Url.create(item.imgUrl),
                SearchItemTemplate.descriptionRef bind item.description,
                SearchItemTemplate.visibilityRef bind expression("@{contains(trim(toLowerCase('${item.description}')), trim(toLowerCase($SEARCH_TEXT_VARIABLE))) ? 'visible' : 'gone'}"),
            )
        }
    )
}
