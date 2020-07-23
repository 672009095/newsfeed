package rama.id.newsfeed

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import rama.id.newsfeed.data.module.apiModule
import rama.id.newsfeed.data.module.networkModule
import rama.id.newsfeed.data.module.repositoryDataModule
import rama.id.newsfeed.domain.module.useCaseModule
import rama.id.newsfeed.presentation.module.viewModelModule


@Suppress("unused")
class AndroidApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@AndroidApp)
            modules(
                listOf(
                    apiModule,
                    useCaseModule,
                    networkModule,
                    repositoryDataModule,
                    viewModelModule
                )
            )
        }
    }
}