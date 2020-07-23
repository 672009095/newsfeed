package rama.id.newsfeed.data.common.exception.network

import rama.id.newsfeed.data.common.entity.ApiError
import java.io.IOException

class ApiException(
    val apiError: ApiError
) : IOException(apiError.error?.errorMessage ?: "Unexpected error")
