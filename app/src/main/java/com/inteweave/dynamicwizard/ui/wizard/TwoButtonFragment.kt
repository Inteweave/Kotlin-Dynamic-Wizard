package com.inteweave.dynamicwizard.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.R
import kotlinx.android.synthetic.main.fragment_two_button.*

private const val ARG_SCREEN = "screen"

/**
 * Template Fragment to show a snacks screen
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class TwoButtonFragment : Fragment() {
    private var presenter: TwoButtonFragmentPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_two_button, container, false)
    }

    /**
     * Set the contents of the screen based on its screen identifier
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // button is the Button id
        button1.setOnClickListener {
            presenter?.onButton1Pressed()
        }
        button2.setOnClickListener {
            presenter?.onButton2Pressed()
        }

        presenter?.let {
            with(it) {
                label.text = screenContents.description
                button1.text = screenContents.button1Title
                button2.text = screenContents.button2Title
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventListener) {
            arguments?.let {
                presenter = TwoButtonFragmentPresenter(it.getParcelable(ARG_SCREEN)!!, context)
            }
        } else {
            throw RuntimeException("$context must implement SnacksFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * a snacks screen fragment given the screen identifier.
         *
         * @param contents The screen contents to display
         * @return A new instance of fragment SnacksFragment.
         */
        @JvmStatic
        fun newInstance(contents: Map<String, String>) =
            TwoButtonFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SCREEN, TwoButtonFragmentPresenter.ScreenContents(contents))
                }
            }
    }
}
