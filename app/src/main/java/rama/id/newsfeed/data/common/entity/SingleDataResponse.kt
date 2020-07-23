package rama.id.newsfeed.data.common.entity

import com.google.gson.annotations.SerializedName

/**
 * this class is Base for return single data or items from json
 */
data class SingleDataResponse <T>(
    @SerializedName("articles") val items: T?
) {
    fun getOrThrow() = items ?: throw NoSuchElementException("Require value itemes")
}