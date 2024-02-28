package com.example.crudownapi

class RecordsItem(var id: Int, var name: String, var description: String, var price: String, var rating: String, var image: String, var dateCreate: String, var dateMod: String) {

    fun toCSV(): String {
        return "$id,$name,$description,$price,$rating"
    }

    override fun toString(): String {
        return "$name : $description"
    }
}