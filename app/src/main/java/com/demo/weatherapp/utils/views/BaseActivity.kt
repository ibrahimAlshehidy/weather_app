package com.demo.weatherapp.utils.views

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding

/**
 * BaseActivity acts out as a base class for all activities
 */
abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    lateinit var binding: B

    //    private val disposableContainer = CompositeDisposable()
    private lateinit var popupLoading: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpViews()
        defineDialog()
    }

    abstract fun getViewBinding(): B
    open fun setUpViews() {

    }

    private fun defineDialog() {
//        popupLoading = Dialog(this, android.R.style.Theme_Dialog)
//        popupLoading.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        popupLoading.window!!.setBackgroundDrawableResource(R.color.transparent)
//        popupLoading.setContentView(R.layout.popup_loading)
//        val lp1 = WindowManager.LayoutParams()
//        lp1.copyFrom(popupLoading.window!!.attributes)
//        lp1.width = WindowManager.LayoutParams.MATCH_PARENT
//        lp1.height = WindowManager.LayoutParams.WRAP_CONTENT
    }


//    fun Disposable.addToContainer() = disposableContainer.add(this)


    fun showFlipDialog() {
        popupLoading.show()
    }

    fun dismissFlipDialog() {
        popupLoading.dismiss()

    }


}