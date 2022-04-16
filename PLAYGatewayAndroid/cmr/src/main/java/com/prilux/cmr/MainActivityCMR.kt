package com.prilux.cmr


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.prilux.cmr.databinding.ActivityMainCmrBinding

class MainActivityCMR : AppCompatActivity() {

    private lateinit var binding: ActivityMainCmrBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_cmr)
        binding = ActivityMainCmrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val frameContainer = binding.frameContainer.id

        var mFragment: Fragment? = null
        mFragment = CmrMainFragment.newInstance()
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(frameContainer,mFragment).commit()


        Toast.makeText(this, "Notificación corta", Toast.LENGTH_SHORT).show()



    }
}