package com.example.retrofitregastrationlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.retrofitregastrationlogin.databinding.ActivityFirebaseBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class Firebase : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseBinding
    private lateinit var auth:FirebaseAuth
    lateinit var varificationid:String
    lateinit var mcallback:PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var num="+91"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFirebaseBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        auth=FirebaseAuth.getInstance()

        binding.update.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtotp.text.toString()))
            {
                Toast.makeText(this@Firebase, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val otp:String=binding.edtotp.text.toString()
                varifycode(otp)
            }
        }
        mcallback=object :PhoneAuthProvider.OnVerificationStateChangedCallbacks()
        {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                var code=p0.smsCode

               if (code!=null)
               {
                   binding.edtotp.setText(code)

               }
                else
               {
                   Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
               }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(applicationContext, "Process Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                varificationid=p0
            }

        }
    }

    private fun varifycode(otp: String) {
        val credential=PhoneAuthProvider.getCredential(varificationid,otp)
        signinwithcredential(credential)
    }

    private fun signinwithcredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                var i = Intent(this, Login::class.java)
                startActivity(i)
            } else {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        }
            .addOnFailureListener() {

            }


        }
    private fun sendvarificationcode(mob:String)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mob,60,TimeUnit.SECONDS,this,mcallback)

    }
}
