package com.example.onetimepassword

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.onetimepassword.ui.theme.OneTimePasswordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneTimePasswordTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    OtpView()
                }
            }
        }
    }
}

@Composable
fun OtpView(
    modifier: Modifier = Modifier,
    otpText: String= "",
    itemCount: Int = 4,
    withBorders: Boolean = true,
    borderColor: Color = Black,
    textStyle: TextStyle = TextStyle.Default,
    cornerRadius: Dp = 11.dp,
    borderWidth: Dp = 2.dp,
    itemSpacing: Dp = 8.dp,
    itemWidth: Dp = 54.dp,
    itemHeight: Dp = 48.dp,
    itemBackground: Color = Blue,
    //onOtpTextChange: (String) -> Unit
){
    BasicTextField(
        value = otpText,
        onValueChange = {
//            if(it.length <= itemCount){
//                onOtpTextChange.invoke(it)
//           }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(itemCount){ index ->
                    OtpItem(
                        index = index,
                        text = otpText,
                        modifier = modifier,
                        textStyle,
                        withBorders,
                        borderWidth,
                        borderColor,
                        cornerRadius,
                        itemWidth,
                        itemHeight,
                        itemBackground
                    )
                    Spacer(modifier = Modifier.width(itemSpacing))
                }
            }
        }
    )
}

@Composable
fun OtpItem(
    index: Int = 0,
    text: String = "",
    modifier: Modifier,
    textStyle: TextStyle,
    withBorders: Boolean,
    borderWidth: Dp,
    borderColor: Color,
    cornerRadius: Dp,
    itemWidth: Dp,
    itemHeight: Dp,
    itemBackground: Color
) {
    val isFocused = text.length == index
    val char = when{
        index == text.length -> ""
        index > text.length -> ""
        else -> text[index].toString()
    }
    Box(
        modifier = modifier
            .width(itemWidth)
            .height(itemHeight)
            .background(color = itemBackground, shape = RoundedCornerShape(cornerRadius))
            .border(
                if (withBorders) borderWidth else 0.dp,
                if (withBorders) {
                    when {
                        isFocused -> borderColor
                        else -> Color.Transparent
                    }
                } else Color.Transparent,
                RoundedCornerShape(cornerRadius)
            )
    ){
        Text(
            text = char,
            textAlign = TextAlign.Center,
            style = textStyle
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OneTimePasswordTheme {
        OtpView()
    }
}