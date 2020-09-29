package edu.ufp.pam.examples.oleazinho.project.GUI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import org.json.JSONObject


class LoginActivity : AppCompatActivity(), IVolley {



    override fun onResponse(response : String)
    {
        Toast.makeText(this@LoginActivity, ""+response, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var userName = findViewById<View>(R.id.UserNameEditText) as EditText
        var password = findViewById<View>(R.id.PasswordEditText) as EditText
        var loginButton = findViewById<Button>(R.id.LoginButton)
        var registrationButton = findViewById<Button>(R.id.RegistryButton)

        loginButton.setOnClickListener{
            val userName = userName.text
            val password = password.text


            /*VolleyRequest.getInstance(this@LoginActivity, this@LoginActivity)
                .postRequest("http://localhost:8000/api-auth/login/")
            startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))*/

            //start activity intent para NavigationActivity verifiar login
            //e depois é entra na navigationActivity

            //val url = "http://192.168.1.74:8000/api-auth/login/"
            val url = ""
            val params = HashMap<String, String>()
            params["username"] = "Mickey"
            params["password"] = "secretomickey"
            val jsonObj = JSONObject(params)

            //dummy data
            if(userName.toString().equals("Mickey")
                &&password.toString().equals("secretomickey"))
                startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))

            //POST api
            /*val req = JsonObjectRequest(Request.Method.POST,url,jsonObj, Response.Listener {
                Toast.makeText(this, "That work!", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this@LoginActivity, NavigationActivity::class.java))
            }, Response.ErrorListener{
                Log.d("aa", it.toString())
                Toast.makeText(this, "That didn't work! $it", Toast.LENGTH_SHORT).show()
            })
            req.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                0,
                1f
            )
            VolleyRequest.getInstance(this).addToRequestQueue(req)*/

        }

        registrationButton.setOnClickListener {
            //start activity intent para formulario registo
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))

        }

    }
}
