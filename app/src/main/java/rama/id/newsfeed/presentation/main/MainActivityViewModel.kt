package rama.id.newsfeed.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import rama.id.newsfeed.presentation.common.base.BaseViewModel
//import rama.id.newsfeed.presentation.search.SearchActivity
import kotlinx.coroutines.delay
import rama.id.newsfeed.presentation.category.CategoryActivity
import java.util.concurrent.TimeUnit

class MainActivityViewModel: BaseViewModel() {
    private val nextClassToLaunchMutable = MutableLiveData<Class<*>>()
    val nextClassToLaunch: LiveData<Class<*>> = nextClassToLaunchMutable

    suspend fun delayToNextScreen() {
        delay(TimeUnit.SECONDS.toMillis(1))
        toCategoryPage()
    }

    private suspend fun toCategoryPage() {
        nextClassToLaunchMutable.postValue(CategoryActivity::class.java)
    }
}