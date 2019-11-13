package home.panoff.arsenii.game

import android.os.Bundle
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.view.View


class ScoresActivity : Activity() {

    private var mCounter: Int = 0
    private var mInfoTextView: TextView? = null

    // имя файла настройки
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COUNTER = "counter"
    private var mSettings: SharedPreferences? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.scores)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        mInfoTextView = findViewById<View>(R.id.textViewInfo) as TextView?
    }

    @SuppressLint("SetTextI18n")
    fun onClick(v:View) {
        // Выводим на экран
        mInfoTextView!!.text = "Я насчитал " + ++mCounter + " ворон"
    }

    override fun onResume() {
        super.onResume()

        if (mSettings!!.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            mCounter = mSettings!!.getInt(APP_PREFERENCES_COUNTER, 0)
            // Выводим на экран данные из настроек
            mInfoTextView!!.text = ("Я насчитал "
                    + mCounter + " ворон")
        }
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
    override fun onPause() {
        super.onPause()
        // Запоминаем данные
        val editor = mSettings!!.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, mCounter)
        editor.apply()
    }
}
