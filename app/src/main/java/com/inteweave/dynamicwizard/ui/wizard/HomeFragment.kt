package com.inteweave.dynamicwizard.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.R
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * Very simple fragment to start the wizard
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class HomeFragment : Fragment() {

    /**
     * Pretty standard pattern for a fragment interaction listener;
     * set in onAttach and cleared in onDetach
     */
    interface Listener {
        fun onStartWizard()
        var isEnabled: Boolean
    }

    var isEnabled: Boolean
        get() = startWizard.isEnabled
        set(value) {
            startWizard.isEnabled = value
        }

    private var listener: Listener? = null

    companion object {
        /**
         * Factory method to create an instance of this fragment
         */
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startWizard.setOnClickListener {
            listener?.onStartWizard()
        }

        if (savedInstanceState == null) {
            startWizard.isEnabled = listener?.isEnabled ?: false
        }
    }

    /**
     * Get a reference to the fragment interaction listener
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement HomeFragment.Listener")
        }
    }

    /**
     * Clear the reference to the fragment interaction listener
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
