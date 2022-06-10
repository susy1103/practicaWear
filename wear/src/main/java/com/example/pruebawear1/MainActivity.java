package com.example.pruebawear1;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear1.databinding.ActivityMainBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;
    private Button wBoton = null;
    private Intent intent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notification;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi Canal";
    int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;
    String longText = "Without Bigstyle, only a single line of text would be visible"+
            "Any addtitional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = findViewById(R.id.wButton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();
        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wBoton.setOnClickListener(new View.OnClickListener() {

            public void start(View view){
                Timer t = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        String name = "Notification 2";
                        NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                        nm.createNotificationChannel(notificationChannel);

                        pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                        notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Mi notification")
                                .setContentText("Mi segunda notificacion wear")
                                .extend(wearableExtender);
                        nm.notify(idNotification, notification.build());
                    }
                };

                t.schedule(tt, 5000);

            }

            @Override
            public void onClick(View view) {
                start(view);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notification";
                NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Mi notification")
                        .setContentText("Mi primera notificacion wear")
                        .extend(wearableExtender);
                nm.notify(idNotification, notification.build());
            }
        });
    }
  /*notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Wear")
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setStyle(bigTextStyle)
                        .setVibrate(new long[]{100,200,300,400,500,400,500,400})
                        .setStyle(bigTextStyle);*/


}