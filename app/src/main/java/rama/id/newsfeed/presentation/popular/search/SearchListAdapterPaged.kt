package rama.id.newsfeed.presentation.popular.search

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import rama.id.newsfeed.R
import rama.id.newsfeed.domain.popularnews.PopularNews
import rama.id.newsfeed.presentation.common.widget.paged.PagedState
import rama.id.newsfeed.presentation.common.widget.recycler.DefaultFooterHolder

class SearchListAdapterPaged(
    private val onItemClicked:(PopularNews)-> Unit,
    private val onRetry: () -> Unit
) : PagedListAdapter<PopularNews, RecyclerView.ViewHolder>(DIFF_CALLBACK){
    var currentState: PagedState = PagedState.Loading(true)
        set(value){
            Log.i("hasEXtrarow",hasExtraRow().toString())
            val prevState = currentState
            val prevExtraRow = hasExtraRow()
            field = value
            val newExtraRow = hasExtraRow()
            if(prevExtraRow != newExtraRow){
                if (prevExtraRow){
                    notifyItemRemoved(itemCount)
                } else {
                    notifyItemInserted(itemCount)
                }
            } else if (newExtraRow && prevState != value){
                notifyItemChanged(itemCount - 1)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        R.layout.search_item -> SearchHolder.newInstance(parent,onItemClicked)
        R.layout.item_loading -> DefaultFooterHolder.newInstance(parent)
        else -> throw IllegalStateException("view unddefined")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SearchHolder -> getItem(position)?.run(holder::onBind)
            is DefaultFooterHolder -> holder.onBind(currentState)
        }
    }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_loading
        } else {
            R.layout.search_item
        }

    private fun hasExtraRow() = currentState is PagedState.Loading && !currentState.isInitial

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PopularNews>() {
            override fun areItemsTheSame(oldItem: PopularNews, newItem: PopularNews) =
                oldItem.author == newItem.author

            override fun areContentsTheSame(oldItem: PopularNews, newItem: PopularNews) = oldItem == newItem
        }
    }
}