package edu.ufp.pam.examples.oleazinho

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import edu.ufp.pam.examples.oleazinho.R
import edu.ufp.pam.examples.oleazinho.project.GUI.EditProfileActivity
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class EditProfileActivityTest  {

    private lateinit var firstToBeChecked: String
    private lateinit var usernameToBeChecked: String

    @get:Rule
    var activityRule: ActivityTestRule<EditProfileActivity>
            = ActivityTestRule(EditProfileActivity::class.java)

    @Before
    fun initValidValues() {
        firstToBeChecked = "Mini"
        usernameToBeChecked = "Mini"
    }

    @Test
    fun changeFirstNameContent() {
        Espresso.onView(ViewMatchers.withId(R.id.EditFirstNameEditText))
            .perform(ViewActions.clearText(), ViewActions.typeText(firstToBeChecked));
    }







}
