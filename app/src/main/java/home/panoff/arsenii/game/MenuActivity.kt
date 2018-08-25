package home.panoff.arsenii.game


import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.menu.*
import android.content.SharedPreferences



class MenuActivity : BaseActivity() {
    private var Counter: Int = 0

    // имя файла настройки
    val APP_PREFERENCES = "settings"
    val APP_PREFERENCES_COUNTER = "fouder"
    private var settings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)



        this.settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        this.Counter = this.settings!!.getInt(APP_PREFERENCES_COUNTER, 0)

        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        val mp2: MediaPlayer = MediaPlayer.create(this, R.raw.space)
        mp2.start()
        mp2.isLooping = true
        
        
        if(this.Counter==0) {
            mp2.setVolume(0f, 0f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
            this.Counter = 1
        }
        else {this.Counter=0
            mp2.setVolume(1f, 1f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)
        }

        start.setOnClickListener { startActivity(Intent(this@MenuActivity, GameActivity::class.java)) }
        help.setOnClickListener { startActivity(Intent(this@MenuActivity, HelpActivity::class.java)) }
        about.setOnClickListener { startActivity(Intent(this@MenuActivity, AboutActivity::class.java)) }
        scores.setOnClickListener { startActivity(Intent(this@MenuActivity, ScoresActivity::class.java)) }
        exit.setOnClickListener { finishAffinity();System.exit(0)}

        sound.setOnClickListener {if(this.Counter==0) {
            this.Counter = 1
            mp2.setVolume(0f, 0f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
                                            }
                                  else{
            this.Counter = 0
            mp2.setVolume(1f, 1f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)

        }
                                    }

        val spaceImageView = findViewById<View>(R.id.ice) as ImageView
        // Анимация для восхода солнца
        val menuSplashAnimation = AnimationUtils.loadAnimation(this, R.anim.menu_splash)
        // Подключаем анимацию к нужному View
        spaceImageView.startAnimation(menuSplashAnimation)

        val fogImageView = findViewById<View>(R.id.fog) as ImageView
        // Анимация для восхода солнца
        val fogSplashAnimation = AnimationUtils.loadAnimation(this, R.anim.fog_anim)
        // Подключаем анимацию к нужному View
        fogImageView.startAnimation(fogSplashAnimation)
    }

    override fun onResume() {
        super.onResume()

        if (this.settings!!.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            this.Counter = this.settings!!.getInt(APP_PREFERENCES_COUNTER, 0)
        }
    }

    override fun onStop() {
        super.onStop()
        // Запоминаем данные
        val editor = this.settings!!.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, this.Counter)
        editor.apply()
    }
}
