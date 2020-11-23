package com.jeanbarrossilva.realism.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jeanbarrossilva.realism.MainActivity

class MainViewModelFactory(private val activity: MainActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = modelClass.getConstructor(MainActivity::class.java).newInstance(activity)
}