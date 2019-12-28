package com.example.project1

import kotlin.reflect.typeOf

class Addr_Profile(photo: Int, name: String, telephone: String){
    var photo : Int = 0
    var name : String = "default"
    var addr : String = "000-0000-0000"

    init {
        this.photo = R.drawable.def_icon
        this.name = name
        this.addr = telephone
    }
}