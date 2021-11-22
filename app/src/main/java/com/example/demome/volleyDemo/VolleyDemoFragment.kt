package com.example.demome.volleyDemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.demome.R
import com.example.demome.databinding.FragmentVolleyDemoBinding
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VolleyDemoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VolleyDemoFragment : Fragment(), OnResponseUpdatedListener {


    private var userData: UserData? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var userImageIV: ImageView

    private lateinit var viewModel: VollyDemoViewModel
    private lateinit var binding: FragmentVolleyDemoBinding

    private val volleyClient: VolleyClient = VolleyClient()
    private lateinit var onresponseUpdateListener: OnResponseUpdatedListener

    private var urlPath = "2"

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
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_volley_demo,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(VollyDemoViewModel::class.java)

        //assign the viewModel
        binding.vollyDemoViewModel = viewModel

        //assign lifecycle to viewmodel
        binding.lifecycleOwner = viewLifecycleOwner

        //observe the url to update imageView. This is done here because Glide needs context.
        viewModel.imageURL.observe(viewLifecycleOwner, Observer { updateImageInUI(it) })

        userImageIV = binding.userImageIV

        onresponseUpdateListener = this


        var requestQueue = VolleySingleton.getQueue(this.requireContext())
        volleyClient.getData(requestQueue, urlPath, onresponseUpdateListener)

        return binding.root
    }


    private fun updateUserDataInUI(userData: UserData) {
        Log.i("Volley ", "vm " + userData)

        viewModel.updateResponseInUI(userData)

    }

    private fun updateImageInUI(it: String?) {
        Glide
            .with(this)
            .load(it)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(userImageIV)
    }


    override fun responseUpdated(userJsonObject: JSONObject?) {

        val jsonDataConverter = JSONDataConverter()
        userData = jsonDataConverter.convertJsonToData(userJsonObject)
        updateUserDataInUI(userData!!)
    }


}