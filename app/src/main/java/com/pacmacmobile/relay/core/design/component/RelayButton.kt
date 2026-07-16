package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun RelayButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .semantics { 
                contentDescription?.let { this.contentDescription = it }
            },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RelayTheme.colors.accent,
            contentColor = RelayTheme.colors.textPrimary
        )
    ) {
        Text(text, style = RelayTheme.typography.h3)
    }
}

@Preview(name = "RelayButton")
@Composable
fun RelayButtonPreview() {
    RelayTheme {
        RelayButton(text = "Connect", onClick = {})
    }
}
