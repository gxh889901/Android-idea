package com.example.test.test.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.test.R
import kotlinx.android.synthetic.main.content_view.*

class LambdaActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_view)
        demoContent.text = "代码块作为函数参数"
    }

}