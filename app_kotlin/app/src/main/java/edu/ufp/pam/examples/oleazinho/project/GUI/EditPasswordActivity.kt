package edu.ufp.pam.examples.oleazinho.project.GUI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import org.json.JSONObject
import com.android.volley.Request
import com.android.volley.Response
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy

class EditPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_password)

        var newPassword = findViewById<View>(R.id.NewPasswordEditText) as EditText
        var confirmationPassword = findViewById<View>(R.id.ConfirmationPasswordEditText) as EditText
        var saveButton = findViewById<Button>(R.id.SavePasswordButton)

        //POST
        saveButton.setOnClickListener{
            val newPassword = newPassword.text
            val confirmationPassword = confirmationPassword.text
            val url = "http://192.168.1.74:8000/login/"
            val params = HashMap<String, String>()
            params["newPassword"] = "secretomickey"
            params["confirmationPassword"] = "secretomickey1"
            val jsonObj = JSONObject(params)
            val req = JsonObjectRequest(Request.Method.POST,url,jsonObj, Response.Listener {
                Toast.makeText(this, "That work!", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this@EditPasswordActivity, EditProfileActivity::class.java))
            }, Response.ErrorListener{
                Toast.makeText(this, "That didn't work! $it", Toast.LENGTH_SHORT).show()
            })
            req.retryPolicy = DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                0,
                1f
            )
            VolleyRequest.getInstance(this).addToRequestQueue(req)
        }
    }
}
