package com.pacmacmobile.relay.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.component.RelayCard
import com.pacmacmobile.relay.core.design.component.SectionHeader
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayTheme.colors.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        SectionHeader(title = "Connection")
        SettingsRowStub("SSH Host")
        SettingsRowStub("Port")
        SettingsRowStub("Username")

        SectionHeader(title = "Security")
        SettingsRowStub("SSH Key Configuration")
        SettingsRowStub("Biometric Unlock")

        SectionHeader(title = "Appearance")
        SettingsRowStub("Theme (Dark Mode)")

        SectionHeader(title = "Behavior")
        SettingsRowStub("Automatic Reconnect")
        
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Settings will be fully implemented in Phase 02.",
            style = RelayTheme.typography.body2,
            color = RelayTheme.colors.textSecondary
        )
    }
}

@Composable
private fun SettingsRowStub(label: String) {
    RelayCard(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
        Text(
            text = label,
            style = RelayTheme.typography.body1,
            color = RelayTheme.colors.textPrimary
        )
    }
}

@Preview(name = "Settings Screen Phone")
@Preview(name = "Settings Screen Tablet", device = "id:pixel_c")
@Composable
fun SettingsScreenPreview() {
    RelayTheme {
        SettingsScreen()
    }
}
