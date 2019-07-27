package com.inteweave.dynamicwizard.snacks.wizard

import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.ui.wizard.ResultFragment
import com.inteweave.dynamicwizard.ui.wizard.TwoButtonFragment

class FragmentFactory(private val screenMap: Map<String, Map<String, String>>) {
    fun fragmentForScreen(screenId: String): Fragment? {
        val screen = screenMap[screenId]
        screen?.let {
            val template = screen["template"]
            return fragmentForTemplate(template, screen)
        }

        return null
    }

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
