package rama.id.newsfeed.domain.popularnews.usecase

import rama.id.newsfeed.domain.common.UseCaseConstant
import rama.id.newsfeed.domain.common.UseCaseConstant.sources
import rama.id.newsfeed.domain.common.base.BaseUseCase
import rama.id.newsfeed.domain.common.entity.Pagination
import rama.id.newsfeed.domain.popularnews.PopularNews
import rama.id.newsfeed.domain.popularnews.PopularNewsRepository

/**
 * search without limit pagination use case
 */
class GetListOfPopularNewsUseCase(private val repository: PopularNewsRepository) :
    BaseUseCase<List<PopularNews>>(){

    override suspend fun build(params: Map<String, Any?>) = repository.getListOfPopular(
        params[UseCaseConstant.q] as String?,
        params[UseCaseConstant.PAGINATION] as Pagination
    )

}