package com.CyberDunkers.Sla7ly.presentation.onBoarding

import androidx.annotation.DrawableRes
import com.CyberDunkers.Sla7ly.R


data class Page(
   @DrawableRes val logo: Int,
   @DrawableRes val img: Int,
    val title: String,
    val desc: String,
)

val listOfPages = listOf<Page>(
    Page(
        logo = R.drawable.logo,
        img = R.drawable.onboarding_one,
        title = "مرحبًا بكم في ابلكيشن صلحلي",
        desc = "نحن نقوم بتوصيل العمال المهرة بالعملاء المحتاجين لخدماتهم\n" +
                "تم تصميم الأبلكيشن لتوفير طريقة سلسة وفعالة للعملاء للعثور على العامل المثالي الذي يلبي احتياجاتهم الخاصة"
    ),


    Page(
        logo = R.drawable.logo,
        img = R.drawable.onboaeding_two,
        title = "من خلال واجهتنا سهلة الاستخدام" ,
        desc = "يمكن للعملاء تصفح قاعدة بيانات واسعة من العمال، وقراءة المراجعات، ومقارنة التقييمات لاتخاذ قرار مستنير. بمجرد العثور على العامل المثالي، يمكنك التواصل معه مباشرة وطلب خدماته."
    ) ,

    Page(
        logo = R.drawable.logo,
        img = R.drawable.onboarding_three,
        title = "مع صلحلي حافظ على ممتلكاتك الخاصة" ,
        desc ="من خلال التواصل مع العمال المتخصصين في مختلف المهن والحرف. سواء كنت بحاجة إلى سباك، أو نجار، أو منظف، أو أي مهنة محددة أخرى، فإن تطبيقنا يتيح لك تحديد الخدمة التي تحتاجها بالضبط."

        )

    )