package rama.id.newsfeed.presentation.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import rama.id.newsfeed.domain.category.Category
import rama.id.newsfeed.domain.category.usecase.GetListOfCategoryUseCase
import rama.id.newsfeed.domain.common.UseCaseConstant
import rama.id.newsfeed.presentation.common.ResultData
import rama.id.newsfeed.presentation.common.base.BaseViewModel
import rama.id.newsfeed.presentation.common.widget.paged.PagedFactory
import rama.id.newsfeed.presentation.common.widget.paged.PagedState

class CategoryActivityViewModel(
    private val category:String?,
    private val getListOfCategoryUseCase: GetListOfCategoryUseCase
) : BaseViewModel(){

    private val itemsMutable = MutableLiveData<ResultData<List<Category>>>()
    val items: LiveData<ResultData<List<Category>>> = itemsMutable

    suspend fun getListOfCategory(category: String?){
        val params = mapOf(
            UseCaseConstant.category to category
        )
        getListOfCategoryUseCase.addParams(params)
            .invoke()
            .toResult()
            .run(itemsMutable::postValue)
    }
}