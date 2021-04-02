package com.mindorks.framework.alarmmanagerandjobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.location.LocationManager
import android.preference.PreferenceActivity
import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header



class CuacaHariIni : JobService() {
    val AppID = "f99de39bc639da83eb6f9c7592531c7e"
    val Kota = "Medan"


    override fun onStartJob(p0: JobParameters?): Boolean {
        Log.w("TAG","START JOB")
        getCuacaHariIni(p0)
        return false
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        Log.w("TAG","STOP JOB")
        return true
    }

    private fun getCuacaHariIni(p0: JobParameters?) {
        var client = AsyncHttpClient()
        var url = "http://api.openweathermap.org/data/2.5/weather?q=$Kota&AppID=$AppID"
        val charset = Charsets.UTF_8
        var handler = object : AsyncHttpResponseHandler() {

            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?) {
                var result = responseBody?.toString(charset) ?: "Kosong"
                Log.w("TAG",result)
                jobFinished(p0,true)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable?) {
                jobFinished(p0,true)
                Log.w("TAG", "Gagal")
            }
        }
        client.get(url,handler)
    }

}