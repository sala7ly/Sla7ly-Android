package com.CyberDunkers.Sla7ly.presentation.bottom_nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintBookingScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintFavScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintHomeScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.ClintProfileScreenDestination
import com.CyberDunkers.Sla7ly.presentation.destinations.DirectionDestination

enum class BottomBarDestination(
    val direction: DirectionDestination,
    val icon: ImageVector,
    val label: String
) {
    Home(ClintHomeScreenDestination, Icons.Default.Home, "home"),
    Fav(ClintFavScreenDestination, Icons.Default.Favorite, "fav"),
    Booking(ClintBookingScreenDestination, Icons.Default.DateRange, "Booking"),
    Profile(ClintProfileScreenDestination, Icons.Default.Person, "profile"),

}
