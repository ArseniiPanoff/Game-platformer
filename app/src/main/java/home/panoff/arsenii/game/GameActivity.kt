package home.panoff.arsenii.game

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.util.*
import android.graphics.Bitmap
import android.graphics.Bitmap.createScaledBitmap
import android.graphics.BitmapFactory
import android.text.style.BackgroundColorSpan
import java.io.*

@SuppressLint("ViewConstructor")
class Draw2D(context: Context, S:String) : View(context) {

    private val Str:String = S
    private val mPaint = Paint()
    private var xPath: Float = 0.0f
    private var yPath: Float = 0.0f
    private var mBitmap: Bitmap? = null
    private var mBitmap2: Bitmap? = null
    private var mBitmap1: Bitmap? = null
    private var mBitmap3: Bitmap? = null
    private var startTime : Long = 0
    private var i :Int = 0
    private var j :Int = 0
    private var helpToTime:Boolean = false
    private var cglvlpart:Int = 0
    private var k: Int = 0

    init {
        val res = this.resources
        this.mBitmap =  BitmapFactory.decodeResource(res, R.drawable.ice)
        this.mBitmap1 = BitmapFactory.decodeResource(res, R.drawable.genji)
        this.mBitmap2 = BitmapFactory.decodeResource(res, R.drawable.logo)
        this.mBitmap3 = BitmapFactory.decodeResource(res, R.drawable.mercy)

    }

    override fun performClick(): Boolean {

        return super.performClick()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        val eventAction = event.action
        if (eventAction == MotionEvent.ACTION_DOWN ) {
            this.xPath = event.x
            this.yPath = event.y
            cglvlpart++

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

        super.onDraw(canvas)
        startTime = System.currentTimeMillis()

        canvas.drawARGB(0, 225, 225, 255)
        mPaint.isAntiAlias = true
        mPaint.color = Color.WHITE
        mPaint.textSize = height.toFloat()/4f
        mPaint.strokeWidth = 2.0f
        mPaint.style = Paint.Style.STROKE
        mPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLUE)

    //    createScaledBitmap(mBitmap, width, height, true)
    //    if(k == 0)
    //        {canvas.drawBitmap(createScaledBitmap(mBitmap, width, height, true), 0f, 0f, mPaint)}
    //    else if(k == 1)
     //       {canvas.drawBitmap(createScaledBitmap(mBitmap1, width, height, true), 0f, 0f, mPaint)}

    //    else if(k == 2)
     //       {canvas.drawBitmap(createScaledBitmap(mBitmap2, width, height, true), 0f, 0f, mPaint)}
    //    else if(k == 3)
      //      {canvas.drawBitmap(createScaledBitmap(mBitmap3, width, height, true), 0f, 0f, mPaint)}

        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.RED
        if(helpToTime)
            cglvlpart++

        while(lvl.lvl1[j] != '.')
        {
        if(lvl.lvl1[j] == 'R')
            mPaint.color = Color.RED
        if(lvl.lvl1[j] == 'Y')
            mPaint.color = Color.YELLOW
        if(lvl.lvl1[j] == 'B')
            mPaint.color = Color.BLUE
        if(lvl.lvl1[j] == 'G')
            mPaint.color = Color.GREEN
        if(lvl.lvl1[j] == 'L')
                mPaint.color = Color.BLACK
        if(lvl.lvl1[j] == 'P')
             mPaint.color = Color.MAGENTA
        if(lvl.lvl1[j] != ' ')
            canvas.drawRect(i.toFloat()-15f*cglvlpart, height.toFloat()*9/10,i.toFloat()+150f-15f*cglvlpart,height.toFloat(),mPaint)
            j++
            i+=150
        }
        i = 0
        j = 0
        while(lvl.lvl2[j] != '.')
        {
            if(lvl.lvl2[j] == 'R')
                mPaint.color = Color.RED
            if(lvl.lvl2[j] == 'Y')
                mPaint.color = Color.YELLOW
            if(lvl.lvl2[j] == 'B')
                mPaint.color = Color.BLUE
            if(lvl.lvl2[j] == 'G')
                mPaint.color = Color.GREEN
            if(lvl.lvl2[j] == 'L')
                mPaint.color = Color.BLACK
            if(lvl.lvl2[j] == 'P')
                mPaint.color = Color.MAGENTA
            if(lvl.lvl2[j] != ' ')
                canvas.drawRect(i.toFloat()-15f*cglvlpart, height.toFloat()*4/10,i.toFloat()+150f-15f*cglvlpart,height.toFloat()*1/2,mPaint)
            j++
            i+=150
        }
        i = 0 ; j = 0
        canvas.drawText("START", width.toFloat()/3f-20f, height.toFloat()/2f, mPaint)
        canvas.drawText("$startTime", 0f, 330f, mPaint)
        mPaint.textSize = 60.0f
        canvas.drawText("$width   $height", 0f, 430f, mPaint)
        canvas.drawText("$xPath   $yPath",  0f, 130f, mPaint)
        canvas.drawText(Str,  30f, 460f, mPaint)
        canvas.drawText("$cglvlpart      $helpToTime",  30f, 560f, mPaint)
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
        Timer().schedule( TimerHandle(draw2D), 1000, 10 )
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
