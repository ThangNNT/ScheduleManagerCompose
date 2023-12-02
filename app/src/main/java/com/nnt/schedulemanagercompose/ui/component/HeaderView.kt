package com.nnt.schedulemanagercompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.ui.common.HorizontalSpacer
import com.nnt.schedulemanagercompose.ui.theme.TextColorPrimary

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Preview
@Composable
fun HeaderViewPreview() {
    HeaderView(header = "Header", isExpand = true, onExpandChanged = {})
}

@Composable
fun HeaderView(header: String, isExpand: Boolean, onExpandChanged: (Boolean) -> Unit) {
    val icon = if (isExpand) {
        R.drawable.ic_arrow_down
    } else R.drawable.ic_arrow_up
    Row(
        modifier = Modifier.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            }, indication = null
        ) {
            onExpandChanged.invoke(isExpand.not())
        }, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = header, color = TextColorPrimary, style = MaterialTheme.typography.h4)
        HorizontalSpacer(height = 8.dp)
        Image(
            painter = painterResource(id = icon),
            contentDescription = "collapse or expand"
        )
    }
}