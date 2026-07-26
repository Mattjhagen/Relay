package com.pacmacmobile.relay.feature.terminal

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.component.PlaceholderPanel
import com.pacmacmobile.relay.core.design.component.RelayTopAppBar
import com.pacmacmobile.relay.core.design.component.ShortcutKeyChip
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun TerminalScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(RelayTheme.colors.background)
    ) {
        RelayTopAppBar(title = "Terminal (Placeholder)")

        // Shortcut row
        val shortcuts = listOf("ESC", "CTRL", "TAB", "↑", "↓", "←", "→", "/", "-", "|")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(RelayTheme.colors.surface)
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            shortcuts.forEach { key ->
                ShortcutKeyChip(keyName = key, onClick = { /* TODO Phase 4 */ })
            }
        }

        // Terminal Surface Placeholder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "user@opencode:~$ echo 'Terminal engine coming in Phase 04'\nTerminal engine coming in Phase 04\nuser@opencode:~$ _",
                style = RelayTheme.typography.terminal,
                color = RelayTheme.colors.textPrimary
            )
            
            PlaceholderPanel(message = "Terminal engine coming in Phase 04")
        }
    }
}

@Preview(name = "Terminal Screen Phone")
@Preview(name = "Terminal Screen Tablet", device = "id:pixel_c")
@Composable
fun TerminalScreenPreview() {
    RelayTheme {
        TerminalScreen()
    }
}
