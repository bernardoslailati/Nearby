package com.rocketseat.nlw.nearby.ui.component.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CategoryFilterChipPreviewParameterProvider :
    PreviewParameterProvider<Pair<CategoryFilterChipView, Boolean>> {
    override val values: Sequence<Pair<CategoryFilterChipView, Boolean>>
        get() {
            val notSelectedFilters: List<Pair<CategoryFilterChipView, Boolean>> =
                CategoryFilterChipView.entries.associateWith { false }
                    .toList()
            val selectedFilters: List<Pair<CategoryFilterChipView, Boolean>> =
                CategoryFilterChipView.entries.associateWith { true }
                    .toList()

            return sequenceOf(
                *((notSelectedFilters + selectedFilters)
                    .sortedBy { (filterType, _) -> filterType.ordinal })
                    .toTypedArray()
            )
        }
}