package com.mindorks.framework.alarmmanagerandjobscheduler

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //    private var mPendingIntent: PendingIntent? = null
//    private var sendIntent: Intent? = null
//    private var mAlarmManager: AlarmManager? = null
    var JobId = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        setAlarm.setOnClickListener {
//            if(mPendingIntent!=null){
//                mAlarmManager?.cancel(mPendingIntent)
//                mPendingIntent?.cancel()
//            }
//            var alarmTimer = Calendar.getInstance()
//
//            alarmTimer.add(Calendar.SECOND,15)
//            sendIntent = Intent(this, MyAlarmReceiver::class.java)
//            sendIntent?.putExtra(EXTRA_PESAN,myMessage.text.toString())
//
//            mPendingIntent = PendingIntent.getBroadcast(this,101,sendIntent,0)
//
////            mAlarmManager?.set(AlarmManager.RTC,alarmTimer.timeInMillis,mPendingIntent)
//            mAlarmManager?.setInexactRepeating(AlarmManager.RTC,alarmTimer.timeInMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES,mPendingIntent)
//            Toast.makeText(this,"Scheduler Di Aktifkan",Toast.LENGTH_SHORT).show()
//        }
//        cancelAlarm.setOnClickListener {
//            if(mPendingIntent!=null){
//                mAlarmManager?.cancel(mPendingIntent)
//                mPendingIntent?.cancel()
//                Toast.makeText(this,"Scheduler Di matikan", Toast.LENGTH_SHORT).show()
//            }
//
//        }


        //job Scheduler
        setAlarm.setOnClickListener {
            startMyJob()
        }
        cancelAlarm.setOnClickListener {
            cancelMyJob()
        }
    }

    private fun startMyJob() {
        var serviceComponent = ComponentName(this, CuacaHariIni::class.java)
        val mJobInfo = JobInfo.Builder(JobId, serviceComponent)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
        var JobCuaca = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        JobCuaca.schedule(mJobInfo.build())
    }

    private fun cancelMyJob() {
        var JobCuaca = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        JobCuaca.cancel(JobId)
    }
}