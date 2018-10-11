package home.panoff.arsenii.game

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView


class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.splash)
        val mp1: MediaPlayer = MediaPlayer.create(this, R.raw.blog)
        mp1.start()
        // Получим ссылку на солнце
        val sunImageView = findViewById<View>(R.id.sun) as ImageView
        // Анимация для восхода солнца
        val sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.sun_rise)
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation)

        val handler = Handler()
        handler.postDelayed( { startActivity(Intent(this@SplashActivity, MenuActivity::class.java));mp1.stop();mp1.release();finish() }, 1000)

    }
}
