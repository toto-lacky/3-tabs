package com.example.project1

class Addr_Profile(photo: Int, name: String, telephone: String){
    var photoid : Int = R.drawable.def_icon
    var name : String = "default"
    var addr : String = "000-0000-0000"
    var id: Int = 0

    init {
        this.photoid = photo
        this.name = name
        this.addr = telephone
    }
/*
    fun getPhotoId() : Int {
        return photoid
    }

    fun setPhotoId(pid : Int) {
        this.photoid = pid
    }

    fun getName() : String {
        return this.name
    }
*/
}