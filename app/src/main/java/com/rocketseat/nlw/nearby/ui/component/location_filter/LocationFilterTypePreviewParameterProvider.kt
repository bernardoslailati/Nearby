package com.rocketseat.nlw.nearby.ui.component.location_filter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LocationFilterTypePreviewParameterProvider :
    PreviewParameterProvider<Pair<LocationFilterType, Boolean>> {
    override val values: Sequence<Pair<LocationFilterType, Boolean>>
        get() {
            val notSelectedFilters: List<Pair<LocationFilterType, Boolean>> =
                LocationFilterType.entries.associateWith { false }
                    .toList()
            val selectedFilters: List<Pair<LocationFilterType, Boolean>> =
                LocationFilterType.entries.associateWith { true }
                    .toList()

            return sequenceOf(
                *((notSelectedFilters + selectedFilters)
                    .sortedBy { (filterType, _) -> filterType.ordinal })
                    .toTypedArray()
            )
        }
}