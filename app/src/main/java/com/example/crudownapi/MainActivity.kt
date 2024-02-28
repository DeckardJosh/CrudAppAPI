package com.example.crudownapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : BaseActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var recordsListAdapter: RecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This is to reset the recordslist so that it does not duplicate on the recycler view
        recordsList.clear()

        recyclerView = findViewById(R.id.recRecords)
        recordsListAdapter = RecordsAdapter( recordsList ) { position ->
            //preform on click event from this cell (position)
            val intent = Intent(this, ShowRecord::class.java)
            currentRecord = position
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = recordsListAdapter

        //VOLLEY
        val queue = Volley.newRequestQueue(this)

        val stringRequest = JsonArrayRequest(
            Request.Method.GET, baseUrl, null,
            { response ->
                for (i in 0 until response.length()){
                    val event: JSONObject = response.getJSONObject(i)
                    val id = event.getInt("id")
                    val name = event.getString("name")
                    val description = event.getString("description")
                    val price = event.getDouble("price")
                    val rating = event.getInt("rating")
                    val image = event.getString("image")
                    val created_at = event.getString("created_at")
                    val updated_at = event.getString("updated_at")
                    val recordsItem = RecordsItem(id,name,description,price.toString(),rating.toString(),image,created_at,updated_at)
                    recordsList.add(recordsItem)
                }
                recordsListAdapter.notifyDataSetChanged()

                Log.i("CRUDAPI", "Response is ${response.toString()}")
            },
            {
                Log.i("CRUDAPI", "Error - ${it.message}")
            })
        stringRequest.setShouldCache(false)
        queue.add(stringRequest)
    }

    fun onMockClick(v: View) {
        toastIt("This button is currently disabled")
    }

    fun onClearClick(v: View){
        toastIt("This button is currently disabled")
    }

    fun onNewRecordClick(v: View) {
        val intent = Intent(this, AddRecord::class.java)
        startActivity(intent)
    }
}