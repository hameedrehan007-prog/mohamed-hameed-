package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.AppLanguage
import com.example.data.repository.VerifiedLegalKnowledgeBase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `verify app name and verified legal knowledge base integrity`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Nyaya Sahayak", appName)

        // Verify statutory helplines include 112, NALSA 15100, Cybercrime 1930, Consumer 1915, Childline 1098
        val helplineNumbers = VerifiedLegalKnowledgeBase.verifiedHelplines.map { it.number }
        assertTrue(helplineNumbers.contains("112"))
        assertTrue(helplineNumbers.contains("15100"))
        assertTrue(helplineNumbers.contains("1930"))
        assertTrue(helplineNumbers.contains("1915"))
        assertTrue(helplineNumbers.contains("1098"))

        // Verify directory strictly separates verified official authorities from demo entries
        val verifiedEntries = VerifiedLegalKnowledgeBase.verifiedDirectoryEntries.filter { it.isVerifiedOfficial }
        val demoEntries = VerifiedLegalKnowledgeBase.verifiedDirectoryEntries.filter { !it.isVerifiedOfficial }
        assertTrue(verifiedEntries.isNotEmpty())
        assertTrue(demoEntries.isNotEmpty())
        demoEntries.forEach { demo ->
            assertTrue(demo.name.get(AppLanguage.ENGLISH).contains("[DEMO ENTRY]"))
            assertFalse(demo.isVerifiedOfficial)
        }
    }
}
