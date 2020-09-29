package edu.ufp.pam.examples.oleazinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import edu.ufp.pam.examples.oleazinho.project.GUI.PerfilActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
@LargeTest
class PerfilActivityTest {

    private lateinit var firstNameToBeChecked: String
    private lateinit var LastNameToBeChecked: String
    private lateinit var addressToBeChecked: String


    @get:Rule
    var activityRule: ActivityTestRule<PerfilActivity>
            = ActivityTestRule(PerfilActivity::class.java)

    @Before
    fun initValidValues() {
        firstNameToBeChecked = "Mickey"
        LastNameToBeChecked = "Mouse"
        addressToBeChecked = "Rua dos Gatos"
    }

    @Test
    fun checkTextViewHelloContent() {
        Espresso.onView(ViewMatchers.withId(R.id.EditButton)).perform(click())
    }

}
