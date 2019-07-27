package com.inteweave.dynamicwizard.snacks.wizard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.inteweave.dynamicwizard.json.wizard.model.WizardModel
import com.inteweave.dynamicwizard.wizard.Wizard

/**
 * The view model contains the definition of the wizard
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class SnacksViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * The wizard is loaded in a coroutine from the JSON file in the assets
     *
     * It could just as easily be loaded from a web service or CMS store
     */
    val wizard: LiveData<Wizard<String, String>> = liveData {
        val json = application.assets.open("snacks.json").bufferedReader().use { it.readText() }
        val wizardModel = Gson().fromJson(json, WizardModel::class.java)
        val screenMap = HashMap<String, Map<String, String>>()
        for (screen in wizardModel.screens) {
            screenMap[screen["id"] ?: ""] = screen
        }
        fragmentFactory = FragmentFactory(screenMap)
        emit(Wizard(wizardModel.navigation, wizardModel.start))
    }

    lateinit var fragmentFactory: FragmentFactory
}
