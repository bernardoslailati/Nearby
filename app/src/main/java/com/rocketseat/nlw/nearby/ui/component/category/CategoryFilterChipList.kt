package com.rocketseat.nlw.nearby.ui.component.category

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R

@Composable
fun CategoryFilterChipList(
    modifier: Modifier = Modifier,
    types: List<CategoryFilterChipView> = CategoryFilterChipView.entries,
    onSelectedFilterChange: (CategoryFilterChipView) -> Unit
) {
    var selectedFilterIndex by remember { mutableIntStateOf(types.firstOrNull()?.ordinal ?: -1) }

    LaunchedEffect(selectedFilterIndex) {
        val selectedFilterOrNull = types.getOrNull(selectedFilterIndex)
        selectedFilterOrNull?.let { selectedFilter ->
            onSelectedFilterChange(selectedFilter)
        }
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = types, key = { it.ordinal }) { filterType ->
            CategoryFilterChip(
                type = filterType,
                isSelected = selectedFilterIndex == filterType.ordinal,
                onClick = { isSelected ->
                    if (isSelected)
                        selectedFilterIndex = filterType.ordinal
                }
            )
        }
    }
}

@Preview
@Composable
private fun CategoryFilterChipListPreview() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.bg_map),
            contentDescription = null
        )
        CategoryFilterChipList(
            modifier = Modifier,
            onSelectedFilterChange = {
                Log.d("CategoryFilterChipListPreview", "$it")
            }
        )
    }
}