package ru.startandroid.develop.olbaseadmin

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

val database = FirebaseDatabase.getInstance()
val myRef = database.getReference("message")

fun writeNewUser(user: User) {
    myRef.setValue(user)
}

fun writeNewOlympiad() {
    //TODO()
}

