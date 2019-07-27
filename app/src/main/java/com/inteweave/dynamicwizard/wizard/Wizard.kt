package com.inteweave.dynamicwizard.wizard

/**
 * The wizard class is the engine that drives the navigation
 * Given the screen navigation and the events it determines the identifier for the
 * next screen
 *
 * @param screenNavigation The list of screens, events and target screens
 * @param startScreen The identifier for the initial screen
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class Wizard<ScreenIdentifier, WizardEvent>(
    private val screenNavigation: List<ScreenNavigation<ScreenIdentifier, WizardEvent>>,
    val startScreen: ScreenIdentifier
) {
    /**
     * An event was raised on the current screen
     *
     * @param event The event that occurred on the current screen
     * @return The [ScreenIdentifier] for the event
     */
    fun onEvent(event: WizardEvent, currentScreen: ScreenIdentifier): ScreenIdentifier {
        val navigation = screenNavigation.filter { it.event == event && it.onScreen == currentScreen }
        when (navigation.size) {
            1 -> return navigation[0].navigateTo
            0 -> throw WizardError("No event found for screen $currentScreen")
            else -> throw WizardError("Multiple definitions for screen $currentScreen")
        }
    }
}
