package com.pacmacmobile.relay.core.preview

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DemoModelsTest {

    @Test
    fun `DemoServer default values are correct`() {
        val server = DemoServer()
        assertEquals("My Server (Demo)", server.name)
        assertEquals("Offline (Demo)", server.status)
        assertEquals("Never", server.lastConnected)
    }

    @Test
    fun `DemoData sessions contain correct number of windows`() {
        val sessions = DemoData.sessions
        assertTrue("Should have exactly 3 demo sessions", sessions.size == 3)
        
        val totalWindows = sessions.sumOf { it.windowCount }
        assertEquals("Total windows should be 7", 7, totalWindows)
    }
}
