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
import com.example.proyectoelectivaui.R
import android.Manifest
import android.app.PendingIntent
import android.util.Log
import com.example.proyectoelectivaui.MainActivity2

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



            showNotification(context, title, text)
        }
    }

    private fun showNotification(context: Context, title: String?, text: String?) {

        // Check if the required permission is granted
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.VIBRATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }


        // Crear un canal de notificación (para dispositivos corriendo Android 8.0 y superior)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Crear el patron de vibración
        val vibrationPattern = longArrayOf(0, 500, 200, 500)


        // Crear la notificación
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notificacion)
            .setContentTitle(title)
            .setContentText("Hacer el pago en " + text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(vibrationPattern)
            .setFullScreenIntent(getFullScreenIntent(context), true)
            .setAutoCancel(true)


        // Mostrar la notificación
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getFullScreenIntent(context: Context): PendingIntent {
        val intent = Intent(context, MainActivity2::class.java)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

}