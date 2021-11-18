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
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.demome.R
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

    var dataForVolley: DataForVolley? = null;

    private lateinit var userImageIV:ImageView
    private lateinit var name:TextView
    private lateinit var email : TextView

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volley_demo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userImageIV = view.findViewById(R.id.userImage_IV)
        name = view.findViewById(R.id.name_ET)
        email = view.findViewById(R.id.email_ET)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = "https://reqres.in/api/users/2"

        var jsonRequest = JsonObjectRequest(Request.Method.GET,url,null,
            {response->
                Log.i("Volley ", " response "+ response);
                updateResponseInUI(response)
            },
            {
                Toast.makeText(context,"That didn't work!",Toast.LENGTH_LONG).show()
                Log.i("Volley ", " error "+it);
            })



// Add the request to the RequestQueue.
        queue.add(jsonRequest)

    }

    private fun updateResponseInUI(response: JSONObject) {
        dataForVolley = convertJsonToData(response)
        Log.i("volley ", " data "+ (dataForVolley?.data?.firstName ?: dataForVolley))


        setUpUIElements(dataForVolley)
    }

    private fun convertJsonToData(response: JSONObject): DataForVolley? {
        var gson = Gson();

        var data=gson.fromJson(response.toString(),DataForVolley::class.java)
        return data
    }


    private fun setUpUIElements(dataForVolley: DataForVolley?)
    {
        name.setText(dataForVolley?.data?.firstName+" "+dataForVolley?.data?.lastName)
        email.setText(dataForVolley?.data?.email)

        Glide
            .with(this)
            .load(dataForVolley?.data?.avatar)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(userImageIV);


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