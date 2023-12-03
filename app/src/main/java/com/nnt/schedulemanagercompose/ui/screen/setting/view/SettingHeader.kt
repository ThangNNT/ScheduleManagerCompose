package com.nnt.schedulemanagercompose.ui.screen.setting.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nnt.schedulemanagercompose.provider.ColorProvider

/**
 * Created by ThangNNT on 03/12/2023.
 */
@Preview
@Composable
fun SettingHeader(text: String = " header"){
    Text(
        text = "Header",
        style = MaterialTheme.typography.h5,
        color = ColorProvider.appColors.textPrimary
    )
}