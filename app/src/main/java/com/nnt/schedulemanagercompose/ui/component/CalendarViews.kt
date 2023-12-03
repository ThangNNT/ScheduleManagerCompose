package com.nnt.schedulemanagercompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.nnt.schedulemanagercompose.provider.ColorProvider
import java.time.DayOfWeek
import java.time.LocalDate
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
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (day.position == DayPosition.MonthDate) {
                    onSelected.invoke(day)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        // display selected date mark
        if (isSelected){
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(
                        color = ColorProvider.appColors.selectDateColor,
                        shape = CircleShape
                    )
                    .fillMaxSize()
            )
        }
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor.value
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
            )
        }
    }
}
@Composable
fun MonthYear(yearMonth: YearMonth){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp), contentAlignment = Alignment.Center) {
        Text(text = "${yearMonth.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault())} ${yearMonth.year}")
    }
}