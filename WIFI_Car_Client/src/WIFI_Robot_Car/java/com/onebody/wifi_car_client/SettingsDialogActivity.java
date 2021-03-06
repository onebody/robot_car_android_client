package com.onebody.wifi_car_client;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsDialogActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_dialog);
        EditText txtServer = (EditText) findViewById(R.id.txtServer);
//        txtServer.setText(WebIOPI_Util.webiopi_server);
        txtServer.setText(SocketUtil.HOST);
//        EditText txtUser = (EditText) findViewById(R.id.user);
//        txtUser.setText(WebIOPI_Util.webiopi_username);
//        EditText txtPassword = (EditText) findViewById(R.id.password);
//        txtPassword.setText(WebIOPI_Util.webiopi_password);
        showDialog(0);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog settingsDialog;

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.activity_settings_dialog, null);
        builder.setTitle("登录");
        builder.setView(layout);
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Log.i("测试", "登录 >>>>\n");
                EditText txtServer = (EditText) findViewById(R.id.txtServer);
                String hostIP = txtServer.getText().toString();

                EditText txtUser = (EditText) findViewById(R.id.user);
                String userName = txtUser.getText().toString();

                EditText txtPassword = (EditText) findViewById(R.id.password);
                String password = txtPassword.getText().toString();
//                WebIOPI_Util.getGPIO(hostIP, userName, password);
//                HttpRequest.serverHost = hostIP + ":9999/robot_api";
                SocketUtil.HOST = hostIP;

                Toast.makeText(SettingsDialogActivity.this, "登录成功", Toast.LENGTH_LONG).show();

          /*      Intent i = new Intent(SettingsDialogActivity.this, RobotActivity.class);
                Bundle b = new Bundle();
                b.putString("str", "哈哈");
                i.putExtras(b);
                SettingsDialogActivity.this.setResult(RESULT_OK, i);*/
                SettingsDialogActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                SettingsDialogActivity.this.finish();
            }
        });
        settingsDialog = builder.create();
        return settingsDialog;
    }
}