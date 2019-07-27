package com.inteweave.dynamicwizard.json.wizard.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class WizardModelTest {
    @Test
    fun model_Should_RepresentData() {
        val json = this::class.java.getResource("/snacks.json")?.readText(Charsets.UTF_8) ?: ""
        val wizardModel = Gson().fromJson(json, WizardModel::class.java)
        assertNotNull(wizardModel)
        assertEquals("select snack", wizardModel.start)
        assertEquals(7, wizardModel.screens.size)
        assertEquals(6, wizardModel.navigation.size)
    }
}
