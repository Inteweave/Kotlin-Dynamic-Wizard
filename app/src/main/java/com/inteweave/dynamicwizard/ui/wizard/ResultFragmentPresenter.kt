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
class ResultFragmentPresenter(
    val screenContents: ScreenContents,
    private val listener: EventListener
) {
    /**
     * Maintain the contents of the screen
     */
    data class ScreenContents(
        val identifier: String,
        val description: String
    ) : Parcelable {
        /**
         * Construct from a parcel
         */
        constructor(parcel: Parcel) : this(
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
                    contents["description"] ?: "Not defined in JSON"
                )

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(identifier)
            parcel.writeString(description)
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

    fun onFinishPressed() {
        listener.onEvent("finish", screenContents.identifier)
    }
}
