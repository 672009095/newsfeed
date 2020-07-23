package rama.id.newsfeed.data.popularnews

import rama.id.newsfeed.data.popularnews.cloud.PopularApi
import rama.id.newsfeed.domain.common.entity.Pagination
import rama.id.newsfeed.domain.popularnews.PopularNews
import rama.id.newsfeed.domain.popularnews.PopularNewsRepository

class PopularDataRepository (private val popularApi: PopularApi) : PopularNewsRepository {

    override suspend fun getListOfPopular(q: String?, page: Pagination
    ): List<PopularNews> = popularApi.getListOfPopular(
        q,page.page
    ).getOrThrow().map(::transform)


}