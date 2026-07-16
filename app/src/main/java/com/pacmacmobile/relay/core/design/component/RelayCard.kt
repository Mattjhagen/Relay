package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme
import androidx.compose.material3.Text

@Composable
fun RelayCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(RelayTheme.colors.surface)
            .border(1.dp, RelayTheme.colors.border, RoundedCornerShape(24.dp))
            .padding(24.dp),
        content = content
    )
}

@Preview(name = "RelayCard Phone")
@Preview(name = "RelayCard Tablet", device = "id:pixel_c")
@Composable
fun RelayCardPreview() {
    RelayTheme {
        RelayCard {
            Text(
                text = "Preview Content",
                style = RelayTheme.typography.body1,
                color = RelayTheme.colors.textPrimary
            )
        }
    }
}
