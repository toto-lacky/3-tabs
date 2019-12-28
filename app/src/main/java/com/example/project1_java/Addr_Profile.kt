package com.example.project1_java

class Addr_Profile(photo: Long, person_id: Long, name: String, telephone: String){
    var photoid : Long = R.drawable.def_icon.toLong()
    var name : String = "default"
    var addr : String = "000-0000-0000"
    var personId : Long = 0
    var id: Int = 0

    init {
        this.photoid = photo
        this.personId = person_id
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