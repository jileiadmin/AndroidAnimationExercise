package home.smart.fly.animations.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import home.smart.fly.animations.R
import home.smart.fly.animations.utils.StatusBarUtil
import kotlinx.android.synthetic.main.include_view.*

class MergeIncludeViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.setColor(this,ContextCompat.getColor(this,R.color.colorPrimary))
        setContentView(R.layout.activity_merge_include_view)

        back.setOnClickListener { finish() }
    }
}
