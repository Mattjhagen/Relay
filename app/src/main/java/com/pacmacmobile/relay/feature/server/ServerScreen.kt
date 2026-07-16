package com.pacmacmobile.relay.feature.server

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.component.MetricCard
import com.pacmacmobile.relay.core.design.component.SectionHeader
import com.pacmacmobile.relay.core.design.theme.RelayTheme
import com.pacmacmobile.relay.core.preview.DemoData

@Composable
fun ServerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayTheme.colors.background)
            .padding(horizontal = 24.dp)
    ) {
        SectionHeader(title = "Server Dashboard (Demo)")
        
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            val metrics = DemoData.metrics
            item { MetricCard(label = "CPU", value = "${metrics.cpuUsagePercent}%") }
            item { MetricCard(label = "Memory", value = "${metrics.memoryUsageGb} / ${metrics.memoryTotalGb} GB") }
            item { MetricCard(label = "Disk", value = "${metrics.diskUsageGb} / ${metrics.diskTotalGb} GB") }
            item { MetricCard(label = "Temperature", value = "${metrics.temperatureCelsius}°C") }
            item { MetricCard(label = "Uptime", value = "${metrics.uptimeDays} days") }
            item { MetricCard(label = "Ollama", value = metrics.ollamaStatus) }
            item { MetricCard(label = "OpenCode", value = metrics.openCodeStatus) }
            item { MetricCard(label = "tmux Sessions", value = "${metrics.activeTmuxSessions}") }
        }
    }
}

@Preview(name = "Server Screen Phone")
@Preview(name = "Server Screen Tablet", device = "id:pixel_c")
@Composable
fun ServerScreenPreview() {
    RelayTheme {
        ServerScreen()
    }
}
