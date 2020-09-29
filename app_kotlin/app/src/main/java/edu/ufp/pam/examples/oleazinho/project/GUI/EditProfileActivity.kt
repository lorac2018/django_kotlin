package edu.ufp.pam.examples.oleazinho.project.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import org.json.JSONObject

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        var editFirstName = findViewById<View>(R.id.EditFirstNameEditText) as TextView
        var editLastName = findViewById<View>(R.id.EditLastNameEditText) as TextView
        var EditAddress = findViewById<View>(R.id.EditAddressEditText) as TextView
        var EditUserName = findViewById<View>(R.id.EditUsernameEditText) as TextView
        var EditEmail = findViewById<View>(R.id.EditEmailEditText) as TextView
        var PasswordEditButton = findViewById<Button>(R.id.PasswordEditButton)
        var SaveProfileButton = findViewById<Button>(R.id.SaveProfileButton)

        //dummy data
        editFirstName.text = "Mickey"
        editLastName.text = "Mouse"
        EditAddress.text = "Rua dos Gatos"
        EditUserName.text = "Mickey"
        EditEmail.text = "mickey@teste.com"

        //GET vai buscar a informaçao para editar o profile
        val url = ""
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            editFirstName.text = "%s".format(response.toString())
            editLastName.text = "%s".format(response.toString())
            EditAddress.text = "%s".format(response.toString())
            EditUserName.text = "%s".format(response.toString())
            EditEmail.text = "%s".format(response.toString())
            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            }
        )
        VolleyRequest.getInstance(this).addToRequestQueue(jsonObjectRequest)

        PasswordEditButton.setOnClickListener{
            startActivity(Intent(this@EditProfileActivity, EditPasswordActivity::class.java))
        }

        //POST
        SaveProfileButton.setOnClickListener{
            val firstName = editFirstName.text
            val lastName = editLastName.text
            val address = EditAddress.text
            val userName = EditUserName.text
            val email = EditEmail.text


            val url = ""
            val params = HashMap<String, String>()
            params["firstName"] = "Mickey"
            params["lastName"] = "Mouse"
            params["address"] = "Rua dos Gatos"
            params["userName"] = "Mickey"
            params["email"] = "mickey@teste.com"
            val jsonObj = JSONObject(params)

            val req = JsonObjectRequest(Request.Method.POST,url,jsonObj, Response.Listener {
                Toast.makeText(this, "That work!", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this@EditProfileActivity, PerfilActivity::class.java))
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
