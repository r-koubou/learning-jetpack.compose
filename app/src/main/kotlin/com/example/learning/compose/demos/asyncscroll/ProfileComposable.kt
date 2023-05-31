package com.example.learning.compose.demos.asyncscroll

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.learning.compose.ui.theme.Mint

@Composable
internal fun ProfileComposable(profile: ProfileModel)
{
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = Mint
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {

            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://picsum.photos/320")
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .diskCachePolicy(CachePolicy.DISABLED)
                    .build(),
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(12.dp)
            )

            Column {
                HeaderText(text = "Name")
                Text(text = profile.name)
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))

                HeaderText(text = "Description")
                Text(text = profile.description)
                Spacer(modifier = Modifier.fillMaxHeight(0.01f))

                HeaderText(text = "Web")
                Text(text = profile.webUrl)
            }

        }
    }
}

@Composable
private fun HeaderText(text: String, modifier: Modifier = Modifier, color : Color = Color.Black) {
    Text(
        text = text,
        style = textHeaderStyle(),
        modifier = modifier,
        color = color
    )
}

private fun textHeaderStyle() : TextStyle
{
    return TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}
