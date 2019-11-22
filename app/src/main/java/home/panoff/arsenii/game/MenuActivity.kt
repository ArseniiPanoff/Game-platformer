package home.panoff.arsenii.game


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.menu.*
import android.content.SharedPreferences
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import kotlin.system.exitProcess



class MenuActivity : Activity() {
    private var MusicEnabled: Boolean = false
    var mediaPlayer: MediaPlayer? = null
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
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.menu)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        MusicEnabled = settings!!.getBoolean(APP_PREFERENCES_COUNTER, false)


        CommonMethod.soundPlayer(this, R.raw.space)
        CommonMethod.player!!.isLooping = true
        CommonMethod.player?.seekTo(bob.BOB)

        if( MusicEnabled ) {
         //   mp2.setVolume(1f, 1f)
            CommonMethod.player?.setVolume(1f,1f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)
        }
        else {
           // mp2.setVolume(0f, 0f)
            CommonMethod.player?.setVolume(0f,0f)
            sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
        }

        start.setOnClickListener  { val intent = Intent(this@MenuActivity, GameActivity::class.java)
            startActivity(intent);bob.BOB = CommonMethod.player!!.currentPosition; CommonMethod.player?.stop();finish()
        }
        help.setOnClickListener   { val intent = Intent(this@MenuActivity, HelpActivity::class.java)
            startActivity(intent) }
        about.setOnClickListener  { val intent = Intent(this@MenuActivity, AboutActivity::class.java)
            startActivity(intent) }
        scores.setOnClickListener { val intent = Intent(this@MenuActivity, ScoresActivity::class.java)
            startActivity(intent) }

        exit.setOnClickListener { finishAffinity();exitProcess(0) }

        sound.setOnClickListener {
            if( MusicEnabled ) {
                MusicEnabled = false
                CommonMethod.player?.setVolume(0f,0f)
                sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode)
            }
            else {
                MusicEnabled = true
                CommonMethod.player?.setVolume(1f,1f)
                sound.setBackgroundResource(android.R.drawable.ic_lock_silent_mode_off)

            }
            val editor = this.settings!!.edit()
            editor.putBoolean(APP_PREFERENCES_COUNTER, MusicEnabled)
            editor.apply()
        }

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
