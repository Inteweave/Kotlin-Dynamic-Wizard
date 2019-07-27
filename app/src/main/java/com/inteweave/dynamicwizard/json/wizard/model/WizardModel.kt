package com.inteweave.dynamicwizard.json.wizard.model

import com.inteweave.dynamicwizard.wizard.ScreenNavigation

/**
 * Data class for GSON to decode the snacks file to.
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
@Suppress("ArrayInDataClass")
data class WizardModel(
    val start: String,
    val screens: Array<Map<String, String>>,
    val navigation: List<ScreenNavigation<String, String>>
)
