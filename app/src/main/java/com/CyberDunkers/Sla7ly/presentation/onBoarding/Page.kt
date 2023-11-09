package com.CyberDunkers.Sla7ly.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.CyberDunkers.Sla7ly.R


data class Page(
   @DrawableRes val logo: Int,
   @DrawableRes val background: Int = R.drawable.polygon ,
   @DrawableRes val img: Int,
    val title: String,
    val desc: String,
){
    companion object {

        val listOfPages = mutableListOf(
            Page(
                logo = R.drawable.home_logo_org,
                img = R.drawable.on1,
                title = "",
                desc = "نقوم بتوصيل العماله المهرة بالعملاء المحتاجين لخدماتهم.",
            ),


            Page(
                logo = R.drawable.home_logo_org,
                img = R.drawable.on2,
                title = "",
                desc = "من خلال واجهه سهلة الاستخدام يمكنك التواصل مع العمال المتخصصين في مختلف المهن والحرف بكل سهوله ويسر" ,

            ) ,

            Page(
                logo = R.drawable.home_logo_org,
                img = R.drawable.on3,
                title = "HomeFix" ,
                desc ="هو اختيارك الامثل للحفاظ علي بيتك"

            )

        )
    }
}

