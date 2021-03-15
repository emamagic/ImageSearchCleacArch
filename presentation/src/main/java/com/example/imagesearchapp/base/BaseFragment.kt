package com.example.imagesearchapp.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.example.imagesearchapp.R

abstract class BaseFragment<VB: ViewBinding>: Fragment() {

    private  var _binding: VB? = null
    protected val binding get() = _binding
    private lateinit var loading: FrameLayout
    private var callback: OnBackPressedCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater ,container)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = binding?.root?.rootView?.findViewById(R.id.my_loading)!!
    }

    protected fun showLoading(isDim: Boolean = false){
        if (loading.visibility != View.VISIBLE){
            if (isDim) loading.setBackgroundColor(Color.parseColor("#cc000000"))
            loading.visibility = View.VISIBLE
        }
    }

    protected fun hideLoading(){
        if (loading.visibility != View.GONE){
            loading.visibility = View.GONE
        }
    }

    @SuppressLint("ResourceAsColor")
    fun toasty(title: String, selectedMode: Int? = null) {
        val layout = layoutInflater.inflate(
            R.layout.toast_layout,
            requireView().findViewById(R.id.toast_root)
        )
        when (selectedMode) {

            1 -> {
                //correct
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_corroct_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_corroct_toast)
            }
            2 -> {
                //Warning
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_warning_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_warning_toast)
                layout.findViewById<TextView>(R.id.toast_txt).setTextColor(R.color.black)
            }
            3 -> {
                //Error
                layout.findViewById<ImageView>(R.id.toast_img)
                    .setImageResource(R.drawable.ic_error_toast)
                layout.findViewById<ConstraintLayout>(R.id.toast_root)
                    .setBackgroundResource(R.drawable.bg_error_toast)
            }
            else -> {
                //AnyNumber
                Toast.makeText(requireContext(), title, Toast.LENGTH_LONG).show()
            }

        }

        layout.findViewById<TextView>(R.id.toast_txt).text = title
        if(selectedMode!=null){
            Toast(requireActivity()).apply {
                setGravity(Gravity.BOTTOM, 0, 100)
                duration = Toast.LENGTH_LONG
                view = layout
            }.show()
        }
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater ,container: ViewGroup?): VB

    fun onMyBackPressed(owner: LifecycleOwner, call: () -> Unit) {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                call()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(owner, callback!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        if (callback != null){
            callback?.isEnabled = false
            callback?.remove()
        }
    }


}