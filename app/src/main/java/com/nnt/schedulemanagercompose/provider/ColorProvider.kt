package com.nnt.schedulemanagercompose.provider

import androidx.compose.ui.graphics.Color
import com.nnt.schedulemanagercompose.ui.theme.TextColorPrimary
import com.nnt.schedulemanagercompose.ui.theme.TextColorSecondary

/**
 * Created by ThangNNT on 02/12/2023.
 */
object ColorProvider {
    var appColors: AppColor = AppColor.Default
}

abstract class AppColor {
    abstract val activeBottomBarIcon: Color
    abstract val inactiveBottomBarIcon: Color
    abstract val bottomBarBackground: Color
    abstract val windowBackground: Color
    abstract val cardBackground: Color
    abstract val textPrimary: Color
    abstract val textPrimaryContrast: Color
    abstract val textSecondary: Color
    abstract val completeTaskIconTint: Color
    abstract val incompleteTaskIconTint: Color
    abstract val selectDateColor: Color
    abstract val selectedDateText: Color

    companion object {
        val Default = object: AppColor(){
            override val activeBottomBarIcon: Color
                get() = Color(0xFF000000)
            override val inactiveBottomBarIcon: Color
                get() = Color(0xFF676767)
            override val bottomBarBackground: Color
                get() = Color.White
            override val windowBackground: Color
                get() = Color.White
            override val cardBackground: Color
                get() = Color.LightGray
            override val textPrimary: Color
                get() = TextColorPrimary
            override val textPrimaryContrast: Color
                get() = Color.White
            override val textSecondary: Color
                get() = TextColorSecondary
            override val completeTaskIconTint: Color
                get() = Color(0xFF4CAF50)
            override val incompleteTaskIconTint: Color
                get() = Color(0xFF676767)
            override val selectDateColor: Color
                get() = Color.DarkGray
            override val selectedDateText: Color
                get() = textPrimaryContrast

        }
    }
}

