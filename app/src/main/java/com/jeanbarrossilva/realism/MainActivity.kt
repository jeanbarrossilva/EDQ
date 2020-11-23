package com.jeanbarrossilva.realism

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.jeanbarrossilva.realism.ui.MainUI
import com.jeanbarrossilva.realism.ui.viewmodel.MainViewModel
import com.jeanbarrossilva.realism.ui.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    internal val viewModel by viewModels<MainViewModel> { MainViewModelFactory(activity = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainUI(activity = this) }
        viewModel.configAppearance()
    }
}