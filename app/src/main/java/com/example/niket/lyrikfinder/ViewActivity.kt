package com.example.niket.lyrikfinder


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*


class ViewActivity:AppCompatActivity() {
    lateinit var heroList: MutableList<Hero>
    lateinit var ref: DatabaseReference
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        heroList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("songs")
        listView = findViewById(R.id.listView)
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    heroList.clear()
                    for (h in p0.children) {
                        val hero = h.getValue(Hero::class.java)
                        heroList.add(hero!!)
                    }
                    val adapter = HeroAdapter(applicationContext, R.layout.heroes, heroList)
                    listView.adapter = adapter
                }


            }
        })
        listView.setOnItemClickListener { parent, view, position, id ->
            //Toast.makeText(applicationContext,"age is = "+playerList[position].age,Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ViewActivity, InfoActivity::class.java).putExtra("URL",heroList[position].url)
            intent.putExtra("Name",heroList[position].name)
            intent.putExtra("Lyrics",heroList[position].lyricname)

            println("Hi")
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
     getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

}
