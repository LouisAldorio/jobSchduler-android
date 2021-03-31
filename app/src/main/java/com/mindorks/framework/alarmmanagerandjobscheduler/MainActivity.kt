package com.mindorks.framework.alarmmanagerandjobscheduler

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mPendingIntent: PendingIntent? = null
    private var sendIntent: Intent? = null
    private var mAlarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        setAlarm.setOnClickListener {
            if(mPendingIntent!=null){
                mAlarmManager?.cancel(mPendingIntent)
                mPendingIntent?.cancel()
            }
            var alarmTimer = Calendar.getInstance()

            alarmTimer.add(Calendar.SECOND,15)
            sendIntent = Intent(this, MyAlarmReceiver::class.java)
            sendIntent?.putExtra(EXTRA_PESAN,myMessage.text.toString())

            mPendingIntent = PendingIntent.getBroadcast(this,101,sendIntent,0)

//            mAlarmManager?.set(AlarmManager.RTC,alarmTimer.timeInMillis,mPendingIntent)
            mAlarmManager?.setInexactRepeating(AlarmManager.RTC,alarmTimer.timeInMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES,mPendingIntent)
            Toast.makeText(this,"Scheduler Di Aktifkan",Toast.LENGTH_SHORT).show()
        }
        cancelAlarm.setOnClickListener {
            if(mPendingIntent!=null){
                mAlarmManager?.cancel(mPendingIntent)
                mPendingIntent?.cancel()
                Toast.makeText(this,"Scheduler Di matikan", Toast.LENGTH_SHORT).show()
            }

        }
    }
}