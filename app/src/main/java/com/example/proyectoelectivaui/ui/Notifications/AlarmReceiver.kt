package com.example.proyectoelectivaui.ui.Notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
//import com.example.proyectoelectivaui.Manifest
import com.example.proyectoelectivaui.R
import android.Manifest
import android.app.Activity
import android.util.Log
import androidx.core.app.ActivityCompat

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private const val CHANNEL_ID = "alarm_channel"
        private const val NOTIFICATION_ID = 1
    }



    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "com.example.proyectoelectivaui.EVENT_REMINDER") {
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("text")
            if (text != null) {
                Log.d("texto", text)
            }
            // Handle the specific alarm action

            // Display a notification
            showNotification(context, title, text)

            // Play a sound
            //playSound(context)

            // Start a service or perform other actions as needed
            //startBackgroundService(context)
        }
    }

    private fun showNotification(context: Context, title: String?, text: String?) {

        // Check if the required permission is granted
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.VIBRATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Handle the case where the permission is not granted
            // You can show a dialog or request the permission from the user
            return
        }


        // Create a notification channel (for devices running Android 8.0 and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create the vibration pattern
        val vibrationPattern = longArrayOf(0, 500, 200, 500)


        // Create the notification
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notificacion)
            .setContentTitle(title)
            .setContentText("Hacer el pago en " + text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVibrate(vibrationPattern)
            .build()

        // Show the notification
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

}