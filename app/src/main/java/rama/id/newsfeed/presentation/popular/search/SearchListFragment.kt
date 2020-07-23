package rama.id.newsfeed.presentation.popular.search

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.search_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import rama.id.newsfeed.presentation.common.base.BaseFragment
import rama.id.newsfeed.presentation.common.widget.paged.PagedState
import rama.id.newsfeed.shared.extensions.onLoading
import rama.id.newsfeed.shared.extensions.showToast

class SearchListFragment : BaseFragment() {
    override val resourceLayout: Int = rama.id.newsfeed.R.layout.search_list_fragment
    private val viewModel: SearchListViewModel by inject { parametersOf(q,args.q) }
    private val args: SearchListFragmentArgs by navArgs()
    private val q by lazy { arguments?.getString(ARG_Q, "") }
    private val searchListAdapterPaged =
        SearchListAdapterPaged(
            // set click on list user can be show pop or bring it to another view
            onItemClicked = {},
            // set pulldown refresh to refresh list
            onRetry = { viewModel.retryLoadAtLast() }
        )

    override fun onInitViews() {
        //super.onInitViews()
        swipeRefresh.setOnRefreshListener { viewModel.searches.value?.dataSource?.invalidate() }
        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchListAdapterPaged
        }
    }

    override fun onInitObservers() {
        viewModel.searches.observe(viewLifecycleOwner, Observer(searchListAdapterPaged::submitList))
        viewModel.searchesState.observe(viewLifecycleOwner, Observer {
            if (it.isInitial) {
                shimmerView.onLoading(it is PagedState.Loading)
                if (it !is PagedState.Loading) swipeRefresh.isRefreshing = false
                if (it is PagedState.Failure) requireActivity().showToast(it.error.toString())
            } else {
                if (it !is PagedState.Loading) swipeRefresh.isRefreshing = false
                searchListAdapterPaged.currentState = it
            }

        })


        //super.onInitObservers()
    }
    companion object {
        private const val ARG_Q = "ARG_Q"
        fun newInstance(q: String) = SearchListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_Q, q)
            }
        }
    }
}