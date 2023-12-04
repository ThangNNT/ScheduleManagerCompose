package com.nnt.schedulemanagercompose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.R

/**
 * Created by ThangNNT on 04/12/2023.
 */
@Preview
@Composable
fun AddButtonPreview(){
    AddButton {

    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    Image(painter = painterResource(id = R.drawable.ic_fab), contentDescription = "add button",
        modifier = Modifier
            .clickable(interactionSource = remember {
                MutableInteractionSource()
            }, indication = null) {
                onClick.invoke()
            }
            .size(40.dp)
    )
}