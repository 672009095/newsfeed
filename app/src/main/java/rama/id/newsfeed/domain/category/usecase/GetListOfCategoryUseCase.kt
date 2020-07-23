package rama.id.newsfeed.domain.category.usecase

import rama.id.newsfeed.domain.category.Category
import rama.id.newsfeed.domain.category.CategoryRepository
import rama.id.newsfeed.domain.common.UseCaseConstant
import rama.id.newsfeed.domain.common.base.BaseUseCase

/**
 * search without limit pagination use case
 */
class GetListOfCategoryUseCase(private val repository: CategoryRepository) :
    BaseUseCase<List<Category>>(){

    override suspend fun build(params: Map<String, Any?>) = repository.getListOfCategory(
        params[UseCaseConstant.category] as String?
    )

}