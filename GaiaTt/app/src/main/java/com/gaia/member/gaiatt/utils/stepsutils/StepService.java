package com.gaia.member.gaiatt.utils.stepsutils;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.IBinder;

import com.gaia.member.androidlib.Steps;
import com.gaia.member.androidlib.db.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by WangHaohan on 2016/4/18.
 */
public class StepService extends Service {
    public static Boolean flag = false;
    private SensorManager sensorManager;
    private StepDetector stepDetector;
    private SimpleDateFormat sdf;
    private String time ;//时间
    private DbHelper dbHelper;
    private Thread thread;


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dbHelper = DbHelper.getDbHelperInstance(this);
        sdf = new SimpleDateFormat("yyyyMMdd");
        time = sdf.format(new Date());
        new Thread(new Runnable() {
            public void run() {
                startStepDetector();
            }
        }).start();

    }

    private void startStepDetector() {
        flag = true;
        stepDetector = new StepDetector(this);
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);//获取传感器管理器的实例
        Sensor sensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//获得传感器的类型，这里获得的类型是加速度传感器
        //此方法用来注册，只有注册过才会生效，参数：SensorEventListener的实例，Sensor的实例，更新速率
        sensorManager.registerListener(stepDetector, sensor,
                SensorManager.SENSOR_DELAY_FASTEST);
        mThread();
    }


    private void mThread() {
        if (thread == null) {

            thread = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (StepService.flag) {
                            long _id=dbHelper.getIdByTime(time);
                            //修改数据库中的值时,主键不能改动
                            //这要根据情况写逻辑,这里只是举个例子
                            Steps steps = new Steps(_id, time, StepDetector.CURRENT_SETP);
                            dbHelper.updateSteps(steps);
                        }
                    }
                }
            });
            thread.start();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
        if (stepDetector != null) {
            sensorManager.unregisterListener(stepDetector);
        }

    }
}
