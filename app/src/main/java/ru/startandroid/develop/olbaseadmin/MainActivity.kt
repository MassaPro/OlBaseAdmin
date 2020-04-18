package ru.startandroid.develop.olbaseadmin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list_content.view.*

var ITEMS: MutableList<String> = mutableListOf(("L"), ("N"), ("D"), ("L"), ("L"), ("L"), ("L"), ("L"), ("L"), ("L"))

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupRecyclerView(item_list)
        //window.navigationBarColor = Color.BLACK
        //window.decorView.setBackgroundColor(Color.BLACK)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, ITEMS)
    }

    class SimpleItemRecyclerViewAdapter(private val parentActivity: MainActivity,
                                        private val values: List<String>):
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
            val item = v.tag as String
                val intent = Intent(v.context, MainActivity::class.java).apply {
                    //putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                }
                v.context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (position % 2 == 0) {

            }
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("TAG", "Database error")
                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    //Get Post object and use the values to update the UI
                    val item: String = dataSnapshot.child("vector").child(position.toString()).child("content").getValue().toString()
                    holder.idView.text = item
                    holder.contentView.text = item + item
                    //holder.card
                    //holder.contentView.setTextColor(Color.parseColor("#" + getColorByRating(item.rating)))
                    //holder.ratingView.setTextColor(Color.parseColor("#" + getColorByRating(item.rating)))

                    with(holder.itemView) {
                        tag = item
                        setOnClickListener(onClickListener)
                    }
                    //holder.setBackgroundColor(Color.parseColor("#FF4081"))
                }
            })


        }

        override fun getItemCount() = 100

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView = view.content
            //val card: CardView = view.card as CardView
        }
    }
}
