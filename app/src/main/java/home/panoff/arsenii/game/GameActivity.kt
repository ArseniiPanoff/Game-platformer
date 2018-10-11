package home.panoff.arsenii.game

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
import android.graphics.BitmapFactory


class Piece( x1 : Float, y1 : Float )
{
    var x: Float = x1
    var y: Float = y1
}

class Cube()
{

}
class SnakeS {
    var pieceSideSize : Float = 30f
    var body : Vector<Piece> = Vector()

    var deltaX : Float = 0f
    var deltaY : Float = 0f

    var fieldWidth : Float = 0f
    var fieldHeight : Float = 0f

    fun makeHead()
    {
        var newX = body[0].x + deltaX
        var newY = body[0].y + deltaY
        if( newX * pieceSideSize >= fieldWidth - (fieldWidth % pieceSideSize))
            newX = 0f
        if( newX < 0f )
            newX = ( (fieldWidth - (fieldWidth % pieceSideSize))/ pieceSideSize ) -1f

        if( newY * pieceSideSize >= fieldHeight - (fieldHeight % pieceSideSize))
            newY = 0f
        if( newY < 0f )
            newY = ( (fieldHeight - (fieldHeight % pieceSideSize))/ pieceSideSize ) -1f

        body.insertElementAt( Piece( newX, newY ), 0 )
    }

    fun eat()
    {
        makeHead()
    }

    fun start( w : Float, h : Float )
    {
        fieldWidth = w
        fieldHeight = h
        body.addElement( Piece ( 0f, 0f ) )
    }

    fun move()
    {
        makeHead()
        body.removeElement( body.lastElement() )
    }
}

fun scaleDown(realImage: Bitmap, maxImageSize: Float,
              filter: Boolean): Bitmap {
    val ratio = Math.min(
            maxImageSize / realImage.width,
            maxImageSize / realImage.height)
    val width = Math.round(ratio * realImage.width)
    val height = Math.round(ratio * realImage.height)

    return Bitmap.createScaledBitmap(realImage, width,
            height, filter)
}

class Draw2D(context: Context) : View(context) {


    private val mPaint = Paint()
    private var xPath: Float = 0.0f
    private var yPath: Float = 0.0f
    private var mBitmap: Bitmap? = null
    private var mBitmap2: Bitmap? = null
    private var mBitmap1: Bitmap? = null
    private var mBitmap3: Bitmap? = null

    var startTime : Long = 0
    private var W :Int = 0
    private var H :Int = 0
    // Создание нового объекта изображения


    private var k: Int = 0

    var snake : SnakeS = SnakeS()

    init {
        val res = this.resources
        this.mBitmap = BitmapFactory.decodeResource(res, R.drawable.ice)
        this.mBitmap1 = BitmapFactory.decodeResource(res, R.drawable.genji)
        this.mBitmap2 = BitmapFactory.decodeResource(res, R.drawable.logo)
        this.mBitmap3 = BitmapFactory.decodeResource(res, R.drawable.mercy)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

        snake.start(w.toFloat(),h.toFloat())
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val w: Float = width.toFloat()
            val h: Float = height.toFloat()
            this.xPath = event.x
            this.yPath = event.y

            if(this.xPath > 3f/4f*w && this.yPath < 3f/4f*h && this.yPath > h*1f/4f && snake.deltaX != -1f) {
                snake.deltaX = 1f;snake.deltaY = 0f }
            if(this.yPath > 3f/4f*h && this.xPath > w*1f/4f && this.xPath < w*3f/4f && snake.deltaY != -1f){
                snake.deltaY = 1f;snake.deltaX = 0f}
            if(this.xPath < 1f/4f*w && this.yPath < 3f/4f*h && this.yPath > h*1f/4f && snake.deltaX != 1f ){
                snake.deltaX = -1f;snake.deltaY = 0f}
            if(this.yPath < 1f/4f*h && this.xPath < w*3f/4f && this.xPath > w*1f/4f && snake.deltaY != 1f){
                snake.deltaY = -1f;snake.deltaX = 0f}

           // if(this.xPath < 3f/4f*w && this.yPath < 3f/4f*h && this.yPath > h*1f/4f && this.xPath > 1f/4f*w)
            k++
            if(k > 3)
                k=0
            this.invalidate()
        }
        return super.onTouchEvent(event)
    }



    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        startTime = System.currentTimeMillis()


        mPaint.color = Color.BLUE
        //canvas.drawLine(0f,snake.fieldHeight - (snake.fieldHeight % snake.pieceSideSize), snake.fieldWidth - (snake.fieldWidth % snake.pieceSideSize), snake.fieldHeight - (snake.fieldHeight % snake.pieceSideSize), mPaint)
        //canvas.drawLine(snake.fieldWidth - (snake.fieldWidth % snake.pieceSideSize),0f, snake.fieldWidth - (snake.fieldWidth % snake.pieceSideSize), snake.fieldHeight - (snake.fieldHeight % snake.pieceSideSize), mPaint)
        mPaint.color = Color.RED
        /*for( el in snake.body ) {
            canvas.drawRect( el.x * snake.pieceSideSize, el.y * snake.pieceSideSize,
                    (el.x + 1 )*snake.pieceSideSize, (el.y + 1 )*snake.pieceSideSize, mPaint )
        }*/
        mPaint.isAntiAlias = true
        mPaint.color = Color.WHITE
        mPaint.textSize = height.toFloat()/4f
        mPaint.strokeWidth = 2.0f
        mPaint.style = Paint.Style.STROKE
        mPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLUE)

        canvas.drawText("START", width.toFloat()/3f-20f, height.toFloat()/2f, mPaint)
        canvas.drawText("$startTime", 0f, 330f, mPaint)
        mPaint.textSize = 60.0f
        //Bitmap.createScaledBitmap(mBitmap, width.toInt(), height.toInt(), true)
        if(k == 0)
            {canvas.drawBitmap(Bitmap.createScaledBitmap(mBitmap, width.toInt(), height.toInt(), true), 0f, 0f, mPaint)}
        else if(k == 1)
            {canvas.drawBitmap(Bitmap.createScaledBitmap(mBitmap1, width.toInt(), height.toInt(), true), 0f, 0f, mPaint)}

        else if(k == 2)
            {canvas.drawBitmap(Bitmap.createScaledBitmap(mBitmap2, width.toInt(), height.toInt(), true), 0f, 0f, mPaint)}
        else if(k == 3)
            {canvas.drawBitmap(Bitmap.createScaledBitmap(mBitmap3, width.toInt(), height.toInt(), true), 0f, 0f, mPaint)}


        canvas.drawText("$width   $height", 0f, 430f, mPaint)
        canvas.drawText("$xPath   $yPath",  0f, 130f, mPaint)



    }

    fun onTimer()
    {
        /*if(k==1f) {
            snake.eat()
            k=0f
        }
        else
            snake.move()*/
        postInvalidate()
    }

}

class TimerHandle( view1 : Draw2D ) : TimerTask() {
    var view = view1
    override fun run()
    {
        //view.invalidate()
        view.onTimer()
    }
}
class GameActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        val draw2D = Draw2D(this )
        setContentView(draw2D)
        Timer().schedule( TimerHandle(draw2D), 1000, 200 )

    }
}
