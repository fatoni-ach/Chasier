package com.example.chasier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chasier.adapter.AdapterRiwayat
import com.example.chasier.api.ApiMain
import com.example.chasier.viewmodel.ActivityViewModel
import kotlinx.android.synthetic.main.activity_riwayat.*

class RiwayatActivity : AppCompatActivity() {

    lateinit var activityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        RV_riw.setHasFixedSize(true)
        RV_riw.layoutManager = LinearLayoutManager(this)

        activityViewModel.getRiwayat("bd0b3ae6651538fac2515baafc9326c5")!!.observe(this, Observer { serviceSetterGetter ->
            val msg = serviceSetterGetter
            val adapter2 = AdapterRiwayat(msg)
            adapter2.notifyDataSetChanged()
            RV_riw.adapter = adapter2

        })

    }
}