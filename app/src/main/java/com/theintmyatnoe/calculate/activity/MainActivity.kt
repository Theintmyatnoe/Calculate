package com.theintmyatnoe.calculate.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.theintmyatnoe.calculate.R
import com.theintmyatnoe.calculate.fragment.CategoryFragment
import com.theintmyatnoe.calculate.fragment.ChartFragment
import com.theintmyatnoe.calculate.fragment.HomeFragment
import com.theintmyatnoe.calculate.fragment.SettingFragment
import com.theintmyatnoe.calculate.util.Common
import com.theintmyatnoe.calculate.util.Constant
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var fragment: Fragment? = null
    var fragmentClass: Class<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        var headerView=navView.getHeaderView(0)
//        var navUserName=headerView.findViewById<TextView>(R.id.nav_user_name)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        if (Common.getDataFromSharedPref(Constant.key_go_to,this).toString()==Constant.go_to_category){
            fragmentClass = HomeFragment::class.java
        }
        else if (Common.getDataFromSharedPref(Constant.key_go_to,this).toString()==Constant.go_to_chart){
            fragmentClass = ChartFragment::class.java
        }
        else if (Common.getDataFromSharedPref(Constant.key_go_to,this).toString()==Constant.go_to_setting){
            fragmentClass = SettingFragment::class.java
        }
        else{
            fragmentClass = HomeFragment::class.java
        }


        callFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var needToCall = false
        when (item.itemId) {
            R.id.nav_home->{
                fragmentClass = HomeFragment::class.java
                needToCall = true
            }
            R.id.nav_category->{
                fragmentClass = CategoryFragment::class.java
                needToCall = true
            }
            R.id.nav_chart->{
                fragmentClass = ChartFragment::class.java
                needToCall = true
            }R.id.nav_setting->{
            fragmentClass = SettingFragment::class.java
            needToCall = true
        }

        }
        if (needToCall)
            callFragment()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun callFragment() {
        try {
            fragment = fragmentClass?.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val fragmentManager = supportFragmentManager
        fragment?.let { fragmentManager.beginTransaction().replace(R.id.frameLayout, it).commit() }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
