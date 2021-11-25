package com.example.maprouteapp.helpers

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.maprouteapp.TaskLoadedCallback
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadTask(private val task: TaskLoadedCallback, private val ctx : Context) : AsyncTask<String?, Void?, String>() {

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            data = downloadUrl(params[0].toString())
            Log.d("data",data)
        } catch (e: Exception) {
            Log.d("Background Task", e.toString())
        }
        return data
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        val parserTask = ParserTask(task, ctx)
        Log.d("data","res= ${result.toString()}")
        parserTask.execute(result)
    }

    @Throws(IOException::class)
    private fun downloadUrl(strUrl: String): String {
        var data = ""
        val url = URL(strUrl)
        val urlConnection = url.openConnection() as HttpURLConnection
        val iStream = urlConnection.inputStream
        try {
            urlConnection.connect()
            val br = BufferedReader(InputStreamReader(iStream))
            val sb = StringBuffer()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                sb.append(line)
            }
            data = sb.toString()
            br.close()
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            iStream.close()
            urlConnection.disconnect()
        }
        return data
    }
}