package com.rocketseat.nlw.nearby.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rocketseat.nlw.nearby.R

val rubikFontFamily = FontFamily(
    Font(R.font.rubik, FontWeight.Normal),
    Font(R.font.rubik_medium, FontWeight.Medium),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_semibold, FontWeight.SemiBold)
)

val Typography = Typography(
    // "Title XI"
    headlineLarge = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    // "Title Lg"
    headlineMedium = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    // "Title Md"
    headlineSmall = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    // "Text Sm"
    titleLarge = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    // "Text Md"
    bodyLarge = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // "Text Sm"
    bodyMedium = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    // "Text Xs"
    bodySmall = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    // "Action"
    labelLarge = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    // "Subtitle"
    labelMedium = TextStyle(
//        fontFamily = rubikFontFamily,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

