package com.example.frenchtranslator

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var recordButton: FloatingActionButton
    private lateinit var translatedText: TextView
    private lateinit var statusText: TextView
    
    private val PERMISSION_REQUEST_CODE = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize views
        recordButton = findViewById(R.id.recordButton)
        translatedText = findViewById(R.id.translatedText)
        statusText = findViewById(R.id.statusText)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        
        // Set up click listeners
        recordButton.setOnClickListener {
            if (checkPermissions()) {
                toggleRecording()
            } else {
                requestPermissions()
            }
        }
        
        // Observe ViewModel
        viewModel.isRecording.observe(this) { isRecording ->
            updateUI(isRecording)
        }
        
        viewModel.translatedText.observe(this) { text ->
            translatedText.text = text
        }
    }
    
    private fun toggleRecording() {
        if (viewModel.isRecording.value == true) {
            viewModel.stopRecording()
        } else {
            viewModel.startRecording()
        }
    }
    
    private fun updateUI(isRecording: Boolean) {
        recordButton.setImageResource(
            if (isRecording) android.R.drawable.ic_media_pause
            else android.R.drawable.ic_btn_speak_now
        )
        statusText.text = if (isRecording) "Recording..." else "Ready to translate"
    }
    
    private fun checkPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            PERMISSION_REQUEST_CODE
        )
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toggleRecording()
            } else {
                Toast.makeText(
                    this,
                    "Microphone permission is required for translation",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
