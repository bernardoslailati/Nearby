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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.R
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.data.model.mock.mockCategories

@Composable
fun CategoryFilterChipList(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onSelectedCategoryChange: (Category) -> Unit
) {
    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id.orEmpty()) }

    LaunchedEffect(selectedCategoryId) {
        val selectedCategoryOrNull = categories.find { it.id == selectedCategoryId }
        selectedCategoryOrNull?.let { selectedCategory ->
            onSelectedCategoryChange(selectedCategory)
        }
    }

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categories, key = { it.id }) { category ->
            CategoryFilterChip(
                category = category,
                isSelected = selectedCategoryId == category.id,
                onClick = { isSelected ->
                    if (isSelected)
                        selectedCategoryId = category.id
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
            categories = mockCategories,
            onSelectedCategoryChange = {
                Log.d("CategoryFilterChipListPreview", "$it")
            }
        )
    }
}