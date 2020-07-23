package rama.id.newsfeed.data.module

import org.koin.core.qualifier.named
import org.koin.dsl.module
import rama.id.newsfeed.BuildConfig
import rama.id.newsfeed.data.category.CategoryApi
import rama.id.newsfeed.data.common.entity.RetrofitType
import retrofit2.Retrofit

/**
 * this file is for grouping call API builder to retrofit
 * can use type based on retrofit type (use token or not use token)
 */
val apiModule = module{
    single{
        get<Retrofit.Builder>(named(RetrofitType.USE_TOKEN.value))
            .baseUrl(BuildConfig.BASE_URL_NEWS)
            .build()
            .create(CategoryApi::class.java)
    }
}