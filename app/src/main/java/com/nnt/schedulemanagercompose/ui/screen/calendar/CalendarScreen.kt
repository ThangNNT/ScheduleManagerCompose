package com.nnt.schedulemanagercompose.ui.screen.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.nnt.schedulemanagercompose.AppConstant
import com.nnt.schedulemanagercompose.extension.statusBarHeight
import com.nnt.schedulemanagercompose.extension.toString
import com.nnt.schedulemanagercompose.provider.ColorProvider
import com.nnt.schedulemanagercompose.ui.common.HorizontalSpacer
import com.nnt.schedulemanagercompose.ui.common.VerticalSpacer
import com.nnt.schedulemanagercompose.ui.component.Day
import com.nnt.schedulemanagercompose.ui.component.DaysOfWeekTitle
import com.nnt.schedulemanagercompose.ui.component.MonthYearView
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

/**
 * Created by ThangNNT on 02/12/2023.
 */
@Composable
fun CalendarScreen(){
    val coroutineScope = rememberCoroutineScope()
    val daysOfWeek = remember { daysOfWeek(DayOfWeek.MONDAY) }
    val selectedDate = remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }
    val state = rememberCalendarState(
        startMonth = YearMonth.of(AppConstant.CALENDAR_MIN_YEAR, Month.JANUARY),
        endMonth = YearMonth.of(AppConstant.CALENDAR_MAX_YEAR, Month.DECEMBER),
        firstVisibleMonth = YearMonth.now(),
        firstDayOfWeek = daysOfWeek.first()
    )
    Column(
        modifier = Modifier
            .background(color = ColorProvider.appColors.windowBackground)
            .padding(top = statusBarHeight() + 16.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        MonthYearView(
            yearMonth = state.firstVisibleMonth.yearMonth,
            onArrowLeftClick = {
                coroutineScope.launch {
                    state.animateScrollToMonth(it.minusMonths(1))
                }
            }, onArrowRightClick = {
                coroutineScope.launch {
                    state.animateScrollToMonth(it.plusMonths(1))
                }
            }, onYearMonthChanged = {
                coroutineScope.launch {
                    state.scrollToMonth(it)
                }
            }, onRefresh = {
                coroutineScope.launch {
                    state.scrollToMonth(YearMonth.now())
                    selectedDate.value = LocalDate.now()
                }
            })
        DaysOfWeekTitle(daysOfWeek = daysOfWeek) // Use the title here
        HorizontalCalendar(state = state, dayContent = {
            Day(
                day = it,
                isSelected = selectedDate.value == it.date,
                onSelected = { selectedCalendar ->
                    selectedDate.value = selectedCalendar.date
                })
        })
        VerticalSpacer(height = 16.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = com.nnt.schedulemanagercompose.R.drawable.ic_calendar_24),
                contentDescription = "calendar icon"
            )
            HorizontalSpacer(width = 8.dp)
            Text(
                text = selectedDate.value.toString("dd/MM/yyyy"),
                style = MaterialTheme.typography.h6
            )
        }
    }
}
  