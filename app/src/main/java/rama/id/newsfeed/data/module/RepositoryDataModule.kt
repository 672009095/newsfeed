package rama.id.newsfeed.data.module

import org.koin.dsl.module
import org.koin.experimental.builder.factoryBy
import rama.id.newsfeed.data.popularnews.PopularDataRepository
import rama.id.newsfeed.domain.popularnews.PopularNewsRepository


val repositoryDataModule = module {
    factoryBy<PopularNewsRepository,PopularDataRepository>()
}