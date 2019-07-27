package com.inteweave.dynamicwizard.json.wizard.model

import com.inteweave.dynamicwizard.wizard.ScreenNavigation

data class WizardModel(
    val start: String,
    val screens: Array<Map<String, String>>,
    val navigation: List<ScreenNavigation<String, String>>
)
