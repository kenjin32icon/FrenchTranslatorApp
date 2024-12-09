package com.example.frenchtranslator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _isRecording = MutableLiveData<Boolean>()
    val isRecording: LiveData<Boolean> = _isRecording

    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String> = _translatedText

    private var audioRecorder: AudioRecorder? = null
    private var translator: Translator? = null

    init {
        _isRecording.value = false
        audioRecorder = AudioRecorder(application)
        translator = Translator(application)
    }

    fun startRecording() {
        viewModelScope.launch {
            audioRecorder?.startRecording { audioData ->
                // Process audio data
                translator?.translate(audioData) { translatedText ->
                    _translatedText.postValue(translatedText)
                }
            }
            _isRecording.value = true
        }
    }

    fun stopRecording() {
        viewModelScope.launch {
            audioRecorder?.stopRecording()
            _isRecording.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        audioRecorder?.stopRecording()
        audioRecorder = null
        translator = null
    }
}
