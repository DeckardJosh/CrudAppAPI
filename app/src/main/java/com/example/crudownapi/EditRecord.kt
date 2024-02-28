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

class EditRecord : BaseActivity() {
    lateinit var record : RecordsItem
    lateinit var edtNameData : EditText
    lateinit var edtDescriptionData : EditText
    lateinit var edtPriceData : EditText
    lateinit var edtRatingData : EditText
    lateinit var edtImageData2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_record)

        edtNameData = findViewById(R.id.edtNameData)
        edtDescriptionData = findViewById(R.id.edtDescriptionData)
        edtPriceData = findViewById(R.id.edtPriceData)
        edtRatingData = findViewById(R.id.edtRatingData)
        edtImageData2 = findViewById(R.id.edtImageData2)

        record = recordsList[currentRecord]

        edtNameData.setText(record.name)
        edtDescriptionData.setText(record.description)
        edtPriceData.setText(record.price)
        edtRatingData.setText(record.rating)
        edtImageData2.setText(record.image)
    }

    fun onSubmitClick(v: View) {

        if (edtNameData.text.isNotEmpty() && edtRatingData.text.isNotEmpty() && edtPriceData.text.isNotEmpty() && edtImageData2.text.isNotEmpty()){
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)

            var record = recordsList[currentRecord]
            record.name = edtNameData.text.toString()
            record.description = edtDescriptionData.text.toString()
            record.price = edtPriceData.text.toString()
            record.rating = edtRatingData.text.toString()
            record.image = edtImageData2.text.toString()
            record.dateMod = current.toString()

            recordsList[currentRecord] = record

            //VOLLEY
            val queue = Volley.newRequestQueue(this)

            val request: StringRequest = object : StringRequest(
                Method.PUT, "$baseUrl/${record.id}",
                { response ->
                    Log.i("CRUDAPI", "Response is ${response.toString()}")
                },
                {
                    Log.i("CRUDAPI", "Error - ${it.message}")
                }){
                override fun getParams(): MutableMap<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["name"] = record.name
                    params["description"] = record.description
                    params["price"] = record.price
                    params["rating"] = record.rating
                    params["image"] = record.image
                    params["updated_at"] = current.toString()
                    return params
                }
            }
            request.setShouldCache(false)
            queue.add(request)

            val intent = Intent(this, ShowRecord::class.java)
            startActivity(intent)
        } else {
            toastIt("Please check the values you entered")
        }

    }

    fun onCancelClick(v: View) {
        val intent = Intent(this, ShowRecord::class.java)
        startActivity(intent)
    }

    fun onShowAllClick(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}