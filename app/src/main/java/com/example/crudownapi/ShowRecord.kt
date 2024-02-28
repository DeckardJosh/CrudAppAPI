package com.example.crudownapi

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ShowRecord : BaseActivity() {
    lateinit var record : RecordsItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_record)

        val txtIDDate: TextView = findViewById(R.id.txtIDData)
        val txtNameData : TextView = findViewById(R.id.edtNameData)
        val txtDescriptionData : TextView = findViewById(R.id.edtDescriptionData)
        val txtPriceData : TextView = findViewById(R.id.edtPriceData)
        val txtRatingData : TextView = findViewById(R.id.edtRatingData)
        val imgImageData : ImageView = findViewById(R.id.imgImageData)
        val txtDateModData : TextView = findViewById(R.id.txtDateModData)
        val txtDateCreateData : TextView = findViewById(R.id.txtDateCreateData)

        record = recordsList[currentRecord]

        txtIDDate.text = record.id.toString()
        txtNameData.text = record.name
        txtDescriptionData.text = record.description
        txtPriceData.text = record.price
        txtRatingData.text = record.rating
        //image view population here
        val queue = Volley.newRequestQueue(this)
        val imgURL = record.image

        val imageRequest = ImageRequest(
            imgURL,
            Response.Listener<Bitmap> { bitmap ->
                imgImageData.setImageBitmap(bitmap)
            },
            400,
            600,
            ImageView.ScaleType.CENTER_INSIDE,
            Bitmap.Config.RGB_565,
            Response.ErrorListener { error ->
                error.printStackTrace()
            })
        imageRequest.setShouldCache(false)
        queue.add(imageRequest)

        txtDateModData.text = record.dateMod
        txtDateCreateData.text = record.dateCreate
    }

    fun onDeleteClick(v: View){
        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this record?")
            .setCancelable(false)
            .setPositiveButton("Yes") {
                dialog, which -> //Preform yes action

                recordsList.removeAt(currentRecord)

                //VOLLEY
                val queue = Volley.newRequestQueue(this)

                val jsonObjectRequest = JsonObjectRequest(
                    Request.Method.DELETE, baseUrl + "/${record.id}", null,
                    { response ->
                        Log.i("CRUDAPI", "Response is ${response.toString()}")
                    },
                    {
                        Log.i("CRUDAPI", "Error - ${it.message}")
                    })
                jsonObjectRequest.setShouldCache(false)
                queue.add(jsonObjectRequest)

                //go to another screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("No") {
                dialog, which -> //Preform no action

                dialog.cancel()
            }
            .create()
            .show()
    }

    fun onEditClick(v: View) {
        val intent = Intent(this, EditRecord::class.java)
        startActivity(intent)
    }

    fun onShowAllClick(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}