package rama.id.newsfeed.presentation.common.base

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import rama.id.newsfeed.domain.common.entity.Either
import rama.id.newsfeed.presentation.common.ResultData

/**
 * base for view model
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {
    protected fun <T> Either<Throwable, T>.toResult() = when (this) {
        is Either.Error -> ResultData.Failure(this.error)
        is Either.Value -> ResultData.Success(this.value)
    }
}