package rama.id.newsfeed.domain.category

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * model domain used for implemeent into view (interface)
 * based on data mapper on data module
 */
@Parcelize
data class Category (
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category : String,
    val language : String,
    val country : String
): Parcelable