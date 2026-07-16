package com.pacmacmobile.relay.core.preview

data class DemoServer(
    val name: String = "My Server (Demo)",
    val status: String = "Offline (Demo)",
    val pingMs: Int? = null,
    val lastConnected: String = "Never"
)

data class DemoMetrics(
    val cpuUsagePercent: Int = 12,
    val memoryUsageGb: Double = 4.2,
    val memoryTotalGb: Double = 16.0,
    val diskUsageGb: Int = 128,
    val diskTotalGb: Int = 512,
    val temperatureCelsius: Int = 42,
    val uptimeDays: Int = 15,
    val ollamaStatus: String = "Running (Demo)",
    val openCodeStatus: String = "Active (Demo)",
    val activeTmuxSessions: Int = 3
)

data class DemoSession(
    val name: String = "opencode",
    val windowCount: Int = 4,
    val lastAttached: String = "2 hours ago"
)

object DemoData {
    val server = DemoServer()
    val metrics = DemoMetrics()
    val sessions = listOf(
        DemoSession(),
        DemoSession(name = "backend", windowCount = 1, lastAttached = "Yesterday"),
        DemoSession(name = "monitor", windowCount = 2, lastAttached = "3 days ago")
    )
}
