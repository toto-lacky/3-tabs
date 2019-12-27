package com.example.project1

class Addr_Profile(photo: String, name: String, telephone: String){
    var photo : String = ""
    var name : String = "default"
    var addr : String = "000-0000-0000"

    init {
        this.photo = photo
        this.name = name
        this.addr = telephone
    }
}