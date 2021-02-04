package com.example.ejercicio2.entities

import android.os.Parcel
import android.os.Parcelable

class Client(private var name: String, private var surname: String = "", private var username: String = "",
             private var email: String = "", private var password: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(username)
        parcel.writeString(email)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Client> {
        override fun createFromParcel(parcel: Parcel): Client {
            return Client(parcel)
        }

        override fun newArray(size: Int): Array<Client?> {
            return arrayOfNulls(size)
        }
    }

    fun convertToHashMap() = hashMapOf("name" to name, "surname" to surname , "username" to username, "email" to email)

    fun getName(): String {
        return name
    }
}
