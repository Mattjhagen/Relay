package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = RelayTheme.typography.h3,
        color = RelayTheme.colors.textPrimary,
        modifier = modifier
            .padding(vertical = 16.dp)
            .semantics { heading() }
    )
}

@Preview
@Composable
fun SectionHeaderPreview() {
    RelayTheme {
        SectionHeader(title = "Recent Sessions")
    }
}
