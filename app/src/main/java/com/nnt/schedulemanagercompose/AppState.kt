package com.nnt.schedulemanagercompose

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import java.time.LocalDate

/**
 * Created by ThangNNT on 04/12/2023.
 */
object AppState {
    @OptIn(ExperimentalMaterialApi::class)
    val newEventSheetState = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    var eventSheetInitialDate: LocalDate? = null
}