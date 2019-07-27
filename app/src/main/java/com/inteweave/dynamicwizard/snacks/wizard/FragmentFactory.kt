package com.inteweave.dynamicwizard.snacks.wizard

import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.ui.wizard.ResultFragment
import com.inteweave.dynamicwizard.ui.wizard.TwoButtonFragment

/**
 * Factory to create a fragment based on the template name.
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class FragmentFactory(private val screenMap: Map<String, Map<String, String>>) {
    /**
     * Create the fragment for the specified screen
     *
     * @param screenId The screen identifier
     * @return The fragment or null on error
     */
    fun fragmentForScreen(screenId: String): Fragment? {
        val screen = screenMap[screenId]
        screen?.let {
            val template = screen["template"]
            return fragmentForTemplate(template, screen)
        }

        return null
    }

    /**
     * Create the fragment from the specified template name
     *
     * @param template The template name
     * @param contents The template-specific screen contents to fill the template
     * @return The fragment or null on error
     */
    private fun fragmentForTemplate(template: String?, contents: Map<String, String>): Fragment? {
        template?.let {
            return when (template) {
                "two-button" -> TwoButtonFragment.newInstance(contents)
                "result" -> ResultFragment.newInstance(contents)
                else -> null
            }
        }

        return null
    }
}
