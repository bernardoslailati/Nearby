package com.rocketseat.nlw.nearby.ui.component.category

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class CategoryFilterChipPreviewParameterProvider :
    PreviewParameterProvider<Pair<CategoryFilterChipType, Boolean>> {
    override val values: Sequence<Pair<CategoryFilterChipType, Boolean>>
        get() {
            val notSelectedFilters: List<Pair<CategoryFilterChipType, Boolean>> =
                CategoryFilterChipType.entries.associateWith { false }
                    .toList()
            val selectedFilters: List<Pair<CategoryFilterChipType, Boolean>> =
                CategoryFilterChipType.entries.associateWith { true }
                    .toList()

            return sequenceOf(
                *((notSelectedFilters + selectedFilters)
                    .sortedBy { (filterType, _) -> filterType.ordinal })
                    .toTypedArray()
            )
        }
}