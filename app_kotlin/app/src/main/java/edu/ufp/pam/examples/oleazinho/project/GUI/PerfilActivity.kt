package edu.ufp.pam.examples.oleazinho.project.GUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        var firstName = findViewById<View>(R.id.FirstNameTextView1) as TextView
        var lastName = findViewById<View>(R.id.LastNameTextView1) as TextView
        var address = findViewById<View>(R.id.Address1TextView) as TextView
        var userName = findViewById<View>(R.id.UserNameTextView1) as TextView
        var email = findViewById<View>(R.id.EmailTextView1) as TextView
        var edit = findViewById<Button>(R.id.EditButton)

        //dummy data
        firstName.text = "Mickey"
        lastName.text = "Mouse"
        address.text = "Rua dos Gatos"
        userName.text = "Mickey"
        email.text = "mickey@teste.com"

        edit.setOnClickListener{
            startActivity(Intent(this@PerfilActivity, EditProfileActivity::class.java))
        }

        //GET
        val url = ""
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, Response.Listener { response ->
                firstName.text = "%s".format(response.toString())
                lastName.text = "%s".format(response.toString())
                address.text = "%s".format(response.toString())
                userName.text = "%s".format(response.toString())
                email.text = "%s".format(response.toString())
            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            }
        )
        VolleyRequest.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
