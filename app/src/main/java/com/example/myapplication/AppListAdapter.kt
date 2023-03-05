package com.example.myapplication

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppListAdapter(private val appList: List<AppInfo>) : RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appNameTextView: TextView = itemView.findViewById(R.id.appNameTextView)
        val appIconImageView: ImageView = itemView.findViewById(R.id.appIconImageView)
        val appCheckBox: CheckBox = itemView.findViewById(R.id.appCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        // Inflate the app list item layout
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.app_list_item, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        // Retrieve the app info for the current position
        val appInfo = appList[position]

        // Set the text and icon for the app name and icon views
        holder.appNameTextView.text = appInfo.name
        holder.appIconImageView.setImageDrawable(appInfo.icon)

        // Set the tag for the checkbox to the app's package name
        holder.appCheckBox.tag = appInfo.packageName

        // Set an OnClickListener for the checkbox to toggle the app's selected status
        holder.appCheckBox.setOnClickListener {
            val isChecked = holder.appCheckBox.isChecked
            appInfo.isSelected = isChecked
        }
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    // Define a function to retrieve the list of selected apps
    fun getSelectedApps(): List<AppInfo> {
        return appList.filter { appInfo -> appInfo.isSelected }
    }
}
