package com.example.imagesearchapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.imagesearchapp.R
import com.example.imagesearchapp.base.BaseFragment
import com.example.imagesearchapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class DetailsFragment: BaseFragment<FragmentDetailsBinding>() {

    private val args by navArgs<DetailsFragmentArgs>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater ,container ,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {
            val photo = args.photo
            Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQu_fpPmbK-bebEeX036y7frmW06amtCkG1ew&usqp=CAU")
                .error(R.drawable.ic_error)
                .into(imageView ,object : Callback{
                    override fun onSuccess() {
                       progressBar.isVisible = false
                        textViewCreator.isVisible = true
                        textViewDescription.isVisible = photo.description != null
                    }

                    override fun onError(e: Exception?) {
                        progressBar.isVisible = false

                    }
                })

            textViewDescription.text = photo.description
            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW ,uri)

            textViewCreator.apply {
                text = "Photo by ${photo.user.name} on Unsplash"
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }
        }


    }


}