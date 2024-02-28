package com.example.crudownapi

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var recordsList = ArrayList<RecordsItem>()
var currentRecord = 0
val baseUrl = "https://testapi.jdsoftdev.com/api/products"

open class BaseActivity() : AppCompatActivity() {
    open fun toastIt(msg: String?) {
        Toast.makeText(
            applicationContext,
            msg, Toast.LENGTH_SHORT
        ).show()
    }
}