package home.panoff.arsenii.game

import android.R.attr.bitmap
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.*
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.Color.BLACK
import android.graphics.Color.RED
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*


class Draw2D(context: Context, S:String) : View(context) {

    private val Str:String = S
    private val mPaint = Paint()
    private var xPath: Float = 0.0f
    private var yPath: Float = 0.0f
    private var mBitmap: Bitmap? = null
    private var mBitmap2: Bitmap? = null
    private var mBitmap1: Bitmap? = null
    private var mBitmap3: Bitmap? = null
    private var mBitmap4: Bitmap? = null
    private var startTime : Long = 0
    private var i :Int = 0
    private var j :Int = 0
    private var helpToTime:Boolean = false
    private var cglvlpart:Int = 0
    private var k: Int = 0

    init {
        val res = this.resources
        this.mBitmap =  BitmapFactory.decodeResource(res, R.drawable.ggm_1)
        this.mBitmap1 = BitmapFactory.decodeResource(res, R.drawable.ggm_2)
        this.mBitmap2 = BitmapFactory.decodeResource(res, R.drawable.ggm_3)
        this.mBitmap3 = BitmapFactory.decodeResource(res, R.drawable.ggm_4)
        this.mBitmap4 = BitmapFactory.decodeResource(res, R.drawable.gg_2)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        val eventAction = event.action
        if (eventAction == MotionEvent.ACTION_DOWN ) {
            this.xPath = event.x
            this.yPath = event.y
           // cglvlpart++

            k++
            if(k > 3)
                k = 0
            helpToTime = true
            return true
        }
        if (eventAction == MotionEvent.ACTION_UP) {
            helpToTime = false
           this.invalidate()
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
     //   mBitmap4=createScaledBitmap(mBitmap4, 800/6, 2065/6, true)
        super.onDraw(canvas)
        startTime = System.currentTimeMillis()

        mPaint.isAntiAlias = true
        mPaint.color = Color.WHITE
        mPaint.textSize = height.toFloat()/4f
        mPaint.strokeWidth = 2.0f
        mPaint.style = Paint.Style.STROKE
        mPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLUE)

        mPaint.style = Paint.Style.FILL
        mPaint.color = RED
        if(helpToTime && (lvl.lvl1.length*150-width)-150 >= 15*cglvlpart)
            cglvlpart++
        while (lvl.lvl1[j] != '.') {
                if (lvl.lvl1[j] == 'R')
                    mPaint.color = RED
                if (lvl.lvl1[j] == 'Y')
                    mPaint.color = Color.YELLOW
                if (lvl.lvl1[j] == 'B')
                    mPaint.color = Color.BLUE
                if (lvl.lvl1[j] == 'G')
                    mPaint.color = Color.GREEN
                if (lvl.lvl1[j] == 'L')
                    mPaint.color = Color.BLACK
                if (lvl.lvl1[j] == 'P')
                    mPaint.color = Color.MAGENTA
                if (lvl.lvl1[j] != ' ')
                    canvas.drawRect(i.toFloat() - 15f * cglvlpart, height.toFloat() * 9 / 10, i.toFloat() + 150f - 15f * cglvlpart, height.toFloat(), mPaint)
                j++
                i += 150
            }
            i = 0
            j = 0
            while (lvl.lvl2[j] != '.') {
                if (lvl.lvl2[j] == 'R')
                    mPaint.color = RED
                if (lvl.lvl2[j] == 'Y')
                    mPaint.color = Color.YELLOW
                if (lvl.lvl2[j] == 'B')
                    mPaint.color = Color.BLUE
                if (lvl.lvl2[j] == 'G')
                    mPaint.color = Color.GREEN
                if (lvl.lvl2[j] == 'L')
                    mPaint.color = Color.BLACK
                if (lvl.lvl2[j] == 'P')
                    mPaint.color = Color.MAGENTA
                if (lvl.lvl2[j] != ' ')
                    canvas.drawRect(i.toFloat() - 15f * cglvlpart, height.toFloat() * 4 / 10, i.toFloat() + 150f - 15f * cglvlpart, height.toFloat() * 1 / 2, mPaint)
                j++
                i += 150
            }
            i = 0; j = 0

      //  canvas.drawBitmap(mBitmap4,0f,height*6/10f,mPaint)

        canvas.drawText("START", width.toFloat()/3f-20f, height.toFloat()/2f, mPaint)
        canvas.drawText("$startTime", 0f, 330f, mPaint)
        mPaint.textSize = 60.0f
        canvas.drawText("$width   $height", 0f, 430f, mPaint)
        canvas.drawText("$xPath   $yPath",  0f, 130f, mPaint)
        canvas.drawText(Str,  30f, 460f, mPaint)
        canvas.drawText("$cglvlpart      $helpToTime",  30f, 560f, mPaint)
        mPaint.setShadowLayer(0f,0f,0f,0)

        if(helpToTime && startTime/100 % 3 == 0L)
            canvas.drawBitmap((createScaledBitmap(mBitmap, 800/6, 2065/6, true)), 0f,height*6/10f, mPaint)
        else if(helpToTime && startTime/100 % 3 == 1L)
            canvas.drawBitmap((createScaledBitmap(mBitmap1, 800/6, 2065/6, true)), 0f,height*6/10f, mPaint)
        else if(helpToTime && startTime/100 % 3 == 2L)
            canvas.drawBitmap((createScaledBitmap(mBitmap4, 800/6, 2065/6, true)), 0f,height*6/10f, mPaint)
        else
            canvas.drawBitmap((createScaledBitmap(mBitmap4, 800/6, 2065/6, true)), 0f,height*6/10f, mPaint)
    }

    fun onTimer()
    {
        postInvalidate()
    }
}

class TimerHandle( view1 : Draw2D ) : TimerTask() {
    private var view = view1
    override fun run()
    {
        //view.invalidate()
        view.onTimer()
    }
}
class GameActivity : Activity() {

    override fun onBackPressed() {
            super.onBackPressed();val intent = Intent(this@GameActivity, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        fun readRawTextFile( ctx:Context,  resId:Int):String
        {
            val inputStream:InputStream = ctx.resources.openRawResource(resId)

            val byteArrayOutputStream =  ByteArrayOutputStream()

            var i: Int

            i = inputStream.read()
            while (i != -1)
            {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
            inputStream.close()

            return byteArrayOutputStream.toString()
        }
        val s:String = readRawTextFile(this, R.raw.textnew)

        val draw2D = Draw2D(this, s )
        setContentView(draw2D)
        Timer().schedule( TimerHandle(draw2D), 1000, 20 )
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
