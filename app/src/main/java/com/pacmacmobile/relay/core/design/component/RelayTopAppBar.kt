package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun RelayTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(RelayTheme.colors.surfaceHighlight)
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .semantics { heading() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = RelayTheme.typography.h3,
            color = RelayTheme.colors.textPrimary
        )
    }
}

@Preview
@Composable
fun RelayTopAppBarPreview() {
    RelayTheme {
        RelayTopAppBar(title = "Terminal (Placeholder)")
    }
}
