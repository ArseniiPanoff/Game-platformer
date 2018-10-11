package home.panoff.arsenii.game


import android.app.Activity
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
import android.view.Window
import android.view.WindowManager
import android.widget.Toast




class MenuActivity : Activity() {
    private var MusicEnabled: Boolean = false

    // имя файла настройки
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COUNTER = "fouder"

    private var settings: SharedPreferences? = null

    private var back_pressed: Long = 0

    override fun onBackPressed() {
        if (this.back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();finishAffinity();System.exit(0)}
        else
            Toast.makeText(baseContext, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show()
        this.back_pressed = System.currentTimeMillis()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.menu)



        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        MusicEnabled = settings!!.getBoolean(APP_PREFERENCES_COUNTER, false)

        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        val mp2: MediaPlayer = MediaPlayer.create(this, R.raw.space)
        mp2.start()
        mp2.isLooping = true

        
        if( MusicEnabled ) {
            mp2.setVolume(1f, 1f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)
        }
        else {
            mp2.setVolume(0f, 0f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
        }

        start.setOnClickListener  { val intent = Intent(this@MenuActivity, GameActivity::class.java)
            startActivity(intent)
            }
        help.setOnClickListener   { val intent = Intent(this@MenuActivity, HelpActivity::class.java)
            startActivity(intent) }
        about.setOnClickListener  { val intent = Intent(this@MenuActivity, AboutActivity::class.java)
            startActivity(intent) }
        scores.setOnClickListener { val intent = Intent(this@MenuActivity, ScoresActivity::class.java)
            startActivity(intent) }
        exit.setOnClickListener { finishAffinity();System.exit(0)}

        sound.setOnClickListener {
            if( MusicEnabled ) {
                MusicEnabled = false
                mp2.setVolume(0f, 0f)
                sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
            }
            else {
                MusicEnabled = true
                mp2.setVolume(1f, 1f)
                sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)

            }
            val editor = this.settings!!.edit()
            editor.putBoolean(APP_PREFERENCES_COUNTER, MusicEnabled)
            editor.apply()
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
            MusicEnabled = this.settings!!.getBoolean(APP_PREFERENCES_COUNTER, false)
        }
    }

    override fun onStop() {

        // Запоминаем данные
        val editor = this.settings!!.edit()
        editor.putBoolean(APP_PREFERENCES_COUNTER, MusicEnabled)
        editor.apply()

        super.onStop()
    }
}
