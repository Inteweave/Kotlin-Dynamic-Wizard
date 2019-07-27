package com.inteweave.dynamicwizard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.inteweave.dynamicwizard.snacks.wizard.SnacksViewModel
import com.inteweave.dynamicwizard.ui.wizard.EventListener
import com.inteweave.dynamicwizard.ui.wizard.HomeFragment
import com.inteweave.dynamicwizard.wizard.Wizard

/**
 * Main activity is responsible for displaying the fragments of the wizard.
 * The view model contains the definition of the wizard
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class WizardActivity : AppCompatActivity(),
    EventListener,
    HomeFragment.Listener {

    override var isEnabled: Boolean
        get() = wizard != null
        set(_) {}

    private lateinit var model: SnacksViewModel
    private var wizard: Wizard<String, String>? = null
    private var homeFragment: HomeFragment? = null

    /**
     * Create or attach the the view model and instantiate the home fragment
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wizard_activity)
        model = ViewModelProviders.of(this)[SnacksViewModel::class.java]
        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, homeFragment!!)
                .commitNow()
        }

        model.wizard.observe(this, Observer<Wizard<String, String>> {
            wizard = it
            homeFragment?.isEnabled = true
        })
    }

    /**
     * Perhaps in a production app the event would be handled by the View Model, which is
     * really the wizard controller.
     * I would also expect to see factory methods to create the fragment to display.
     */
    override fun onEvent(event: String, currentScreen: String) {
        if (event == "finish") {
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } else {
            val screen = wizard?.onEvent(event, currentScreen) ?: ""
            showScreen(model.fragmentFactory.fragmentForScreen(screen))
        }
    }

    /**
     * The user has selected to start the wizard. Show the initial screen.
     */
    override fun onStartWizard() {
        showScreen(model.fragmentFactory.fragmentForScreen(wizard?.startScreen ?: ""))
    }

    /**
     * Show a new wizard fragment with animation. A standard sliding animation is used
     * to indicate forward or back navigation.
     */
    private fun showScreen(fragment: Fragment?) {
        homeFragment = null
        fragment?.let {
            supportFragmentManager.beginTransaction().setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
