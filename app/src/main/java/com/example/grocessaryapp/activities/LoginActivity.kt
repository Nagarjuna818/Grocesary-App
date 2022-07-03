package com.example.grocessaryapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.grocessaryapp.R
import com.example.grocessaryapp.SessionManager
import com.example.grocessaryapp.app.Endpoints
import com.example.grocessaryapp.models.LoginResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        init()
    }

    private fun init() {
        button_login.setOnClickListener {
            var email = edit_text_email.text.toString()
            var password = edit_text_password.text.toString()
            postRequest(email, password)
        }
        click_text.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

    private fun postRequest(email: String, password: String) {

        var params = HashMap<String, String>()
        params["email"] = email
        params["password"] = password

        var jsonObject = JSONObject(params as Map<*, *>?)


        var requestQueue = Volley.newRequestQueue(this)
        var request = JsonObjectRequest(
            Request.Method.POST,
            Endpoints.getLoginUrl(),
            jsonObject,
            Response.Listener {

                var gson = Gson()
                var loginResponse = gson.fromJson(it.toString(), LoginResponse::class.java)
                sessionManager.saveUser(loginResponse.user, loginResponse.token)

                Toast.makeText(applicationContext, "Register successfully", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(applicationContext, MainActivity::class.java))

            },
            Response.ErrorListener {
                Log.d("abc", it.message.toString())
            }
        )
        requestQueue.add(request)
    }
}
