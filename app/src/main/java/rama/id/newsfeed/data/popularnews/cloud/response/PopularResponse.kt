package rama.id.newsfeed.data.popularnews.cloud.response

import com.google.gson.annotations.SerializedName


/**
 * define value return from data (response model)
 */
data class PopularResponse (
    @SerializedName("sourceResponse") val source: SourceResponse?,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: String?,
    @SerializedName("content")val content: String?
)