package rama.id.newsfeed.presentation.common

import rama.id.newsfeed.presentation.common.base.BaseViewModel

interface PresentationVM<VM : BaseViewModel> {
    val viewModel: VM
}