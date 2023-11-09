package com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen

import CircularIcon
import CompleteDataDialog
import RoundedBtn
import Sla7lyText
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import com.CyberDunkers.Sla7ly.common.toMultiPart
import com.CyberDunkers.Sla7ly.common.toRequestBody
import com.CyberDunkers.Sla7ly.common.validation.isValidNationalID
import com.CyberDunkers.Sla7ly.presentation.clint.home.MapScreen.MapsScreen
import com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components.CleaningList
import com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components.WorkerItem
import com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components.homeservices
import com.CyberDunkers.Sla7ly.presentation.clint.home.homeScreen.components.maintenanceList
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import org.osmdroid.util.GeoPoint

@Destination
@Composable
fun ClintHomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
) {


    val dataDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    var imageUri = remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val isVisible = remember {
        mutableStateOf(false)
    }
    val address = remember {
        mutableStateOf("")
    }
    val geoPoint = remember {
        mutableStateOf(GeoPoint(0.0, 0.0))
    }
    val closeMap: MutableState<Boolean> = remember {
        mutableStateOf(true)
    }
    val nationalId: MutableState<String> = remember {
        mutableStateOf("")
    }
    val profilePic: MutableState<Uri> = remember {
        mutableStateOf(Uri.EMPTY)
    }



    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            imageUri.value = uri
        }
    }

    Column(Modifier.fillMaxSize()) {

        if (closeMap.value) {
            Log.d("address", address.value)

            if (dataDialog.value) {
                CompleteDataDialog(
                    onNationalIdChange = {
                        nationalId.value = it
                    },
                    onLocationChange = {
                        address.value = it
                    },
                    onLocationClick = {
                        closeMap.value = false
                        Log.d("uri from luncher " , geoPoint.value.toString())

                    },
                    onImgePickerClick = {
                     launcher.launch("image/*").toString().toUri()


                    },
                    onDismiss = {
                        if (nationalId.isValidNationalID() && address.value.isNotEmpty() ) {
                            Log.d("data for confermation" , imageUri.value.toString() + " " + address.value+" " + geoPoint.value + " " + nationalId.value)
                            viewModel.dataConformation(
                                nationalId.value.toRequestBody(),
                                address.value.toRequestBody(),
                                geoPoint.value.latitude.toString().toRequestBody(),
                                geoPoint.value.longitude.toString().toRequestBody(),
                                imageUri.value.toMultiPart(context),
                            )
                        }
                        dataDialog.value = false
                    },
                    profilePic = imageUri.value,
                    context = context, address = address,
                    nationalId = nationalId
                )
            }
            LazyColumn(modifier = Modifier.padding(bottom = 50.dp)) {
                item {
                    Column {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 20.dp, bottom = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Column {
                                CircularIcon(
                                    resID = R.drawable.clint_person,
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier
                                        .padding(start = 20.dp, bottom = 10.dp)
                                        .shadow(50.dp)
                                )
                                AlertBox {
                                    dataDialog.value = true
                                }

                            }
                            Column {
                                Sla7lyText(
                                    text = "Welcome,",
                                    sizeWithSp = 18,
                                    color = Constants.Main_Yellow
                                )
                                Sla7lyText(text = "Ahmed", sizeWithSp = 12, color = Constants.Blue2)
                            }


                        }

                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                Modifier
                                    .weight(2f)
                                    .height(2.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Constants.Main_Yellow,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                            )
                            Sla7lyText(
                                text = "Our Services",
                                modifier = Modifier.weight(2f),
                                sizeWithSp = 18,
                                color = Constants.Blue1,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Box(
                                Modifier
                                    .weight(2f)
                                    .height(2.dp)
                                    .fillMaxWidth()
                                    .background(
                                        Constants.Main_Yellow,
                                        shape = RoundedCornerShape(50.dp)
                                    )
                            )

                        }
                    }

                }

                item {

                    Column(Modifier.fillMaxSize()) {
                        TitleSection(title = "Maintenance Services")
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            items(maintenanceList) { item ->
                                WorkerItem(workerItemData = item) {

                                }
                            }
                        }
                    }

                }

                item {
                    Column(Modifier.fillMaxSize()) {
                        TitleSection(title = "Cleaning Services")
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            items(CleaningList) { item ->
                                WorkerItem(workerItemData = item) {

                                }
                            }
                        }
                    }
                }

                item {

                    Column(Modifier.fillMaxSize()) {
                        TitleSection(title = "Maintenance Services")
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            items(homeservices) { item ->
                                WorkerItem(workerItemData = item) {

                                }
                            }
                        }
                    }

                }

            }

        } else {
            MapsScreen(address = address, geoPoint = geoPoint, closeMap = closeMap)
        }


    }


}

@Composable
fun AlertBox(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(
                Constants.CUSTOM_RED,
                shape = RoundedCornerShape(topEnd = 50.dp, bottomEnd = 50.dp)
            )
            .padding(10.dp)
    ) {
        Column {
            Sla7lyText(
                text = "please click here to complete your data ",
                color = Color.White,
                sizeWithSp = 12
            )
            RoundedBtn(
                onClick = {
                    onClick()
                },
                text = "complete my data",
                buttonColor = Color.White,
                textColor = Color.Black,
                textSizeSp = 12,
                modifier = Modifier
                    .width(150.dp)
                    .wrapContentHeight()
            )
        }
        Image(painter = painterResource(id = R.drawable.warning), contentDescription = "")
    }
}

@Composable
fun TitleSection(title: String) {
    Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxSize()) {
        Sla7lyText(
            text = title + "     ",
            sizeWithSp = 18,
            color = Constants.Blue1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        )
        Box(
            Modifier
                .height(5.dp)
                .width(200.dp)
                .background(Constants.Main_Yellow, shape = RoundedCornerShape(50.dp))
                .padding(bottom = 10.dp)
        )
    }
}

