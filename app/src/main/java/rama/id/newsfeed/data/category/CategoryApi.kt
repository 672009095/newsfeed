package rama.id.newsfeed.data.category

import rama.id.newsfeed.data.category.cloud.response.CategoryResponse
import rama.id.newsfeed.data.common.entity.ListDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * interface for grouping endpoint based on per feature
 */
interface CategoryApi {
    @GET("v2/sources")
    suspend fun getListOfSearch(
        @Query("category") category:String?
    ): ListDataResponse<CategoryResponse>
}