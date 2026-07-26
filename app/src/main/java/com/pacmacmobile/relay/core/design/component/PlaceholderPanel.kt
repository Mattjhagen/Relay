package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun PlaceholderPanel(
    message: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = RelayTheme.typography.body1,
            color = RelayTheme.colors.textSecondary
        )
    }
}

@Preview
@Composable
fun PlaceholderPanelPreview() {
    RelayTheme {
        PlaceholderPanel(message = "Terminal engine coming in Phase 04")
    }
}
