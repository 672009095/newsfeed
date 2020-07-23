package rama.id.newsfeed.presentation.module

import rama.id.newsfeed.domain.common.UseCaseConstant.q
import rama.id.newsfeed.presentation.main.MainActivityViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<MainActivityViewModel>()
    //viewModel<SearchActivityViewModel>()
    //viewModel{(q:String) -> SearchActivityViewModel(q,get(),get())}
    //viewModel{(q:String) -> SearchListViewModel(q,get(),get())}
}