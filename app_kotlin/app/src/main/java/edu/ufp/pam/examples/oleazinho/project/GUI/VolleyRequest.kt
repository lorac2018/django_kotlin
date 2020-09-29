package edu.ufp.pam.examples.oleazinho.project.GUI

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.github.kittinunf.fuel.android.extension.jsonDeserializer

class VolleyRequest {
    private var mRequestQueue: RequestQueue ?= null
    private var context: Context ?= null
    private var iVolley:IVolley ?= null

    /*val requestQueue:RequestQueue
        get(){
            if(mRequestQueue == null)
                mRequestQueue = Volley.newRequestQueue(context!!.applicationContext)
            return mRequestQueue!!
        }*/

    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context!!.applicationContext)
    }

    private constructor(context: Context, iVolley: IVolley)
    {
        this.context = context
        this.iVolley = iVolley
        mRequestQueue = requestQueue
    }

    private constructor(context: Context)
    {
        this.context = context
        mRequestQueue = requestQueue
    }

    fun <T> addToRequestQueue(req: Request<T>)
    {
        requestQueue.add(req)
    }

    //GET
    fun getRequest(url : String)
    {
        val getRequest = JsonObjectRequest(Request.Method.GET, url, null,Response.Listener
        { response -> iVolley!!.onResponse(response.toString())

        },Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!)})
        addToRequestQueue(getRequest)
    }

    //POST
    /*fun postRequest(url: String)
    {
        val postRequest = object : StringRequest(Request.Method.POST, url, Response.Listener
        { response -> iVolley!!.onResponse(response.toString())

        },Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!)})
        {
            override fun getParams() : MutableMap<String, String>
            {
                val params = HashMap<String, String>()
                params["username"] = "kitty"
                params["password"] = "secretonilsa"
                //return super.getParams()
                return params
            }
        }
            addToRequestQueue(postRequest)
    }*/

    companion object{
        /*private var mInstance : VolleyRequest ?= null
        @Synchronized
        fun getInstance(context: Context) : VolleyRequest{
            if (mInstance == null)
            {
                mInstance = VolleyRequest(context)
            }
            return mInstance!!
        }

        @Synchronized
        fun getInstance(context: Context, iVolley: IVolley ) : VolleyRequest{
            if (mInstance == null)
            {
                mInstance = VolleyRequest(context, iVolley)
            }
            return mInstance!!
        }*/
        @Volatile
        private var INSTANCE: VolleyRequest? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyRequest(context).also {
                    INSTANCE = it
                }
            }

    }

}