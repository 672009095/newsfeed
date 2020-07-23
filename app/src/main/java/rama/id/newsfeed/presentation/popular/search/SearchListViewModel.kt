package rama.id.newsfeed.presentation.popular.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import rama.id.newsfeed.domain.common.UseCaseConstant
import rama.id.newsfeed.domain.common.entity.Either
import rama.id.newsfeed.domain.common.entity.Pagination
import rama.id.newsfeed.domain.popularnews.PopularNews
import rama.id.newsfeed.domain.popularnews.usecase.GetListOfPopularNewsUseCase
import rama.id.newsfeed.presentation.common.base.BaseViewModel
import rama.id.newsfeed.presentation.common.widget.paged.PagedFactory
import rama.id.newsfeed.presentation.common.widget.paged.PagedState

class SearchListViewModel(
    private val q:String?,
    private val getListOfSearchWithPageUseCase: GetListOfPopularNewsUseCase
) : BaseViewModel(){

    private val factory = PagedFactory(::getLisOfSearchWithPage)
    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(10)
        .setPageSize(10)
        .build()

    val searches = (LivePagedListBuilder(factory,pagedListConfig)).build()
    val searchesState: LiveData<PagedState> = factory.pagedState

    private suspend fun getLisOfSearchWithPage(page: Int?):
            Either<Throwable, List<PopularNews>> {
        val params = mapOf(
            UseCaseConstant.q to q,
            UseCaseConstant.PAGINATION to Pagination(page?:1)
        )
        return getListOfSearchWithPageUseCase.addParams(params)
            .invoke(viewModelScope.coroutineContext)
    }

    fun retryLoadAtLast() {
        val lastKey = searches.value?.lastKey as? Int ?: return
        searches.value?.loadAround(lastKey)
    }
}