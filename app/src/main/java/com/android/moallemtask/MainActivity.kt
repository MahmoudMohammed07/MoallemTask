package com.android.moallemtask

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.moallemtask.adapter.SubjectAdapter
import com.android.moallemtask.adapter.VideoAdapter
import com.android.moallemtask.interfaces.OnVideoClickListener
import com.android.moallemtask.model.SubjectItem
import com.android.moallemtask.model.VideoItem
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnVideoClickListener {

    private lateinit var mDatabaseRef: DatabaseReference
    private lateinit var subjectList: List<SubjectItem>
    private lateinit var videoList: List<VideoItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(app_bar)

        app_bar.changeToolbarFont()

        videoList = ArrayList()

        subjectList = dummyData(8)

        subject_bar.adapter = SubjectAdapter(subjectList)
        dummyVideos()
    }

    private fun dummyData(size: Int): List<SubjectItem> {
        val list = ArrayList<SubjectItem>()

        for (i in 0 until size) {
            val drawable = when (i % 4) {
                0 -> R.drawable.ic_physics
                1 -> R.drawable.ic_biology
                2 -> R.drawable.ic_history
                else -> R.drawable.ic_algebra
            }
            val title = when (i % 4) {
                0 -> "Physics"
                1 -> "Biology"
                2 -> "History"
                else -> "Algebra"
            }

            val item = SubjectItem(drawable, title)
            list.add(item)
        }
        return list
    }

    private fun dummyVideos() {

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("video")

        mDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot: DataSnapshot in dataSnapshot.children) {
                    val item = postSnapshot.getValue(VideoItem::class.java)
                    videoList = videoList + item!!
                }
                video_bar.adapter = VideoAdapter(videoList, this@MainActivity)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainActivity, databaseError.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onVideoClick(position: Int) {
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra("SELECTED_VIDEO", videoList[position])
        startActivity(intent)
    }

    private fun Toolbar.changeToolbarFont() {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is TextView && view.text == title) {
                view.typeface = Typeface.createFromAsset(assets, "fonts/Harabara.ttf")
                break
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}