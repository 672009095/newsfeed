package rama.id.newsfeed.data.popularnews.cloud.response

import com.google.gson.annotations.SerializedName
import rama.id.newsfeed.domain.popularnews.Source

data class SourceResponse (
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)
fun toSource(value: SourceResponse) = Source(
    value.id ?: throw NoSuchElementException("Require value id"),
    value.name ?: throw NoSuchElementException("require value id")
)
