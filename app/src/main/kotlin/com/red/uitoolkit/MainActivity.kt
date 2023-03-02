package com.red.uitoolkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.red.uitoolkit.ui.DemoP1
import com.red.uitoolkit.ui.theme.UIToolkitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIToolkitTheme {
                Surface {
                    DemoP1()
                }
            }
        }
    }
}