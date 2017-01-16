package com.cpfei.project.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.utils.PermissionUtil;

/**
 * Normal Permissions就是不用动态申请的权限
 * <p>
 * android.permission.ACCESS_LOCATION_EXTRA_COMMANDS
 * android.permission.ACCESS_NETWORK_STATE
 * android.permission.ACCESS_NOTIFICATION_POLICY
 * android.permission.ACCESS_WIFI_STATE
 * android.permission.ACCESS_WIMAX_STATE
 * android.permission.BLUETOOTH
 * android.permission.BLUETOOTH_ADMIN
 * android.permission.BROADCAST_STICKY
 * android.permission.CHANGE_NETWORK_STATE
 * android.permission.CHANGE_WIFI_MULTICAST_STATE
 * android.permission.CHANGE_WIFI_STATE
 * android.permission.CHANGE_WIMAX_STATE
 * android.permission.DISABLE_KEYGUARD
 * android.permission.EXPAND_STATUS_BAR
 * android.permission.FLASHLIGHT
 * android.permission.GET_ACCOUNTS
 * android.permission.GET_PACKAGE_SIZE
 * android.permission.INTERNET
 * android.permission.KILL_BACKGROUND_PROCESSES
 * android.permission.MODIFY_AUDIO_SETTINGS
 * android.permission.NFC
 * android.permission.READ_SYNC_SETTINGS
 * android.permission.READ_SYNC_STATS
 * android.permission.RECEIVE_BOOT_COMPLETED
 * android.permission.REORDER_TASKS
 * android.permission.REQUEST_INSTALL_PACKAGES
 * android.permission.SET_TIME_ZONE
 * android.permission.SET_WALLPAPER
 * android.permission.SET_WALLPAPER_HINTS
 * android.permission.SUBSCRIBED_FEEDS_READ
 * android.permission.TRANSMIT_IR
 * android.permission.USE_FINGERPRINT
 * android.permission.VIBRATE
 * android.permission.WAKE_LOCK
 * android.permission.WRITE_SYNC_SETTINGS
 * com.android.alarm.permission.SET_ALARM
 * com.android.launcher.permission.INSTALL_SHORTCUT
 * com.android.launcher.permission.UNINSTALL_SHORTCUT
 * <p>
 * <p>
 * Dangerous Permissions就是需要动态申请的权限
 * <p>
 * group.CONTACTS
 * android.permission.WRITE_CONTACTS
 * android.permission.GET_ACCOUNTS
 * android.permission.READ_CONTACTS
 * <p>
 * group.PHONE
 * android.permission.READ_CALL_LOG
 * android.permission.READ_PHONE_STATE
 * android.permission.CALL_PHONE
 * android.permission.WRITE_CALL_LOG
 * android.permission.USE_SIP
 * android.permission.PROCESS_OUTGOING_CALLS
 * com.android.voicemail.permission.ADD_VOICEMAIL
 * <p>
 * group.CALENDAR
 * android.permission.READ_CALENDAR
 * android.permission.WRITE_CALENDAR
 * <p>
 * group.CAMERA
 * android.permission.CAMERA
 * <p>
 * group.SENSORS
 * android.permission.BODY_SENSORS
 * <p>
 * group.LOCATION
 * android.permission.ACCESS_FINE_LOCATION
 * android.permission.ACCESS_COARSE_LOCATION
 * <p>
 * group.STORAGE
 * android.permission.READ_EXTERNAL_STORAGE
 * android.permission.WRITE_EXTERNAL_STORAGE
 * <p>
 * group.MICROPHONE
 * android.permission.RECORD_AUDIO
 * <p>
 * group.SMS
 * android.permission.READ_SMS
 * android.permission.RECEIVE_WAP_PUSH
 * android.permission.RECEIVE_MMS
 * android.permission.RECEIVE_SMS
 * android.permission.SEND_SMS
 * android.permission.READ_CELL_BROADCASTS
 */
public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * demo 实现了系统相机权限申请
     */
    protected final int REQUEST_CODE = 1001;
    protected final int PERMS_REQUEST_CODE = 200;
    protected ImageView cameraImage;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, PermissionActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        findViewById(R.id.btnCamera).setOnClickListener(this);
        cameraImage = ((ImageView) findViewById(R.id.cameraImage));

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCamera) {
            // true 不需要申请权限， false 需要申请权限
            if (PermissionUtil.hasSelfPermission(this, Manifest.permission.CAMERA)) {
                intent2Camera();
            } else {
                String[] perms = {Manifest.permission.CAMERA};
                requestPermissions(perms, PERMS_REQUEST_CODE);
            }
        }
    }

    private void intent2Camera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            cameraImage.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMS_REQUEST_CODE:
                boolean request = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (request) {
                    intent2Camera();
                } else {
                    Toast.makeText(this, "请开启相应权限使用相机", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
