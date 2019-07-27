package com.inteweave.dynamicwizard.ui.wizard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.inteweave.dynamicwizard.R
import kotlinx.android.synthetic.main.fragment_result.*
import kotlinx.android.synthetic.main.fragment_two_button.label

private const val ARG_SCREEN = "screen"

/**
 * Template Fragment to show a snacks screen
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class ResultFragment : Fragment() {
    private var presenter: ResultFragmentPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    /**
     * Set the contents of the screen based on its screen identifier
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // button is the Button id
        finishButton.setOnClickListener {
            presenter?.onFinishPressed()
        }

        presenter?.let {
            with(it) {
                label.text = screenContents.description
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is EventListener) {
            arguments?.let {
                presenter = ResultFragmentPresenter(it.getParcelable(ARG_SCREEN)!!, context)
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
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SCREEN, ResultFragmentPresenter.ScreenContents(contents))
                }
            }
    }
}
