package com.rocketseat.nlw.nearby.ui.component.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.data.model.mock.mockCategories

class CategoryFilterChipPreviewParameterProvider :
    PreviewParameterProvider<Pair<Category, Boolean>> {
    override val values: Sequence<Pair<Category, Boolean>>
        get() {
            val notSelectedFilters: List<Pair<Category, Boolean>> =
                mockCategories.associateWith { false }
                    .toList()
            val selectedFilters: List<Pair<Category, Boolean>> =
                mockCategories.associateWith { true }
                    .toList()

            return sequenceOf(
                *((notSelectedFilters + selectedFilters)
                    .sortedBy { (category, _) -> category.name })
                    .toTypedArray()
            )
        }
}