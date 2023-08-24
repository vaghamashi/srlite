package com.example.externaldb

class ShayariModel {

    var id = 0
    lateinit var shayari:String
    lateinit var category:String

    constructor(id: Int, shayari: String, category: String) {
        this.id = id
        this.shayari = shayari
        this.category = category
    }
}