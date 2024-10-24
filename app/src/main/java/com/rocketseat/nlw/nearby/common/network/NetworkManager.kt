package com.rocketseat.nlw.nearby.common.network

import com.rocketseat.nlw.nearby.common.network.KtorHttpClient.httpClientAndroid
import com.rocketseat.nlw.nearby.data.model.Category
import io.ktor.client.call.body
import io.ktor.client.request.get

object NetworkManager {

    private const val LOCAL_HOST_BASE_URL = "http://10.0.2.2:3333"

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories = httpClientAndroid.get("$LOCAL_HOST_BASE_URL/categories")

        Result.success(categories.body<List<Category>>())
    } catch (e: Exception) {
        Result.failure(e)
    }

}