package home.panoff.arsenii.game

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.widget.TextView
import android.content.Context.MODE_PRIVATE
import android.view.View


class ScoresActivity : BaseActivity() {

    private var mCounter: Int = 0
    private var mInfoTextView: TextView? = null

    // имя файла настройки
    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_COUNTER = "counter"
    private var mSettings: SharedPreferences? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.scores)

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        mInfoTextView = findViewById<View>(R.id.textViewInfo) as TextView?
    }

    fun onClick(v: View) {
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

    override fun onPause() {
        super.onPause()
        // Запоминаем данные
        val editor = mSettings!!.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, mCounter)
        editor.apply()
    }
}
