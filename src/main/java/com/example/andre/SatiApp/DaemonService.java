package com.example.andre.SatiApp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class DaemonService extends Service {
    private Timer t;

    @Override
    public void onCreate() {
        t = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        iniciarTarefa();
        return startId;
    }

    @Override
    public void onDestroy() {
        pararTarefa();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void iniciarTarefa()
    {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                long[] padrao = {200,50,200,50,200,50};
                v.vibrate(padrao, -1);
            }
        };
        t.schedule(tt, 0, 5000);
    }

    private void pararTarefa()
    {
        t.cancel();
        t.purge();
    }
}
