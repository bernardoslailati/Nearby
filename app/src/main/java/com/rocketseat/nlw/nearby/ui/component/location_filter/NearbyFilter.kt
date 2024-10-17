package com.rocketseat.nlw.nearby.ui.component.location_filter

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.rocketseat.nlw.nearby.ui.theme.Gray300
import com.rocketseat.nlw.nearby.ui.theme.Gray400
import com.rocketseat.nlw.nearby.ui.theme.GreenBase
import com.rocketseat.nlw.nearby.ui.theme.Typography

@Composable
fun NearbyFilter(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    type: NearbyFilterType,
    onClick: (isSelected: Boolean) -> Unit
) {
    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min = 36.dp),
        elevation = FilterChipDefaults.filterChipElevation(
            elevation = 8.dp,
            pressedElevation = 12.dp
        ),
        selected = isSelected,
        onClick = {
            onClick(!isSelected)
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = GreenBase
        ),
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(type.icon),
                tint = if (isSelected) Color.White else Gray400,
                contentDescription = null
            )
        },
        border = FilterChipDefaults.filterChipBorder(
            enabled = false,
            selected = isSelected,
            disabledBorderColor = Gray300,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
            selectedBorderColor = Color.Transparent
        ),
        label = {
            Text(
                text = type.description, style = Typography.bodyMedium,
                color = if (isSelected) Color.White else Gray400
            )
        }
    )
}

@Preview
@Composable
private fun LocationFilterPreview(
    @PreviewParameter(
        NearbyFilterTypePreviewParameterProvider::class
    ) filterTypeAndIsSelected: Pair<NearbyFilterType, Boolean>
) {
    val (filterType, isSelected) = filterTypeAndIsSelected
    NearbyFilter(
        type = filterType,
        isSelected = isSelected,
        onClick = {}
    )
}