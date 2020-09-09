package com.coba.aja.clean_architecture_mvvm_dagger.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.coba.aja.clean_architecture_mvvm_dagger.app.AppPreference
import dagger.android.support.DaggerFragment


abstract class BaseFragment<B : ViewDataBinding> : DaggerFragment() {

    val appPreference : AppPreference
    val progressDialog : ProgressDialog
    lateinit var binding : B

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }

    init {
        appPreference = AppPreference(context!!)
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please Wait")
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container,false)
        return binding.root

    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    fun launchActivity(cls: Class<*>?) {
        val i = Intent(activity, cls)
        startActivity(i)
    }

    fun launchActivity(bundle : Bundle, cls: Class<*>?) {
        val i = Intent(activity, cls)
        i.putExtras(bundle)
        startActivity(i)
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
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}