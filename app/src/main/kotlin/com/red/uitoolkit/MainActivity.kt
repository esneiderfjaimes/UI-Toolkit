package com.red.uitoolkit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.red.uitoolkit.ui.DemoUIToolkitApp
import com.red.uitoolkit.ui.theme.UIToolkitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIToolkitTheme {
                DemoUIToolkitApp()
            }
        }
    }
}