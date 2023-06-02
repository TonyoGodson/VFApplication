package com.godston.vfapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(
    val capital: String,
    val currency: String,
    val flag: String,
    val language: String,
    val name: String,
    val region: String
) : Parcelable