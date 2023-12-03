package com.nnt.schedulemanagercompose.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.nnt.schedulemanagercompose.AppConstant
import com.nnt.schedulemanagercompose.R
import com.nnt.schedulemanagercompose.extension.dpToPx
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.HorizontalSpacer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

/**
 * Created by ThangNNT on 03/12/2023.
 */

@Composable
fun Day(day: CalendarDay, isSelected: Boolean, onSelected: (CalendarDay) -> Unit) {
    val textColor =
        rememberUpdatedState(
            newValue = if (isSelected) ColorProvider.appColors.selectedDateText
            else if (day.position == DayPosition.MonthDate) Color.Black else Color.LightGray
        )
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, indication = null
            ) {
                if (day.position == DayPosition.MonthDate) {
                    onSelected.invoke(day)
                }
            },
        contentAlignment = Center
    ) {
        // display selected date mark
        if (isSelected){
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = ColorProvider.appColors.selectDateColor, shape = CircleShape
                    )
                    .fillMaxSize()
            )
        }
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor.value,
            style = MaterialTheme.typography.body1
        )
        // display current date mark
        if (day.date == LocalDate.now()){
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp)
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(color = Color.Red)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                color = ColorProvider.appColors.textPrimary
            )
        }
    }
}
@Composable
fun MonthYearView(
    yearMonth: YearMonth,
    onArrowLeftClick: (YearMonth) -> Unit,
    onArrowRightClick: (YearMonth) -> Unit,
    onYearMonthChanged: (YearMonth) -> Unit,
    onRefresh: ()-> Unit
) {
    val expandedMonth = remember {
        mutableStateOf(false)
    }
    val expandedYear = remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
         MutableInteractionSource()
    }
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp), contentAlignment = Center){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "arrow left",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(interactionSource = interactionSource, null) {
                        onArrowLeftClick.invoke(yearMonth)
                    }
            )
            HorizontalSpacer(width = 8.dp)
            Column {
                Text(
                    text = yearMonth.month.getDisplayName(
                        TextStyle.FULL_STANDALONE,
                        Locale.getDefault()
                    ),
                    color = ColorProvider.appColors.textPrimary,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        expandedMonth.value = true
                    }
                )
                MonthSelectionPopup(selectedMonth = yearMonth.month, isExpand = expandedMonth, onMonthSelected = {
                    onYearMonthChanged.invoke(YearMonth.of(yearMonth.year, it))
                })
            }
            HorizontalSpacer(width = 16.dp)
            Column {
                Text(text = "${yearMonth.year}",
                    color = ColorProvider.appColors.textPrimary,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        expandedYear.value = true
                    })
                YearSelectionPopup(selectedYear = yearMonth.year, isExpand = expandedYear, onYearSelected = {
                    onYearMonthChanged.invoke(
                        YearMonth.of(
                            it, yearMonth.month
                        )
                    )
                })
            }
            HorizontalSpacer(width = 8.dp)
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "arrow left",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable(
                        interactionSource = interactionSource, indication = null
                    ) {
                        onArrowRightClick.invoke(yearMonth)
                    }
            )
        }
        if (yearMonth != YearMonth.now()){
            Image(
                painter = painterResource(id = R.drawable.ic_refresh), contentDescription = "refresh",
                modifier = Modifier
                    .align(CenterEnd)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onRefresh.invoke()
                    }
            )
        }
    }
}

@Composable
fun MonthSelectionPopup(
    selectedMonth: Month,
    isExpand: MutableState<Boolean>,
    onMonthSelected: (Month) -> Unit
) {
    if (isExpand.value){
        Popup(
            onDismissRequest = { isExpand.value = false },
            offset = IntOffset(x = 0, y = 24.dp.dpToPx().toInt())
        ) {
            val scrollState = rememberLazyListState()
            LaunchedEffect(key1 = isExpand.value, block = {
                if (isExpand.value){
                    scrollState.scrollToItem(selectedMonth.ordinal)
                }
            })
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                elevation = 16.dp
            ) {
                LazyColumn(
                    modifier = Modifier
                        .requiredHeightIn(max = 300.dp)
                        .width(200.dp)
                        .padding(vertical = 16.dp),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(Month.values().size) {
                        val month = Month.values()[it]
                        val isSelected = month == selectedMonth
                        Text(
                            text = month.getDisplayName(
                                TextStyle.FULL_STANDALONE,
                                Locale.getDefault()
                            ),
                            style = MaterialTheme.typography.body1,
                            color = if (isSelected) ColorProvider.appColors.selectedDateText else ColorProvider.appColors.textPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = if (isSelected) ColorProvider.appColors.selectDateColor else Color.Transparent)
                                .padding(
                                    horizontal = 16.dp, vertical = 8.dp
                                )
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    isExpand.value = false
                                    onMonthSelected.invoke(month)
                                }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun YearSelectionPopup(
    selectedYear: Int,
    isExpand: MutableState<Boolean>,
    onYearSelected: (Int) -> Unit
){
    if (isExpand.value) {
        Popup(
            onDismissRequest = { isExpand.value = false },
            offset = IntOffset(x = 0, y = 24.dp.dpToPx().toInt())
        ) {
            val scrollState = rememberLazyListState()
            LaunchedEffect(key1 = isExpand.value, block = {
                if (isExpand.value){
                    scrollState.scrollToItem(selectedYear-AppConstant.CALENDAR_MIN_YEAR)
                }
            })
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                elevation = 16.dp
            ) {
                LazyColumn(
                    modifier = Modifier
                        .requiredHeightIn(max = 300.dp)
                        .width(100.dp)
                        .padding(vertical = 16.dp),
                    state = scrollState,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(AppConstant.CALENDAR_MAX_YEAR - AppConstant.CALENDAR_MIN_YEAR+1) {
                        val year = it + AppConstant.CALENDAR_MIN_YEAR
                        val isSelected = year == selectedYear
                        Text(
                            text = "$year",
                            style = MaterialTheme.typography.body1,
                            color = if (isSelected) ColorProvider.appColors.selectedDateText else ColorProvider.appColors.textPrimary,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = if (isSelected) ColorProvider.appColors.selectDateColor else Color.Transparent)
                                .padding(
                                    horizontal = 16.dp, vertical = 8.dp
                                )
                                .clickable(
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    },
                                    indication = null
                                ) {
                                    isExpand.value = false
                                    onYearSelected.invoke(year)
                                }
                        )
                    }
                }
            }
        }
    }

}
