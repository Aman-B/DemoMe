package com.example.demome.volleyDemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.demome.R
import com.example.demome.databinding.FragmentLoginBinding
import com.example.demome.databinding.FragmentVolleyDemoBinding
import com.example.demome.login.LoginViewModel
import com.google.gson.Gson
import org.json.JSONObject
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VolleyDemo.newInstance] factory method to
 * create an instance of this fragment.
 */
class VolleyDemo : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private lateinit var userImageIV: ImageView


    private lateinit var viewModel: VollyDemoViewModel
    private lateinit var binding: FragmentVolleyDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_volley_demo,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(VollyDemoViewModel::class.java)

        //assign the viewModel
        binding.vollyDemoViewModel = viewModel;

        //assign lifecycle to viewmodel
        binding.lifecycleOwner = viewLifecycleOwner

        //observe the url to update imageView. This is done here because Glide needs context.
        viewModel.imageURL.observe(viewLifecycleOwner, Observer { updateImageInUI(it) })

        userImageIV = binding.userImageIV

        return binding.root
    }

    private fun updateImageInUI(it: String?) {
        Glide
            .with(this)
            .load(it)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(userImageIV)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VolleyDemo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VolleyDemo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


}