package com.coba.aja.clean_architecture_mvvm_dagger.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.coba.aja.clean_architecture_mvvm_dagger.app.AppPreference
import com.coba.aja.clean_architecture_mvvm_dagger.di.util.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity() {
    @Inject
    private lateinit var viewModelFactory : ViewModelFactory
    lateinit var binding : B
    lateinit var appPreference : AppPreference
    lateinit var progressDialog : ProgressDialog

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        bind()
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, layoutRes())
        appPreference = AppPreference(this)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait")
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    fun launchActivity(cls: Class<*>?) {
        val i = Intent(this@BaseActivity, cls)
        startActivity(i)
    }

    fun launchActivity(bundle : Bundle, cls: Class<*>?) {
        val i = Intent(this@BaseActivity, cls)
        i.putExtras(bundle)
        startActivity(i)
    }

    protected fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        backStackStateName: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .addToBackStack(backStackStateName)
            .commit()
    }

    fun showProgressDialog() {
        try {
            progressDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun hideProgressDialog() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}