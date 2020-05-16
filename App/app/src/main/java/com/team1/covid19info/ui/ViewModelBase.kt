package com.team1.covid19info.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team1.covid19info.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

abstract class ViewModelBase(private val context: Context) : ViewModel() {
    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val calls = LinkedList<suspend () -> Unit>()

    val error = MutableLiveData<String>()

    init {
        Thread {
            handleCalls()
        }.start()
    }

    private fun handleCalls() {
        while (true) {
            while (calls.size > 0) {
                val f = calls.poll()
                scope.launch {
                    var success = false
                    do {
                        try {
                            f.invoke()
                            success = true
                        } catch (e: Exception) {
                            println(e)
                            error.postValue(context.getString(R.string.ConnectionErrorString))
                            Thread.sleep(5000)
                        }
                    } while (!success)
                }
                Thread.sleep(500)
            }
            Thread.sleep(1)
        }
    }
}