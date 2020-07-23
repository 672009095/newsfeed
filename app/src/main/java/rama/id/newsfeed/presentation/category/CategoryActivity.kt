package rama.id.newsfeed.presentation.category

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import rama.id.newsfeed.domain.common.UseCaseConstant.category
import rama.id.newsfeed.presentation.common.ResultData
import rama.id.newsfeed.presentation.common.base.BaseActivity
import rama.id.newsfeed.presentation.popular.search.SearchListFragment

class CategoryActivity : BaseActivity(){
    override val resourceLayout: Int = rama.id.newsfeed.R.layout.activity_category
    private val viewModel: CategoryActivityViewModel by inject { parametersOf(category)}
//    private val searchListAdapter = SearchListAdapter(
//        onItemClicked = {}
//    )

    override fun onInitViews() {
        //super.onInitViews()
//        with(recycleListSearch){
//            layoutManager = LinearLayoutManager(context)
//            //adapter = searchListAdapter
//        }
        searchView.onActionViewExpanded()
        searchView.clearFocus()
        searchView.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                //Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                lifecycleScope.launch{
                    //viewModel.getListOfSearch(query)
                    //viewModel.getLisOfSearchWithPage(1)
                    val manager = supportFragmentManager
                    val transaction = manager.beginTransaction()
                    if (query != null) {
                        transaction.replace(container.id,SearchListFragment.newInstance(query))
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }else{
                        transaction.replace(container.id, SearchListFragment.newInstance(""))
                        transaction.addToBackStack(null)
                        transaction.commit()
                    }
                }
                return false
            }
        })
    }

    override fun onInitObservers() {
        viewModel.items.observe(this, Observer {
            when(it){
                //is ResultData.Success -> it.data.run(searchListAdapter::addItems)
            }
        })
    }
}