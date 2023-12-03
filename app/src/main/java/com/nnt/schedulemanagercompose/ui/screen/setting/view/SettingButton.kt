package com.nnt.schedulemanagercompose.ui.screen.setting.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.HorizontalSpacer

/**
 * Created by ThangNNT on 03/12/2023.
 */

@Preview
@Composable
fun SettingButtonPreview(){
    SettingButton(
        text = "Sample",
        icon = R.drawable.ic_edit,
        onClick = {}
    )
}

@Composable
fun SettingButton(
    text: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) {
                onClick.invoke()
            },
        shape = RoundedCornerShape(8.dp),
        color = ColorProvider.appColors.cardBackground
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "setting icon"
            )
            HorizontalSpacer(width = 32.dp)
            Text(text = text)
        }
    }
}