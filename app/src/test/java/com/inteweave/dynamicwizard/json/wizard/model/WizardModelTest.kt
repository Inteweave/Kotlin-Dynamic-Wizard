package com.inteweave.dynamicwizard.json.wizard.model

import kotlinx.serialization.json.JSON
import org.junit.Assert.assertEquals
import org.junit.Test

class WizardModelTest {
    @Test
    fun model_Should_RepresentData() {
        val json = this::class.java.getResource("/snacks.json")?.readText(Charsets.UTF_8) ?: ""
        val wizardModel = JSON().parse(WizardModel.serializer(), json)
        assertEquals("select snack", wizardModel.start)
        assertEquals(7, wizardModel.screens.size)
        assertEquals(6, wizardModel.navigation.size)
    }
}
