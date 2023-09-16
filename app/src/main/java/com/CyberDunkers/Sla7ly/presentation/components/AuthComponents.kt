import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldString(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    labelColor :Color = Color.Gray
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = labelColor) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Constants.MAIN_ORANGE,
            unfocusedIndicatorColor = labelColor,
            containerColor = Color.White

        ), modifier = modifier, singleLine = true
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldNumber(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    labelColor :Color = Color.Gray

) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = labelColor) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Constants.MAIN_ORANGE,
            unfocusedIndicatorColor = labelColor,
            containerColor = Color.White

        ), modifier = modifier, singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldPass(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    labelColor :Color = Color.Gray

) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val passwordVisibility = remember { mutableStateOf(false) } // Track password visibility
    Box {

        OutlinedTextField(
            value = text,
            label = { Text(text = label, color = labelColor) },
            onValueChange = {
                text = it
                onNameChange(it.text)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Constants.MAIN_ORANGE,
                unfocusedIndicatorColor = labelColor,
                containerColor = Color.White
            ), singleLine = true, modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { passwordVisibility.value = !passwordVisibility.value }
        ) {
            Icon(
                modifier = modifier.padding(top = 6.dp),
                painter = if (passwordVisibility.value) painterResource(id = R.drawable.outline_visibility_24) else painterResource(
                    id = R.drawable.outline_visibility_off_24
                ),
                tint = Color.Gray,
                contentDescription = if (passwordVisibility.value) "Hide Password" else "Show Password"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldSearch(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
     // Track password visibility
    Box() {

        OutlinedTextField(
            value = text,
            label = { Text(text = label, color = Color.Gray) },
            onValueChange = {
                text = it
                onNameChange(it.text)
            }, shape = RoundedCornerShape(25.dp) ,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Constants.MAIN_ORANGE,
                unfocusedIndicatorColor = Color.Gray,
                containerColor = Color.White ,
            ), singleLine = true, modifier = modifier,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
            Icon(
                modifier = Modifier
                    .padding(top = 6.dp, end = 10.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.search_bar),
                tint = Constants.MAIN_ORANGE,
                contentDescription = ""
            )


    }
}


@Composable
fun Sla7lyText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    sizeWithSp: Int = 30,
    color: Color = Constants.MAIN_GREEN,
    textAlign: TextAlign = TextAlign.Center,
) {

    Text(
        text = text,
        color = color,
        modifier = modifier,
        fontSize = sizeWithSp.sp,
        fontWeight = fontWeight,
        fontFamily = FontFamily(Font(R.font.elmessiri)),
        textAlign = textAlign

    )
}


@Composable
fun Btn(onClick: () -> Unit, txt: String) {
    Button(onClick = onClick) {
        Text(text = txt)
    }
}

@Composable
fun NextButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(20.dp)
            .clip(CircleShape)
            .background(Constants.MAIN_ORANGE)
    ) {

        val iconPainter = painterResource(id = R.drawable.next_page)
        Image(
            painter = iconPainter,
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier.size(50.dp) // Adjust the size as needed
        )
    }
}

@Composable
fun ImageShape3() {

    // Creates an [InfiniteTransition] instance for managing child animations.
    val infiniteTransition = rememberInfiniteTransition()

    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val size by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val paddingTop by infiniteTransition.animateFloat(
        initialValue = 10f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    // Creates a Color animation as a part of the [InfiniteTransition].
    val color by infiniteTransition.animateColor(
        initialValue = Color.White,
        targetValue = Constants.THIRD_Orange, // Dark Red
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val isCircle = remember {
        mutableStateOf(true)
    }
    val borderRadius by animateIntAsState(
        targetValue = if (isCircle.value) 60 else 45, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow,

            ),

        )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingTop.dp)
            .animateContentSize()

    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier

                .width(size.dp)
                .height(size.dp)
                .background(color = color, shape = RoundedCornerShape(borderRadius))
                .clickable(interactionSource = interactionSource, indication = null) {
                    isCircle.value = !isCircle.value
                }
                .align(Alignment.TopEnd)

        )

        // Other content in the parent Box
    }
}


@Composable
fun BackBtn(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
    ) {

        val iconPainter = painterResource(id = R.drawable.back_icon)
        Image(
            painter = iconPainter,
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier.size(40.dp) // Adjust the size as needed
        )
    }
}

@Composable
fun RoundedBtn(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Constants.MAIN_GREEN,
    buttonColor: Color = Constants.SEC_ORANGE,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            onClick()
        }, shape = RoundedCornerShape(100.dp), colors = ButtonDefaults.buttonColors(
            buttonColor
        ), modifier = modifier
            .width(172.dp)
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun CustomToast(message: String) {
    Snackbar(
        action = {

        },
        modifier = Modifier.padding(16.dp),

        ) {
        Text(text = message, color = Color.White)
    }
}

@Composable
fun LogoPng(contentScale: ContentScale = ContentScale.FillBounds, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.logopng),
        contentDescription = "image description",
        contentScale = contentScale
    )
}

@Composable
fun ImageShape4() {
    val isVisible = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(isVisible.value) {
        // Hide the box for 200 milliseconds
        if (!isVisible.value) {
            delay(1)
            isVisible.value = true
        }
    }
    val size by animateFloatAsState(
        targetValue = if (!isVisible.value) 10f else 100f,
        label = "", animationSpec = tween(1000)
    )




    val isCircle = remember {
        mutableStateOf(true)
    }
    val borderRadius by animateIntAsState(
        targetValue = if (isCircle.value) 60 else 45, label = "",
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow,

            ),

        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .animateContentSize()

    ) {
        val interactionSource = remember { MutableInteractionSource() }
        Box(
            modifier = Modifier

                .width(size.dp)
                .height(size.dp)
                .background(color = Color(0x9EFFFFFF), shape = RoundedCornerShape(borderRadius))
                .clickable(interactionSource = interactionSource, indication = null) {
                    isCircle.value = !isCircle.value
                }
                .padding(top = 50.dp, end = 50.dp)
                .align(Alignment.TopEnd)

        )

        // Other content in the parent Box
    }
}

@Composable
fun CircularIcon(
    resID: Int,
    background: Color = Color.White,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imgSizeWithDP : Dp = 50.dp
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .background(background)
    ) {

        val iconPainter = painterResource(resID)
        Image(
            painter = iconPainter,
            contentDescription = null, // Provide a meaningful description if needed
            modifier = Modifier
                .size(imgSizeWithDP)
                .padding(8.dp).clip(CircleShape) // Adjust the size as needed
            , contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun CustomLoading(
    modifier: Modifier = Modifier,
    circleSize: Dp = 25.dp,
    spaceBetween: Dp = 10.dp,
    travelDistance: Dp = 20.dp
) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable) {
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { travelDistance.toPx() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        circleValues.forEachIndexed() {  index ,value  ->
            Box(
                modifier = Modifier
                    .size(circleSize)
                    .graphicsLayer {
                        translationY = -value * distance
                    }
                    .background(
                        color = if (index == 0) Constants.MAIN_ORANGE else if (index == 1) Constants.THIRD_Orange else Constants.SEC_ORANGE,
                        shape = CircleShape
                    )
            )
        }
    }

}
@Composable
fun LoadingPage(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White).padding(top = 140.dp) , horizontalAlignment = Alignment.CenterHorizontally) {
        LogoPng(modifier = Modifier.size(width = 262.dp , height = 75.dp))
        CustomLoading(modifier = Modifier.padding( top = 200.dp) )
    }
}