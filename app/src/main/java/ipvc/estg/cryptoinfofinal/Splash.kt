package ipvc.estg.cryptoinfofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import ipvc.estg.cryptoinfofinal.Login
import ipvc.estg.cryptoinfofinal.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splash =findViewById<LottieAnimationView>(R.id.splash1)


        val textview = findViewById<TextView>(R.id.textView4)

        splash.setMaxFrame(300)
        splash.playAnimation()


        splash.animate().translationY((-1600).toFloat()).setDuration(1000).setStartDelay(4000)


        textview.animate().translationY((-1600).toFloat()).setDuration(1000).setStartDelay(4000)

        Handler().postDelayed({
            val i = Intent(this, Login::class.java)
            startActivity(i)
            finish()
        }, 5000)
    }

}