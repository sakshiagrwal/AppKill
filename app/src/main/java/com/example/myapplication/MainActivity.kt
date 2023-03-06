package com.example.myapplication

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var appListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appListRecyclerView = findViewById(R.id.appListRecyclerView)
        appListRecyclerView.layoutManager = LinearLayoutManager(this)

        setupAppList()
    }

    private fun setupAppList() {
        val appList = getInstalledApps()
        val adapter = AppListAdapter(appList)
        appListRecyclerView.adapter = adapter
    }

    private fun getInstalledApps(): List<AppInfo> {
        val pm = packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        val appList = mutableListOf<AppInfo>()
        for (app in apps) {
            if (pm.getLaunchIntentForPackage(app.packageName) != null) {
                val appInfo = AppInfo(
                    app.loadLabel(pm).toString(),
                    app.loadIcon(pm),
                    app.packageName
                )
                appList.add(appInfo)
            }
        }
        return appList
    }
}
