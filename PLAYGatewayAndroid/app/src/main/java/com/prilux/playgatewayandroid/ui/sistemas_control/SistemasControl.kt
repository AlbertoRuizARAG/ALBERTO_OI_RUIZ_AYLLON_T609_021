package com.prilux.playgatewayandroid.ui.sistemas_control

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.prilux.cmr.MainViewCmr
import com.prilux.playgatewayandroid.databinding.FragmentSistemasControlBinding


class SistemasControl : Fragment() {

    private var _binding: FragmentSistemasControlBinding? = null
    val LOG_TAG = "LOG_SISTEMAS_CONTROL"
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(SistemasControlViewModel::class.java)

        _binding = FragmentSistemasControlBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val mainCMR = binding.goToMainCmr
        mainCMR.setOnClickListener {

        /*   var intent = Intent()
            try {
                intent = Intent(
                    this.context,
                    Class.forName("MainActivityCMR")
                )
                startActivity(intent)

            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }*/

/*
            val intent = Intent(Intent.ACTION_VIEW)
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                 intent.component = ComponentName(
                    "com.prilux.cmr",
                    "MainActivityCMR"
                )
                startActivity(intent)
            }  catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
*/
            val intent = Intent(context, MainViewCmr::class.java)
            context?.startActivity(intent)

        }

        val mainPLC = binding.goToMainPlc
        mainPLC.setOnClickListener {

        }

        val mainIOT = binding.goToMainIot
        mainIOT.setOnClickListener {

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}