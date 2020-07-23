package rama.id.newsfeed.data.common.interceptor

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.koin.core.KoinComponent
import org.koin.core.inject
import rama.id.newsfeed.BuildConfig
import rama.id.newsfeed.data.common.entity.ApiError
import rama.id.newsfeed.data.common.entity.RetrofitType
import rama.id.newsfeed.data.common.exception.network.ApiException
import rama.id.newsfeed.data.common.exception.network.ServerException
import rama.id.newsfeed.data.common.exception.network.TokenInvalidException
import java.io.IOException
import java.util.*

class ApiInterceptor(
    private val locale: Locale,
    private val type: RetrofitType
) : Interceptor, KoinComponent {
    private val gson by inject<Gson>()

    private fun makeRequest(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val authorization = "Bearer " + BuildConfig.DEFAULT_AUTH
        return original.newBuilder()
            .addHeader("Authorization", authorization)
            .method(original.method(), original.body())
            .build()
            .let(chain::proceed)
    }

    private fun handleResponse(chain: Interceptor.Chain, response: Response) = when {
        response.code() in 200..299 -> response
        response.code() == 401 -> makeRequest(chain)
            .takeIf { it.code() in 200..299 }
            ?: throw TokenInvalidException()
        response.code() >= 500 -> throw ServerException()
        else -> {
            val body = response.peekBody(Long.MAX_VALUE)
            throw ApiException(parseToApiError(body))
        }
    }

    private fun parseToApiError(body: ResponseBody) = body.string()
        .takeIf { it.isNotEmpty() }
        ?.let {
            try {
                gson.fromJson(it, ApiError::class.java)
            } catch (e: Exception) {
                e.printStackTrace()
                ApiError(ApiError.Error(500, "Unexpected response"))
            }
        }
        ?.takeIf { it.error != null }
        ?: throw IOException("Unexpected error")

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = makeRequest(chain)
        return handleResponse(chain, response)
    }
}