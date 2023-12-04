package com.nnt.schedulemanagercompose.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nnt.schedulemanagercompose.AppState
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.VerticalSpacer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * Created by ThangNNT on 04/12/2023.
 */
@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun NewEventButtonSheetPreview() {
    NewEventButtonSheet(
        state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded),
        {},
        {},
        {})
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewEventButtonSheet(
    state: ModalBottomSheetState,
    onSelectNewTask: () -> Unit,
    onSelectAnniversary: () -> Unit,
    onSelectCountDays: () -> Unit
) {
    val  coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(sheetState = state,
        sheetShape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        sheetBackgroundColor = ColorProvider.appColors.windowBackground,
        sheetContent = {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
                Text(
                    text = stringResource(id = R.string.choose_your_event_type),
                    style = MaterialTheme.typography.h5,
                    color = ColorProvider.appColors.textSecondary
                )
                VerticalSpacer(height = 16.dp)
                SelectionLine(text = stringResource(id = R.string.new_task)){
                    coroutineScope.launch {
                        state.hide()
                    }
                    onSelectNewTask.invoke()
                }
                SelectionLine(text = stringResource(id = R.string.anniversary)){
                    coroutineScope.launch {
                        state.hide()
                    }
                    onSelectAnniversary.invoke()
                }
                SelectionLine(text = stringResource(id = R.string.count_the_days)){
                    coroutineScope.launch {
                        state.hide()
                    }
                    onSelectCountDays.invoke()
                }
                VerticalSpacer(height = 100.dp)
            }
        }) {}
}

@Preview
@Composable
private fun SelectionLinePreview() {
    SelectionLine(text = "Selection line") {
    }
}
@Composable
private fun SelectionLine(text: String, onClick: () -> Unit){
    Column(modifier = Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() }, indication = null
    ) {
        onClick.invoke()
    }) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            color = ColorProvider.appColors.textPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 16.dp)
        )
        Divider(modifier = Modifier.padding(horizontal = 8.dp))
    }
}

/**
 * only call this function in Composition
 */
@OptIn(ExperimentalMaterialApi::class)
fun CoroutineScope.showNewEventButtonSheet(initialDate: LocalDate) {
    this.launch {
        AppState.eventSheetInitialDate = initialDate
        AppState.newEventSheetState.show()
    }
}
