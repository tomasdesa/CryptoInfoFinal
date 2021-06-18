package ipvc.estg.cryptoinfofinal

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment

class tutorial : AppIntro(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make sure you don't call setContentView!


        // Call addSlide passing your Fragments.
        // You can use AppIntroFragment to use a pre-built fragment
        addSlide(AppIntroFragment.newInstance(
            title = getString(R.string.tutBemvindo),
            description = getString(R.string.tutdescBv),
            imageDrawable = R.drawable.finish,
            backgroundDrawable = R.drawable.back_slide3,
            titleColor = Color.WHITE,
            descriptionColor = Color.BLACK,
            backgroundColor = Color.BLUE
        ))
        addSlide(AppIntroFragment.newInstance(
            description = getString(R.string.tut2),
            imageDrawable = R.drawable.trans,
            backgroundDrawable = R.drawable.back_slide5,
            titleColor = Color.WHITE,
            descriptionColor = Color.BLACK,
            backgroundColor = Color.BLUE

        ))
        addSlide(AppIntroFragment.newInstance(
            description = getString(R.string.tut3),
            imageDrawable = R.drawable.grafmo,
            backgroundDrawable = R.drawable.back_slide4,
            titleColor = Color.BLUE,
            descriptionColor = Color.BLACK,
            backgroundColor = Color.BLUE

        ))
        addSlide(AppIntroFragment.newInstance(
            description = getString(R.string.tut4),
            imageDrawable = R.drawable.infocoin,
            backgroundDrawable = R.drawable.back_slide3,
            titleColor = Color.WHITE,
            descriptionColor = Color.BLACK,
            backgroundColor = Color.BLUE

        ))
        addSlide(AppIntroFragment.newInstance(
                title = getString(R.string.tut5),
            description = getString(R.string.tut5desc),
            imageDrawable = R.drawable.cryptocurrency,
            backgroundDrawable = R.drawable.back_slide2,
            titleColor = Color.WHITE,
            descriptionColor = Color.BLACK,
            backgroundColor = Color.BLUE

        ))
        addSlide(AppIntroFragment.newInstance(
                title = getString(R.string.tut6),
                description = getString(R.string.tutdescfinal),
                imageDrawable = R.drawable.logobitcoin,
                backgroundDrawable = R.drawable.back_slide1,
                titleColor = Color.WHITE,
                descriptionColor = Color.BLACK,
                backgroundColor = Color.BLUE

        ))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        val idUser = intent.getStringExtra("id_user")
        val username = intent.getStringExtra("username")
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, Moedas::class.java)
        intent.putExtra("id_user", idUser)
        intent.putExtra("username", username)
        startActivity(intent)

        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val idUser = intent.getStringExtra("id_user")
        val username = intent.getStringExtra("username")
        super.onSkipPressed(currentFragment)
        val intent = Intent(this, Moedas::class.java)
        intent.putExtra("id_user", idUser)
        intent.putExtra("username", username)
        startActivity(intent)
        finish()
    }

}