package com.example.niket.lyrikfinder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*

class HeroAdapter(val mCtx: Context, val layoutResId:Int, val heroList:List<Hero>):ArrayAdapter<Hero>(mCtx,layoutResId,heroList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx);
            val view: View = layoutInflater.inflate(layoutResId, null)
            val textViewName = view.findViewById<TextView>(R.id.textViewName)
            val hero = heroList[position]
            textViewName.text = hero.name
        return view;
        }
    }

