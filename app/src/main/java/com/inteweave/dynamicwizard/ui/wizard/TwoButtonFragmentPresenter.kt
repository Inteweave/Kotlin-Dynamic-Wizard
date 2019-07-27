package com.inteweave.dynamicwizard.ui.wizard

import android.os.Parcel
import android.os.Parcelable

/**
 * The presenter for the two button fragment maintains the actual contents of each screen and the events it can raise.
 *
 * It translates user action into the actual event.
 *
 * @author W M Milward
 * Copyright © 2019 Inteweave. All rights reserved.
 */
class TwoButtonFragmentPresenter(
    val screenContents: ScreenContents,
    private val listener: EventListener
) {
    /**
     * Maintain the contents of the screen
     */
    data class ScreenContents(
        val identifier: String,
        val description: String,
        val button1Title: String,
        val button2Title: String
    ) : Parcelable {
        /**
         * Construct from a parcel
         */
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!
        )

        /**
         * Construct the contents from the map in the JSON file.
         * This template expects the map to have keys **description**, **button1** and **button2**
         *
         * @param contents the map of the screen contents
         */
        constructor(contents: Map<String, String>) :
                this(
                    contents["id"] ?: "",
                    contents["description"] ?: "Not defined in JSON",
                    contents["button1"] ?: "Not defined in JSON",
                    contents["button2"] ?: "Not defined in JSON"
                )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(identifier)
            parcel.writeString(description)
            parcel.writeString(button1Title)
            parcel.writeString(button2Title)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<ScreenContents> {
            override fun createFromParcel(parcel: Parcel): ScreenContents {
                return ScreenContents(parcel)
            }

            override fun newArray(size: Int): Array<ScreenContents?> {
                return arrayOfNulls(size)
            }
        }
    }

    fun onButton1Pressed() {
        listener.onEvent("button1", screenContents.identifier)
    }

    fun onButton2Pressed() {
        listener.onEvent("button2", screenContents.identifier)
    }
}
