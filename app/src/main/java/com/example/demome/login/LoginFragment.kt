package com.example.demome.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demome.R
import com.example.demome.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //assign the viewModel
        binding.loginViewModel = viewModel;

        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.loginClicked.observe(viewLifecycleOwner, Observer <Int>{ loginButtonClicked() })

        Log.i("login ", "onCreateView: ")
        return binding.root
    }

    private fun loginButtonClicked() {

        if(viewModel.loginClicked.value==0)
        {
            Log.i("login ","clicked but some error is there.")
            Toast.makeText(context,"Invalid Credentials!",Toast.LENGTH_LONG).show();


        }
        else
        {
            Toast.makeText(context,"Login successful!",Toast.LENGTH_LONG).show();
            Log.i("login ","clicked")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}