package com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components

import androidx.annotation.DrawableRes
import com.CyberDunkers.Sla7ly.R

data class WorkerItemData(
    @DrawableRes val imgRes: Int, val jobTitle: String
)

val maintenanceList =  mutableListOf<WorkerItemData>(
    WorkerItemData( R.drawable.gas_stove ,"Gas Stove" ),
    WorkerItemData( R.drawable.refrigerator ,"refrigerator" ),
    WorkerItemData( R.drawable.washing_machine ,"Washing Machine" ),
    WorkerItemData( R.drawable.airconditioner ,"air conditioner" ),
    WorkerItemData( R.drawable.fan ,"fan" ),
    WorkerItemData( R.drawable.television ,"television" ),
)

val CleaningList =  mutableListOf<WorkerItemData>(
    WorkerItemData( R.drawable.house_cleaning ,"Car Washing" ),
    WorkerItemData( R.drawable.car_service ,"home cleaning" ),
)

val homeservices = mutableListOf(
    WorkerItemData( R.drawable.plumbing ,"plumbing" ),
    WorkerItemData( R.drawable.backup ,"backup" ),
    WorkerItemData( R.drawable.roller ,"roller" ),
    WorkerItemData( R.drawable.handsaw ,"handsaw" ),
    WorkerItemData( R.drawable.tiles ,"tiles" ),
)
