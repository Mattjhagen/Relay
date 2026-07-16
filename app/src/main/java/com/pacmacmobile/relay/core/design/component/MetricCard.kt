package com.pacmacmobile.relay.core.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacmacmobile.relay.core.design.theme.RelayTheme

@Composable
fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    RelayCard(modifier = modifier.semantics(mergeDescendants = true) {
        contentDescription = "$label: $value"
    }) {
        Column {
            Text(
                text = label,
                style = RelayTheme.typography.body2,
                color = RelayTheme.colors.textSecondary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = RelayTheme.typography.h2,
                color = RelayTheme.colors.textPrimary
            )
        }
    }
}

@Preview
@Composable
fun MetricCardPreview() {
    RelayTheme {
        MetricCard(label = "CPU Usage", value = "12% (Demo)")
    }
}
