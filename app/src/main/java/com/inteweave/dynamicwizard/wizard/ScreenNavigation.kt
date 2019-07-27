package com.inteweave.dynamicwizard.wizard

/**
 * This is the actual navigation
 * There is one of these created for each possible event for each screen
 * This can follow the UX flow; requirement is that each screen has a name
 *
 * @param ScreenIdentifier The type of the screen identifier; normally an enum
 * @param WizardEvent The type of the event raised; normally an enum
 *
 * @param onScreen The screen that the user is currently looking at
 * @param event The event that occurred
 * @param navigateTo The screen the user is going to
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
data class ScreenNavigation<ScreenIdentifier, WizardEvent>(
    val onScreen: ScreenIdentifier,
    val event: WizardEvent,
    val navigateTo: ScreenIdentifier
)
