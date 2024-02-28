package com.example.crudownapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddRecord : BaseActivity() {
    lateinit var edtNameData: EditText
    lateinit var edtDescriptionData: EditText
    lateinit var edtPriceData: EditText
    lateinit var edtRatingData: EditText
    lateinit var edtImageData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_record)

        edtNameData = findViewById(R.id.edtNameData)
        edtDescriptionData = findViewById(R.id.edtDescriptionData)
        edtPriceData = findViewById(R.id.edtPriceData)
        edtRatingData = findViewById(R.id.edtRatingData)
        edtImageData = findViewById(R.id.edtImageData)
    }

    fun onCancelClick(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onAddRecordClick(v: View) {

        if (edtNameData.text.isNotEmpty() && edtRatingData.text.isNotEmpty() && edtPriceData.text.isNotEmpty() && edtImageData.text.isNotEmpty()){

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)

            //VOLLEY
            val queue = Volley.newRequestQueue(this)

            val request: StringRequest = object : StringRequest(
                Method.POST, "$baseUrl",
                { response ->
                    Log.i("CRUDAPI", "Response is ${response.toString()}")
                },
                {
                    Log.i("CRUDAPI", "Error - ${it.message}")
                }){
                override fun getParams(): MutableMap<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["name"] = edtNameData.text.toString()
                    params["description"] = edtDescriptionData.text.toString()
                    params["price"] = edtPriceData.text.toString()
                    params["rating"] = edtRatingData.text.toString()
                    params["image"] = edtImageData.text.toString()
                    params["created_at"] = current.toString()
                    params["updated_at"] = current.toString()
                    return params
                }
            }
            request.setShouldCache(false)
            queue.add(request)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            toastIt("Values Must not be Empty")
        }
    }

    fun onShowAllClick(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}