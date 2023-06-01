package com.example.learning.compose.demos.localization

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.learning.compose.NaviScreen
import com.example.learning.compose.R

class LocalizationDemoScreen : NaviScreen
{
    @Composable
    override fun Screen()
    {
        // OS の R リソースを使うことで、言語切り替えに対応する
        // https://phrase.com/blog/posts/internationalizing-jetpack-compose-android-apps/
        // -> res/values/string.xml
        // -> res/values-ja/string.xml
        Text(stringResource(R.string.hello))
    }
}

@Preview(name = "LocalizationDemoScreen", showBackground = true, showSystemUi = true)
@Composable
private fun Preview_LocalizationDemoScreen() {
    LocalizationDemoScreen().Screen()
}
