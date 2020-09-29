package edu.ufp.pam.examples.oleazinho

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import edu.ufp.pam.examples.oleazinho.project.GUI.*
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginActivityTest {

    private lateinit var user: String
    private lateinit var pass: String

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity>
            = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun initValidValues() {
        user = "Mickey"
        pass = "secretomickey"
    }

    @Test
    fun changeFirstNameContent() {
        //Write something else on the EditText
        Espresso.onView(ViewMatchers.withId(R.id.UserNameEditText))
            .perform(ViewActions.clearText(), ViewActions.typeText(user));
        Espresso.onView(ViewMatchers.withId(R.id.PasswordEditText))
            .perform(ViewActions.clearText(), ViewActions.typeText(pass));
    }

}
