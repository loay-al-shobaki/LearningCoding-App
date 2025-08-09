
package com.example.learningcodingapp.ui.auth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit = {},
    onSend: (String) -> Unit = {},
    onSignUp: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }

    // ألوان قريبة من التصميم
    val bg = Color(0xFFF6F8FF)
    val blob = Color(0xFFDDE5FF)
    val primary = Color(0xFF343F8F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)

    ) {
        // أشكال الخلفية
        BackgroundBlobs(color = blob)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Back
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 4.dp, start = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF2B2B2B)
                )
            }

            Spacer(Modifier.height(8.dp))

            // Title
            Text(
                text = "Reset password",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1D1D),
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(Modifier.height(6.dp))

            // Description
            Text(
                text = "We will email you\na link to reset your password.",
                fontSize = 14.sp,
                color = Color(0xFF6F6F6F),
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(22.dp))

            // Label
            Box(Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                Text(
                    text = "Email",
                    fontSize = 14.sp,
                    color = Color(0xFF3A3A3A),
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            Spacer(Modifier.height(6.dp))

            // Email field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                placeholder = { Text("sarah.jansen@gmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(10.dp),
            )

            Spacer(Modifier.height(16.dp))

            // Send button
            Button(
                onClick = { onSend(email) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = primary)
            ) {
                Text(
                    "Send",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(26.dp))

            // Divider with OR
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2F2F2F),
                    thickness = 1.dp
                )
                Text(
                    "  OR  ",
                    color = Color(0xFF2F2F2F),
                    fontSize = 12.sp
                )
                Divider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2F2F2F),
                    thickness = 1.dp
                )
            }

            Spacer(Modifier.height(16.dp))

            // Sign up outlined button
            OutlinedButton(
                onClick = onSignUp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(14.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 2.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = primary
                )
            ) {
                Text(
                    text = "sign up ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun BackgroundBlobs(color: Color) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // شكل علوي يسار
        val p1 = Path().apply {
            moveTo(w * 0.0f, h * 0.0f)
            lineTo(w * 0.55f, 0f)
            quadraticBezierTo(w * 0.78f, h * 0.18f, w * 0.45f, h * 0.30f)
            quadraticBezierTo(w * 0.15f, h * 0.42f, 0f, h * 0.35f)
            close()
        }
        drawPath(p1, color)

        // شكل سفلي يمين
        val p2 = Path().apply {
            moveTo(w, h * 0.55f)
            quadraticBezierTo(w * 0.78f, h * 0.70f, w * 0.82f, h * 0.88f)
            quadraticBezierTo(w * 0.84f, h * 1.05f, w * 0.60f, h * 1.05f)
            quadraticBezierTo(w * 0.40f, h * 1.05f, w * 0.38f, h * 0.84f)
            quadraticBezierTo(w * 0.36f, h * 0.70f, w * 0.55f, h * 0.58f)
            close()
        }
        drawPath(p2, color)

        // ظل خفيف
        drawCircle(
            color = color.copy(alpha = 0.25f),
            radius = w * 0.18f,
            center = Offset(w * 0.82f, h * 0.18f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ResetPasswordPreview() {
    MaterialTheme {
        ResetPasswordScreen()
    }
}

