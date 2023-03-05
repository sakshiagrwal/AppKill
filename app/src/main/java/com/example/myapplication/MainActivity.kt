package com.example.myapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var appListRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Call findViewById to get a reference to appListRecyclerView
        appListRecyclerView = findViewById(R.id.appListRecyclerView)

        // Call the setupAppList function to display the list of installed apps
        setupAppList()
    }

    // Define the getInstalledApps function to retrieve a list of installed apps
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

    // Define the setupAppList function to display the list of installed apps in the RecyclerView
    private fun setupAppList() {
        val appList = getInstalledApps()
        val adapter = AppListAdapter(appList)
        appListRecyclerView.adapter = adapter
    }
}
