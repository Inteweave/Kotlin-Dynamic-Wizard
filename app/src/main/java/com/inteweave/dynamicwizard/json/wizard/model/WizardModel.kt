package com.inteweave.dynamicwizard.json.wizard.model

import com.inteweave.dynamicwizard.wizard.ScreenNavigation
import kotlinx.serialization.Serializable

/**
 * Data class for the snacks file to be deserialized to
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
@Serializable
@Suppress("ArrayInDataClass")
data class WizardModel(
    val start: String,
    val screens: Array<Map<String, String>>,
    val navigation: List<ScreenNavigation<String, String>>
)
