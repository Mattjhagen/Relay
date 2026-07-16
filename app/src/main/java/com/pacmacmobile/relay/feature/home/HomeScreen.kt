package com.pacmacmobile.relay.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.component.RelayButton
import com.pacmacmobile.relay.core.design.component.RelayCard
import com.pacmacmobile.relay.core.design.component.SectionHeader
import com.pacmacmobile.relay.core.design.component.StatusIndicator
import com.pacmacmobile.relay.core.design.theme.RelayTheme
import com.pacmacmobile.relay.core.preview.DemoData

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayTheme.colors.background)
            .padding(24.dp)
    ) {
        // Logo / Title
        Text(
            text = "Relay",
            style = RelayTheme.typography.h1,
            color = RelayTheme.colors.textPrimary,
            modifier = Modifier
                .padding(bottom = 32.dp, top = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Server Connection Card
        RelayCard(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = DemoData.server.name,
                    style = RelayTheme.typography.h2,
                    color = RelayTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Status:", style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        StatusIndicator(isOnline = false, modifier = Modifier.padding(end = 8.dp))
                        Text(text = DemoData.server.status, style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Ping:", style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                    Text(text = DemoData.server.pingMs?.let { "$it ms" } ?: "-- ms", style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Last Connected:", style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                    Text(text = DemoData.server.lastConnected, style = RelayTheme.typography.body1, color = RelayTheme.colors.textSecondary)
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                RelayButton(
                    text = "Connect",
                    onClick = { /* TODO Phase 3: Implement connection */ },
                    modifier = Modifier.fillMaxWidth(),
                    contentDescription = "Connect to server"
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Recent Sessions Section
        SectionHeader(title = "Recent Sessions (Demo)")
        DemoData.sessions.forEach { session ->
            RelayCard(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = session.name, style = RelayTheme.typography.h3, color = RelayTheme.colors.textPrimary)
                        Text(text = "${session.windowCount} windows", style = RelayTheme.typography.body2, color = RelayTheme.colors.textSecondary)
                    }
                    Text(text = session.lastAttached, style = RelayTheme.typography.body2, color = RelayTheme.colors.textSecondary)
                }
            }
        }
    }
}

@Preview(name = "Home Screen Phone")
@Preview(name = "Home Screen Tablet", device = "id:pixel_c")
@Composable
fun HomeScreenPreview() {
    RelayTheme {
        HomeScreen()
    }
}
