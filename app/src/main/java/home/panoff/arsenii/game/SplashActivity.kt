package home.panoff.arsenii.game

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils



class SplashActivity : Activity() {

    private object CommonMethod {
        var player: MediaPlayer? = null
        fun soundPlayer(ctx: Context, raw_id: Int) {
            player = MediaPlayer.create(ctx, raw_id)
            player!!.isLooping = false // Set looping
            player!!.setVolume(100f, 100f)

            //player.release();
            player!!.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContentView(R.layout.splash)
        CommonMethod.soundPlayer(this, R.raw.blog)

        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, MenuActivity::class.java));CommonMethod.player?.release();finish()
        }, 1500)
    }

    override fun onResume() {
        super.onResume()
        // Получим ссылку на солнце
        val sunImageView = findViewById<View>(R.id.zerg_logo)
        // Анимация для восхода солнца
        val sunRiseAnimation = AnimationUtils.loadAnimation(this, R.anim.zerg_rise)
        // Подключаем анимацию к нужному View
        sunImageView.startAnimation(sunRiseAnimation)
    }

    override fun onDestroy() {
        CommonMethod.player?.release()
        super.onDestroy()
    }
    override fun onStart() {
        super.onStart()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
    override fun onRestart() {
        super.onRestart()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}

