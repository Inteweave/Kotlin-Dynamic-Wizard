package com.inteweave.dynamicwizard.ui.wizard

/**
 * Interface for the wizard events.
 * Further decoupling could be achieved by using an event bus instead of passing the listener.
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
interface EventListener {
    fun onEvent(event: String, currentScreen: String)
}
