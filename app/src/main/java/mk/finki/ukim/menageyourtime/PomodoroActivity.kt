package mk.finki.ukim.menageyourtime

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PomodoroActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var restartButton: Button
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 1500000 // Total time in milliseconds (25 minutes)
    private var timerRunning: Boolean = false
    private var paused: Boolean = false
    private var pausedTimeInMillis: Long = 0

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navTask -> {
                    startActivity(Intent(this, ListTasks::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navTodo -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navPomodoro -> {
                    startActivity(Intent(this, PomodoroActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navStatistics -> {
                    startActivity(Intent(this, PomodoroActivity::class.java))
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro)

        timerTextView = findViewById(R.id.timerTextView)
        startButton = findViewById(R.id.start)
        pauseButton = findViewById(R.id.pause)
        restartButton = findViewById(R.id.restart)

        startButton.setOnClickListener {
            if (!timerRunning) {
                startTimer()
            }
        }

        pauseButton.setOnClickListener {
            if (timerRunning) {
                pauseTimer()
            }
        }

        restartButton.setOnClickListener {
            restartTimer()
        }

        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                timerRunning = false
                updateTimerText()
            }
        }.start()

        timerRunning = true
        paused = false
        startButton.isEnabled = false
        pauseButton.isEnabled = true
        restartButton.isEnabled = true
    }

    private fun pauseTimer() {
        countDownTimer.cancel()
        timerRunning = false
        paused = true
        pausedTimeInMillis = timeLeftInMillis
        startButton.isEnabled = true
        pauseButton.isEnabled = false
        restartButton.isEnabled = true
    }

    private fun restartTimer() {
        if (!timerRunning) {
            timeLeftInMillis = 1500000
            pausedTimeInMillis = 0
            updateTimerText()
            startButton.isEnabled = true
            pauseButton.isEnabled = false
            restartButton.isEnabled = false
        }
    }

    private fun updateTimerText() {
        val minutes = (timeLeftInMillis / 1000 / 60).toInt()
        val seconds = (timeLeftInMillis / 1000 % 60).toInt()
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timerTextView.text = timeFormatted
    }

    override fun onStop() {
        super.onStop()
        if (timerRunning) {
            countDownTimer.cancel()
        }
    }
}
