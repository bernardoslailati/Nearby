package com.rocketseat.nlw.nearby.common.network

import com.rocketseat.nlw.nearby.common.network.KtorHttpClient.httpClientAndroid
import com.rocketseat.nlw.nearby.data.model.Category
import com.rocketseat.nlw.nearby.data.model.Coupon
import com.rocketseat.nlw.nearby.data.model.Market
import com.rocketseat.nlw.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object NearbyRemoteDataSource {

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:3333"
    private const val LOCAL_HOST_BASE_URL = "http://192.168.1.176:3333"

    private const val BASE_URL = LOCAL_HOST_BASE_URL

    suspend fun getCategories(): Result<List<Category>> = try {
        val categories = httpClientAndroid.get("$BASE_URL/categories")

        Result.success(categories.body<List<Category>>())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets = httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}")

        Result.success(markets.body<List<Market>>())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails> = try {
        val market = httpClientAndroid.get("$BASE_URL/markets/${marketId}")

        Result.success(market.body<MarketDetails>())
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun patchCoupons(marketId: String): Result<Coupon> = try {
        val markets = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}")

        Result.success(markets.body<Coupon>())
    } catch (e: Exception) {
        Result.failure(e)
    }

}