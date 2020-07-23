package rama.id.newsfeed.presentation.main

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import rama.id.newsfeed.R
import rama.id.newsfeed.presentation.common.base.BaseActivity
import rama.id.newsfeed.shared.extensions.startActivityClearTask
import org.koin.androidx.viewmodel.ext.android.viewModel
import rama.id.newsfeed.shared.extensions.startActivityClearTask

class MainActivity : BaseActivity() {
    override val resourceLayout: Int? =
        R.layout.activity_main
    private val viewModel: MainActivityViewModel by viewModel()

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.delayToNextScreen()
        }
    }

    override fun onInitObservers() {
        viewModel.nextClassToLaunch.observe(this, Observer {
            startActivityClearTask(Intent(this, it))
        })
    }
}
