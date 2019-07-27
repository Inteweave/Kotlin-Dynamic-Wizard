package com.inteweave.dynamicwizard.ui.wizard

interface EventListener {
    fun onEvent(event: String, currentScreen: String)
}
