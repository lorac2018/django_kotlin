package edu.ufp.pam.examples.oleazinho.project.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import kotlinx.android.synthetic.main.activity_registration.view.*
import org.json.JSONObject


class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        var firstName = findViewById<View>(R.id.FirstNameEditText) as EditText
        var lastName = findViewById<View>(R.id.LastNameEditText) as EditText
        var address = findViewById<View>(R.id.AddressEditText) as EditText
        var userName = findViewById<View>(R.id.UsernameEditText) as EditText
        var email = findViewById<View>(R.id.EmailEditText) as EditText
        var password = findViewById<View>(R.id.PasswordEditText) as EditText
        var newPassword = findViewById<View>(R.id.NewPasswordEditText) as EditText
        var submitButton = findViewById<Button>(R.id.SubmitButton)

        submitButton.setOnClickListener {
            val userName = userName.text
            val firstName = firstName.text
            val email = email.text
            var lastName = lastName.text
            val password = password.text
            val newPassword = newPassword.text
            val address = address.text

            val url = "http://192.168.1.74:8000/register/"
            val params = HashMap<String, String>()
            params["username"] = "kitty"
            params["first_name"] = "secretonilsa"
            params["email"] = "kitty@teste.com"
            params["last_name"] = "kitty"
            params["password1"] = "secretonilsa"
            params["password2"] = "secretonilsa"
            params["address"] = "rua dos gatos"
            val jsonObj = JSONObject(params)

            //Post request
            /*val req = JsonObjectRequest(Request.Method.POST,url,jsonObj, Response.Listener {
                Toast.makeText(this, "That work!", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
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

            //dummy data
            if(userName.toString().equals("") || firstName.toString().equals("") || email.toString().equals("")
                || lastName.toString().equals("") || password.toString().equals("") || newPassword.toString().equals("")
                || address.toString().equals(""))
            {
                Toast.makeText(this,"Registration not completed", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (password.toString().equals(newPassword.toString()))
                {
                    Toast.makeText(this, "Success Registration", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                }
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
