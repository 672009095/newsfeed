package rama.id.newsfeed.domain.popularnews

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularNews (
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
): Parcelable