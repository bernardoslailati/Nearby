package com.rocketseat.nlw.nearby.ui.component.location_filter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NearbyFilterTypePreviewParameterProvider :
    PreviewParameterProvider<Pair<NearbyFilterType, Boolean>> {
    override val values: Sequence<Pair<NearbyFilterType, Boolean>>
        get() {
            val notSelectedFilters: List<Pair<NearbyFilterType, Boolean>> =
                NearbyFilterType.entries.associateWith { false }
                    .toList()
            val selectedFilters: List<Pair<NearbyFilterType, Boolean>> =
                NearbyFilterType.entries.associateWith { true }
                    .toList()

            return sequenceOf(
                *((notSelectedFilters + selectedFilters)
                    .sortedBy { (filterType, _) -> filterType.ordinal })
                    .toTypedArray()
            )
        }
}