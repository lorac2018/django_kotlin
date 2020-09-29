package edu.ufp.pam.examples.oleazinho.project.GUI

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.Group
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.toolbox.JsonObjectRequest
import com.google.android.material.navigation.NavigationView
import edu.ufp.pam.examples.oleazinho.R
import org.json.JSONObject
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import kotlinx.android.synthetic.main.content_main.*


class NavigationActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        var points = findViewById<View>(R.id.PointTextView) as TextView
        //ir buscar os pontos ao backend

        // GET
        val url = ""
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                points.text = "%s".format(response.toString())
            },
            Response.ErrorListener {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            }
        )
        VolleyRequest.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_perfil -> {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                val perfil = findViewById<Group>(R.id.nav_perfil)
                startActivity(Intent(this@NavigationActivity, PerfilActivity::class.java))
            }
            R.id.nav_oleoes -> {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                val oleoes = findViewById<Group>(R.id.nav_oleoes)
                startActivity(Intent(this@NavigationActivity, OleoesMapsActivity::class.java))
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sign out clicked", Toast.LENGTH_SHORT).show()
                val logout = findViewById<Group>(R.id.nav_logout)
                startActivity(Intent(this@NavigationActivity, LoginActivity::class.java))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
