package com.nnt.schedulemanagercompose.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Composable
fun VerticalSpacer(width: Dp){
    Spacer(modifier = Modifier.height(width))
}

@Composable
fun HorizontalSpacer(height: Dp){
    Spacer(modifier = Modifier.width(height))
}