package com.nnt.schedulemanagercompose.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.extension.toDateString
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.HorizontalSpacer
import com.nnt.schedulemanagercompose.ui.common.VerticalSpacer

/**
 * Created by ThangNNT on 02/12/2023.
 */

data class TaskData(
    val id: Int,
    val text: String,
    var isComplete: Boolean,
    val time: Long?,
    val enableNotification: Boolean
)

@Preview
@Composable
fun TaskItemViewPreview() {
    TaskItemView(data = TaskData(
        id = 1,
        text = "brush my teeth",
        isComplete = true,
        time = System.currentTimeMillis(),
        enableNotification = true
    ),
        onCheckedChange = { _, _ ->
        },
        onDelete = {},
        onEdit = {})
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItemView(
    data: TaskData,
    cardColor: Color = ColorProvider.appColors.cardBackground,
    onCheckedChange: (TaskData, Boolean) -> Unit,
    onDelete: (TaskData) -> Unit,
    onEdit: (TaskData) -> Unit
) {
    val swipeState = rememberSwipeableState(
        initialValue = false
    )
    val timeString = data.time?.toDateString("HH:mm:ss dd/MM/yyyy") ?: ""
    val anchors = mapOf(0f to false, 1f to true)
    val density = LocalDensity.current
    val boxWidth = remember {
        mutableStateOf(0.dp)
    }
    val checkIconRes = rememberUpdatedState(
        newValue =
        if (data.isComplete) R.drawable.ic_check_fill else R.drawable.ic_check_outline
    )
    val checkIconTint =
        rememberUpdatedState(newValue = if (data.isComplete) ColorProvider.appColors.completeTaskIconTint else ColorProvider.appColors.incompleteTaskIconTint)
    val offsetX by animateDpAsState(
        targetValue = if (swipeState.targetValue) {
            -boxWidth.value * swipeState.offset.value * 0.3f
        } else 0.dp
    )
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = cardColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .swipeable(
                    state = swipeState,
                    orientation = Orientation.Horizontal,
                    anchors = anchors,
                    reverseDirection = true
                )
                .onGloballyPositioned {
                    boxWidth.value = with(density) {
                        it.size.width.toDp()
                    }
                }
                .offset(x = offsetX)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(painter = painterResource(id = checkIconRes.value),
                contentDescription = "task is complete",
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onCheckedChange.invoke(data, data.isComplete.not())
                    }
                    .align(CenterVertically),
                colorFilter = ColorFilter.tint(checkIconTint.value)
            )
            HorizontalSpacer(width = 8.dp)
            Column(
                modifier = Modifier
                    .weight(1f, fill = true)
                    .align(CenterVertically)
            ) {
                Text(
                    text = data.text,
                    style = if (data.isComplete) {
                        MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.LineThrough)
                    } else MaterialTheme.typography.body1,
                    color = ColorProvider.appColors.textPrimary
                )
                VerticalSpacer(height = 4.dp)
                Row (verticalAlignment = CenterVertically){
                    Text(
                        text = timeString, style = if (data.isComplete) {
                            MaterialTheme.typography.body2.copy(textDecoration = TextDecoration.LineThrough)
                        } else MaterialTheme.typography.body2,
                        color = ColorProvider.appColors.textSecondary
                    )
                    if (data.enableNotification){
                        HorizontalSpacer(width = 8.dp)
                        Image(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentDescription = "ic notification",
                            modifier = Modifier.size(12.dp),
                            colorFilter = ColorFilter.tint(ColorProvider.appColors.textSecondary)
                        )
                    }
                }
            }
            HorizontalSpacer(width = 8.dp)
            // ensure layouts were calculated
            if (boxWidth.value>0.dp){
                Row(
                    modifier = Modifier
                        .offset(x = boxWidth.value * 0.3f)
                        .align(CenterVertically)
                ) {
                    ActionView(
                        label = stringResource(id = R.string.edit),
                        icon = R.drawable.ic_edit,
                        onClick = {
                            onEdit.invoke(data)
                        }
                    )
                    HorizontalSpacer(width = 16.dp)
                    ActionView(
                        label = stringResource(id = R.string.delete),
                        icon = R.drawable.ic_trash,
                        onClick = {
                            onDelete.invoke(data)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ActionView(
    label: String = "Delete",
    @DrawableRes icon: Int = R.drawable.ic_trash,
    onClick: () -> Unit = {}
) {
    Column(modifier = Modifier.clickable(interactionSource = remember {
        MutableInteractionSource()
    }, indication = null) {
        onClick.invoke()

    }, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "action icon"
        )
        Text(text = label, style = MaterialTheme.typography.caption)
    }
}