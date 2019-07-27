package com.inteweave.dynamicwizard.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.R
import kotlinx.android.synthetic.main.wizard_host_fragment.*

/**
 * Very simple fragment to start the wizard
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class HomeFragment : Fragment() {

    interface Listener {
        fun onStartWizard()
    }

    private var listener: Listener? = null

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.wizard_host_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startWizard.setOnClickListener {
            listener?.onStartWizard()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement HomeFragment.Listener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
