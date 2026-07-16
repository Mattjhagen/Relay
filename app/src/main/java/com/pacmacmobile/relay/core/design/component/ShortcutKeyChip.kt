package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun ShortcutKeyChip(
    keyName: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(RelayTheme.colors.surfaceHighlight)
            .clickable(onClick = onClick, role = Role.Button)
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .semantics {
                contentDescription = "Shortcut key $keyName"
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = keyName,
            style = RelayTheme.typography.terminal,
            color = RelayTheme.colors.textPrimary
        )
    }
}

@Preview
@Composable
fun ShortcutKeyChipPreview() {
    RelayTheme {
        ShortcutKeyChip(keyName = "ESC", onClick = {})
    }
}
