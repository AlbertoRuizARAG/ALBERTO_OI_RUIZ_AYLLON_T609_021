package com.prilux.cmr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prilux.cmr.databinding.CmrMainFragmentBinding
import com.prilux.cmr.tests.CmrMainViewModel

class CmrMainFragment : Fragment() {

    companion object {
        fun newInstance() = CmrMainFragment()
    }

    private var _binding: CmrMainFragmentBinding? = null

    private lateinit var viewModel: CmrMainViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CmrMainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}