import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CyberDunkers.Sla7ly.R
import com.CyberDunkers.Sla7ly.common.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldString(
    onNameChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = Color.Gray) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Constants.MAIN_ORANGE,
            unfocusedIndicatorColor = Color.Gray,
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
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = Color.Gray) },
        onValueChange = {
            text = it
            onNameChange(it.text)
        }, colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Constants.MAIN_ORANGE,
            unfocusedIndicatorColor = Color.Gray,
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
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val passwordVisibility = remember { mutableStateOf(false) } // Track password visibility
    Box {

        OutlinedTextField(
            value = text,
            label = { Text(text = label, color = Color.Gray) },
            onValueChange = {
                text = it
                onNameChange(it.text)
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Constants.MAIN_ORANGE,
                unfocusedIndicatorColor = Color.Gray,
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
                tint  =  Color.Gray
            ,
                contentDescription = if (passwordVisibility.value) "Hide Password" else "Show Password"
            )
        }
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
fun CircularIcon(
    resID: Int,
    background: Color = Color.White,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                .size(50.dp)
                .padding(8.dp) // Adjust the size as needed
        )
    }
}
