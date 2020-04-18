package ru.startandroid.develop.olbaseadmin

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

val database = FirebaseDatabase.getInstance()
val myRef = database.getReference("olympiads")

fun writeNewUser(user: User) {
    myRef.setValue(user)
}

fun writeNewOlympiad() {
    //TODO()
}

interface FirebaseCallback {
    //TODO()
    fun onCallback(list: MutableList<Int>)
}

fun readFirebaseData(firebaseCallback: FirebaseCallback, position: String) {
    myRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onCancelled(p0: DatabaseError) {
            //TODO("Not yet implemented")
        }

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            //Get Post object and use the values to update the UI
            //val mess: Users? = dataSnapshot.getValue(Users::class.java)
            //if (mess != null) {

            // } else {
            //  Log.d("TAG", "fail")
            // }
        }
    })
}

