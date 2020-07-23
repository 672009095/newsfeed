package rama.id.newsfeed.presentation.popular.search

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.search_item.view.*
import rama.id.newsfeed.R
import rama.id.newsfeed.domain.popularnews.PopularNews
import rama.id.newsfeed.shared.extensions.inflate

class SearchHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private var item: PopularNews? = null

    private val image = itemView.thumbnailImageView
    private val login = itemView.loginTextView

    private val options = RequestOptions()
        .placeholder(R.drawable.ic_launcher_foreground)
        .transform(CenterCrop(), RoundedCorners(16))

    fun onCreate(onItemClicked: (PopularNews) -> Unit){
        itemView.setOnClickListener{item?.run(onItemClicked)}
    }

    fun onBind(item: PopularNews) {
        this.item = item
        Glide.with(itemView)
            .load(item.urlToImage)
            .apply(options)
            .into(image)
        login.text = item.title
    }

    companion object {
        fun newInstance(parent: ViewGroup, onItemClicked: (PopularNews) -> Unit) =
            SearchHolder(parent.inflate(R.layout.search_item)).apply {
                onCreate(onItemClicked)
            }
    }
}