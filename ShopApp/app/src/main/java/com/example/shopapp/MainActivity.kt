package com.example.shopapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.shopapp.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mGoogleSignInClient: GoogleSignInClient

    private val auth by lazy {
        FirebaseAuth.getInstance()
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var toolbar: Toolbar

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        // Referenciaa GoogleSign
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("697058634657-arf4cjm7lh797o98rubjg3eo4u5iaahd.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toolbar = findViewById(R.id.toolbar_login)
        setSupportActionBar(toolbar)

        binding.email.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.message, 0, 0, 0)
        binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password, 0, 0, 0)


        binding.email.addTextChangedListener(loginTextWatcher)
        binding.password.addTextChangedListener(loginTextWatcher)


        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
            {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
            {

            }

            override fun afterTextChanged(s: Editable)
            {
                if (s.isNotEmpty())
                {

                    var drawable = AppCompatResources.getDrawable(applicationContext, R.drawable.message)//resources.getDrawable(R.drawable.message) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(applicationContext, R.color.colordarkblue)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(applicationContext, R.drawable.message),
                        null,AppCompatResources.getDrawable(applicationContext, R.drawable.cancel), null)
                    //binding.email.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.message), null, applicationContext.getDrawable(R.drawable.cancel), null)
                }
                else if (s.isEmpty())
                {
                    binding.email.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.message,
                        0, 0, 0)
                    var drawable = AppCompatResources.getDrawable(applicationContext, R.drawable.message)//applicationContext.getDrawable(R.drawable.message) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(drawable, ContextCompat.getColor(applicationContext, R.color.colorDefault)) // Set whatever color you want
                    // DrawableCompat.setTint(drawable, resources.getColor(R.color.colorDefault)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    binding.email.setCompoundDrawablesWithIntrinsicBounds(
                        AppCompatResources.getDrawable(applicationContext, R.drawable.message),
                        null, null, null
                    )
                }
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
            {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
            {

            }

            override fun afterTextChanged(s: Editable)
            {
                if (s.isNotEmpty())
                {
                    var drawable = AppCompatResources.getDrawable(applicationContext, R.drawable.password)// resources.getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(drawable!!, getColor(R.color.colordarkblue))//resources.getColor(R.color.colordarkblue)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(applicationContext, R.drawable.password), null,AppCompatResources.getDrawable(applicationContext, R.drawable.cancel), null)
                    //binding.password.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.password), null, resources.getDrawable(R.drawable.cancel), null)
                }
                else if (s.isEmpty())
                {
                    binding.password.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.password,
                        0, 0, 0)
                    var drawable = AppCompatResources.getDrawable(applicationContext, R.drawable.password)// getDrawable(R.drawable.password) //Your drawable image
                    drawable = DrawableCompat.wrap(drawable!!)
                    DrawableCompat.setTint(drawable!!, getColor(R.color.colorDefault)) //resources.getColor(R.color.colorDefault)) // Set whatever color you want
                    DrawableCompat.setTintMode(drawable!!, PorterDuff.Mode.SRC_IN)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    binding.password.setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(applicationContext, R.drawable.password), null,null, null)

                }
            }
        })

        binding.email.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN)
                {
                    if (binding.email.compoundDrawables[2] != null )
                   // if (binding.email.getCompoundDrawables().get(2) != null)
                    {
                        if (event.x >= binding.email.right - binding.email.left -
                            binding.email.compoundDrawables[2].bounds.width())
                        {
                            if (binding.email.text.toString() != "")
                            {
                                binding.email.setText("")
                            }
                        }
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
        binding.password.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event?.action == MotionEvent.ACTION_DOWN) {
                    if (binding.password.compoundDrawables[2] != null) {
                        if (event.x >= binding.password.right - binding.password.left -
                            binding.password.compoundDrawables[2].bounds.width()
                        ) {
                            if (binding.password.text.toString() != "") {
                                binding.password.setText("")
                            }
                        }
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

 /*       binding.email.setOnTouchListener(View.OnTouchListener { v, event ->

            if (event.action == MotionEvent.ACTION_DOWN)
            {
                if (binding.email.getCompoundDrawables().get(2) != null)
                {
                    if (event.x >= binding.email.getRight() - binding.email.getLeft() -
                        binding.email.getCompoundDrawables().get(2).getBounds().width())
                    {
                        if (binding.email.getText().toString() != "")
                        {
                            binding.email.setText("")
                        }
                    }
                }
            }
            false
        })*/

       /* binding.password.setOnTouchListener(View.OnTouchListener { v, event ->

            if (event.action == MotionEvent.ACTION_DOWN)
            {
                if (binding.password.getCompoundDrawables().get(2) != null)
                {
                    if (event.x >= binding.password.getRight() - binding.password.getLeft() -
                        binding.password.getCompoundDrawables()[2].getBounds().width()
                    )
                    {
                        if (binding.password.getText().toString() != "")
                        {
                            binding.password.setText("")
                        }
                    }
                }
            }
            false
        })
*/
        binding.rememberPassword.setOnClickListener {

            if (!(binding.rememberPassword.isSelected)) {
                binding.rememberPassword.isChecked = true
                binding.rememberPassword.isSelected = true
            } else {
                binding.rememberPassword.isChecked = false
                binding.rememberPassword.isSelected = false
            }
        }

        binding.requestAccess.setOnClickListener{
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, LoginScreen::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }
        }
    }

    private val loginTextWatcher: TextWatcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int)
        {


        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int)
        {


        }

        override fun afterTextChanged(s: Editable)
        {
            val mUsername: String = binding.email.text.toString().trim()
            val mPassword: String = binding.password.text.toString().trim()
            val t = mUsername.isNotEmpty() && mPassword.isNotEmpty()
            if (t)
            {
                binding.loginButton.setBackgroundResource(R.color.colordarkblue)
            }
            else
            {
                binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
            }

        }
    }

    override fun onStart()
    {
        super.onStart()
        val mUsername: String = binding.email.text.toString().trim()
        val mPassword: String = binding.password.text.toString().trim()
        val t = mUsername.isNotEmpty() && mPassword.isNotEmpty()
        if (t)
        {
            binding.loginButton.setBackgroundResource(R.color.colordarkblue)
        }
        else
        {
            binding.loginButton.setBackgroundResource(R.color.colorwhiteblueshade)
        }
    }
}