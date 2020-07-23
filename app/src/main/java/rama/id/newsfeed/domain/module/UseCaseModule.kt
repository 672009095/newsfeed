package rama.id.newsfeed.domain.module

import org.koin.dsl.module
import org.koin.experimental.builder.factory
import rama.id.newsfeed.domain.category.usecase.GetListOfCategoryUseCase
import rama.id.newsfeed.domain.popularnews.usecase.GetListOfPopularNewsUseCase

/**
 * modul for grouping use case
 */
val useCaseModule = module {
    factory<GetListOfCategoryUseCase>()
    factory<GetListOfPopularNewsUseCase>()
    //factory<GetListOfSearchWithPageUseCase>()
}