package org.godotengine.godot.gpgs;

import java.lang.IllegalStateException;
import android.util.Log;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient;
import org.godotengine.godot.GodotLib;

import com.google.android.gms.games.Games;

public class Achievements {

    private static final int REQUEST_ACHIEVEMENTS = 9002;

    private Activity activity = null;
    private int instance_id = 0;
    private GoogleApiClient googleApiClient = null;

    private static final String TAG = "godot";

    public Achievements(Activity activity, GoogleApiClient googleApiClient, int instance_id) {
        this.googleApiClient = googleApiClient;
        this.activity = activity;
        this.instance_id = instance_id;
    }

    public void incrementAchy(final String id, final int increment) {
        if (googleApiClient == null || !googleApiClient.isConnected()) return;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Games.Achievements.increment(googleApiClient, id, increment);
                    Log.d(TAG, "GPGS: incrementAchy '" + id + "' by " + increment + ".");
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    public void unlockAchy(final String id) {
        if (googleApiClient == null || !googleApiClient.isConnected()) return;
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    Games.Achievements.unlock(googleApiClient, id);
                    Log.d(TAG, "GPGS: unlockAchy '" + id + "'.");
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

    public void showAchyList() {
        if (googleApiClient == null || !googleApiClient.isConnected()) return;
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    activity.startActivityForResult(Games.Achievements.getAchievementsIntent(googleApiClient), REQUEST_ACHIEVEMENTS);
                    Log.d(TAG, "GPGS: showAchyList.");
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.toString());
                }
            }
        });
    }

}
