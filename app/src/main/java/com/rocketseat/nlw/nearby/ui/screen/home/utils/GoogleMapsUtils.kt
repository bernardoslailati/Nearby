package com.rocketseat.nlw.nearby.ui.screen.home.utils

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import com.google.android.gms.maps.model.LatLng
import kotlin.math.roundToInt

fun Density.dpToPx(dp: Dp): Int = this.run { dp.toPx() }.roundToInt()

fun findSouthwestPoint(points: List<LatLng>): LatLng {
    // Caso a lista esteja vazia, retorna nulo
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    // Inicializa o ponto mais ao sudoeste como o primeiro da lista
    var southwestPoint = points[0]

    // Itera sobre a lista para encontrar o ponto mais ao sudoeste
    for (point in points) {
        // Verifica se o ponto atual é mais ao sudoeste:
        // 1. Se tiver latitude menor (mais ao sul)
        // 2. Em caso de mesma latitude, verifica se a longitude é menor (mais a oeste)
        if (point.latitude < southwestPoint.latitude ||
            (point.latitude == southwestPoint.latitude && point.longitude < southwestPoint.longitude)
        ) {
            southwestPoint = point
        }
    }

    return southwestPoint
}

fun findNortheastPoint(points: List<LatLng>): LatLng {
    // If the list is empty, return null
    if (points.isEmpty()) return LatLng(0.0, 0.0)

    // Initialize the northeasternmost point as the first point in the list
    var northeastPoint = points[0]

    // Iterate over the list to find the northeasternmost point
    for (point in points) {
        // Check if the current point is more northeast:
        // 1. If it has a higher latitude (more north)
        // 2. In case of the same latitude, check if it has a higher longitude (more east)
        if (point.latitude > northeastPoint.latitude ||
            (point.latitude == northeastPoint.latitude && point.longitude > northeastPoint.longitude)
        ) {
            northeastPoint = point
        }
    }

    return northeastPoint
}