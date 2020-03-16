package home.smart.fly.animations.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import home.smart.fly.animations.R
import home.smart.fly.animations.widget.dp2px
import kotlinx.android.synthetic.main.activity_prepare.*


const val TAG_1 = "PrepareActivity"

class PrepareActivity : AppCompatActivity() {
    private val url = "http://pic.vjshi.com/2018-04-11/78243894ece50c9b1ad6fa6c211a1bdb/00003.jpg?x-oss-process=style/watermark"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare)
        Glide.with(this).load(url).into(webImage)

        name.maxWidth = 0
        name1.maxWidth = 20

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val result = s.toString()
                content.text = result
                content1.text = result
                content2.text = result
                content2.invalidate()
            }

        })

        over_layout_1.layoutDirection = LinearLayout.LAYOUT_DIRECTION_RTL
        over_layout_2.layoutDirection = LinearLayout.LAYOUT_DIRECTION_LTR

        for (i in 0..10) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_image, null)
            val imageView = view.findViewById<CircleImageView>(R.id.image)
            imageView.setImageResource(R.drawable.cat)
            over_layout_3.addView(view)
        }

        scroll_to.setOnClickListener {
            nested_scrollview.scrollTo(0,600)
        }

        scroll_by.setOnClickListener {
            nested_scrollview.scrollBy(0,300)
        }

    }
}
