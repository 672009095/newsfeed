package rama.id.newsfeed.data.category.cloud.response

import com.google.gson.annotations.SerializedName

/**
 * define value return from data (response model)
 */
data class CategoryResponse (
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("category") val category : String?,
    @SerializedName("language") val language : String?,
    @SerializedName("country") val country : String
)