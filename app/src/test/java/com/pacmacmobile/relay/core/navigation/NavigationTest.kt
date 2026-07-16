package com.pacmacmobile.relay.core.navigation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NavigationTest {

    @Test
    fun `bottomNavItems contains correct destinations`() {
        val expectedRoutes = setOf("home", "terminal", "server", "settings")
        val actualRoutes = bottomNavItems.map { it.route }.toSet()
        
        assertEquals("Should contain exact required routes", expectedRoutes, actualRoutes)
    }

    @Test
    fun `navigation screen titles are properly capitalized`() {
        bottomNavItems.forEach { screen ->
            assertTrue("Title should start with uppercase: ${screen.title}", 
                screen.title.first().isUpperCase())
        }
    }
}
