@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.learningcodingapp.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcodingapp.R

// Palette مطابقة للصورة
private val PageBg = Color(0xFFF5F6FA)
private val Primary = Color(0xFF3F4EB3)      // البنفسجي/أزرق الغامق
private val Blob = Color(0xFFE7EBFF)         // الأزرق الفاتح للخلفية
private val FieldBg = Color.White
private val FieldRadius = 14.dp

@Composable
fun LoginScreen() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PageBg)
            .padding(horizontal = 20.dp)
    ) {
        // زخرفة الخلفية (blobs) مثل الصورة
        BackgroundBlobs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 24.dp , vertical = 96.dp)
                .verticalScroll(rememberScrollState())
            ,
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {

            // Logo دائري مع حدود خفيفة
            Image(
                painter = painterResource(R.drawable.ic_logo_coding_app),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Primary.copy(alpha = 0.35f), CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Login",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF121212)
            )

            Spacer(Modifier.height(16.dp))

            // Email
            LabeledField(
                value = email.value,
                onValueChange = { email.value = it },
                placeholder = "Email address",
                leadingContent = {
                    IconBadge { androidx.compose.material3.Icon(Icons.Filled.Email, null, tint = Color.White) }
                }
            )

            // Password
            LabeledField(
                value = password.value,
                onValueChange = { password.value = it },
                placeholder = "Password",
                visualTransformation = PasswordVisualTransformation(),
                leadingContent = {
                    IconBadge { androidx.compose.material3.Icon(Icons.Filled.Lock, null, tint = Color.White) }
                }
            )

            Spacer(Modifier.height(6.dp))

            // زر Log in (Primary)
            Button(
                onClick = { /* no-op */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(FieldRadius),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Color.White
                )
            ) {
                Text("Log in", fontSize = 16.sp)
            }

            // فاصل OR

            Spacer(Modifier.height(12.dp))

            Spacer(Modifier.height(4.dp))

            TextButton(onClick = { /* no-op (UI only) */ }) {
                Text("Forgot password ?", color = Color.Black, fontWeight = FontWeight.SemiBold)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider(modifier = Modifier.weight(1f), color = Color(0x1A000000))
                Text("  OR  ", color = Color.Gray)
                Divider(modifier = Modifier.weight(1f), color = Color(0x1A000000))
            }
            Spacer(Modifier.height(10.dp))

            // sign up (Outlined) مع سهم
            OutlinedButton(
                onClick = { /* no-op */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(FieldRadius),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 1.dp,
                    brush = SolidColor(Primary.copy(alpha = 0.9f))
                ),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Primary)
            ) {
                Text("sign up")


            }

            Spacer(Modifier.height(28.dp))
        }
    }
}

@Composable
private fun LabeledField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: androidx.compose.ui.text.input.VisualTransformation =
        androidx.compose.ui.text.input.VisualTransformation.None,
    leadingContent: @Composable () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        singleLine = true,
        leadingIcon = {
            // مربع ملون للأيقونة مثل الصورة
            IconBadge { leadingContent() }
        },
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(FieldRadius),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            focusedContainerColor = FieldBg,
            unfocusedContainerColor = FieldBg
        )
    )
}

@Composable
private fun IconBadge(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Primary),
        contentAlignment = Alignment.Center
    ) { content() }
}

@Composable
private fun BackgroundBlobs() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // Blob كبير أعلى اليسار
        val p1 = Path().apply {
            moveTo(0f, h * 0.14f)
            cubicTo(w * 0.18f, h * 0.02f, w * 0.45f, h * 0.10f, w * 0.52f, h * 0.28f)
            lineTo(0f, h * 0.28f)
            close()
        }
        drawPath(p1, Blob.copy(alpha = 0.8f))

        // نقطة صغيرة (دائرة) يمين العنوان
        drawCircle(
            color = Blob.copy(alpha = 0.9f),
            radius = 6.dp.toPx(),
            center = Offset(w * 0.78f, h * 0.24f)
        )

        // Blob كبير أسفل اليمين
        val p2 = Path().apply {
            moveTo(w * 0.55f, h * 0.82f)
            cubicTo(w * 0.78f, h * 0.70f, w * 1.08f, h * 0.90f, w, h)
            lineTo(w * 0.60f, h)
            close()
        }
        drawPath(p2, Blob.copy(alpha = 0.9f))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoginScreen_ProUI() {
    MaterialTheme { LoginScreen() }
}
