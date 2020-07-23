package rama.id.newsfeed.domain.popularnews

import rama.id.newsfeed.domain.common.entity.Pagination

interface PopularNewsRepository {
    suspend fun getListOfPopular(q:String?,
                                page:Pagination): List<PopularNews>
}