package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun StatusIndicator(
    isOnline: Boolean,
    modifier: Modifier = Modifier
) {
    val color = if (isOnline) Color(0xFF4CAF50) else Color(0xFFF44336)
    val statusText = if (isOnline) "Online" else "Offline"
    
    Box(
        modifier = modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(color)
            .semantics { 
                contentDescription = "Status: $statusText"
            }
    )
}

@Preview
@Composable
fun StatusIndicatorPreview() {
    RelayTheme {
        StatusIndicator(isOnline = true)
    }
}
