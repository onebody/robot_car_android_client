package com.onebody.wifi_car_client;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.onebody.wifi_car_client.webiopi_util.RobotMotorAPI;
import com.onebody.wifi_car_client.webiopi_util.StepMotorAPI;

public class RobotActivity extends Activity {
    private RobotMotorAPI motor = new RobotMotorAPI();
    private StepMotorAPI stepMotor = new StepMotorAPI();
    private boolean stepMotorIsInited = false;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //取出字符串
//        Bundle bundle = data.getExtras();
//        String str = bundle.getString("str");
//        Log.i("测试", str + "\n");
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot);

        findViewById(R.id.btnSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("测试", "设置 >>>>\n");
                Intent i = new Intent(RobotActivity.this, SettingsDialogActivity.class);
                startActivityForResult(i, 0);
            }
        });


        findViewById(R.id.btn_motor_left).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "turn left >>>>\n");
                                motor.turn_left_forward();
                            }
                        };
                        runnable.run();
                        break;
                    case MotionEvent.ACTION_UP:
                        //弹起
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "stop >>>>\n");
                                motor.stop();
                            }
                        };
                        runnable.run();
                        break;
                }
                return true;
            }
        }

        );


        findViewById(R.id.btn_motor_right).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "turn right >>>>\n");
                                motor.turn_right_forward();
                            }
                        };
                        runnable.run();
                        break;
                    case MotionEvent.ACTION_UP:
                        //弹起
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "stop >>>>\n");
                                motor.stop();
                            }
                        };
                        runnable.run();
                        break;
                }
                return true;
            }
        }

        );

        findViewById(R.id.btn_auto_robot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("测试", "auto  >>>>\n");
                        motor.auto();
                    }
                };
                runnable.run();
            }

        }

        );

        findViewById(R.id.btn_motor_up).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "forward >>>>\n");
                                motor.forward();
                            }
                        };
                        runnable.run();
                        break;
                    case MotionEvent.ACTION_UP:
                        //弹起
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "stop >>>>\n");
                                motor.stop();
                            }
                        };
                        runnable.run();
                        break;
                }
                return true;
            }
        }

        );

        findViewById(R.id.btn_motor_down).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "backward >>>>\n");
                                motor.backward();
                            }
                        };
                        runnable.run();
                        break;
                    case MotionEvent.ACTION_UP:
                        //弹起
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                Log.i("测试", "stop >>>>\n");
                                motor.stop();
                            }
                        };
                        runnable.run();
                        break;
                }
                return true;
            }
        }

        );


        findViewById(R.id.btn_webCam_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("测试", "webcam left >>>>\n");
                        if (!stepMotorIsInited) {
                            stepMotor.setup(8, 11, 25, 7, 0.01, 20);
                            stepMotorIsInited = true;
                        }
                        stepMotor.left();
                    }
                };
                runnable.run();
            }
        }

        );


        findViewById(R.id.btn_webCam_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Log.i("测试", "webcam right >>>>\n");
                        if (!stepMotorIsInited) {
                            stepMotor.setup(8, 11, 25, 7, 0.01, 20);
                            stepMotorIsInited = true;
                        }
                        stepMotor.right();
                    }
                };
                runnable.run();
            }
        }

        );


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
