package rama.id.newsfeed.data.popularnews.cloud

import rama.id.newsfeed.data.common.entity.ListDataResponse
import rama.id.newsfeed.data.popularnews.cloud.response.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * interface for grouping endpoint based on per feature
 */
interface PopularApi {
    @GET("v2/top-headlines")
    suspend fun getListOfPopular(
        @Query("q") q:String?,
        @Query("page") page:Int?
    ): ListDataResponse<PopularResponse>
}