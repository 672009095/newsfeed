package rama.id.newsfeed.data.common.entity

import com.google.gson.annotations.SerializedName

data class ApiError(
    @SerializedName("error") val error: Error?
) {
    data class Error(
        @SerializedName("error_code") val errorCode: Int?,
        @SerializedName("error_message") val errorMessage: String?
    )
}