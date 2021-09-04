package com.neowise.androidlessions.itmath.model

import android.os.Parcel
import android.os.Parcelable

interface UserResult : Parcelable{
    val skipped: Int
    val wrong: Int
    val rights: Int
}

class MutableUserResult(
    private var _skipped: Int,
    private var _wrong: Int,
    private var _rights: Int
) : UserResult  {

    override val skipped get() = _skipped
    override val wrong get() = _wrong
    override val rights get() = _rights

    fun addSkipped() {  _skipped++  }

    fun addRight() {  _rights++  }

    fun addWrong() {  _wrong++  }

    // Parcelable implementation
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(skipped)
        parcel.writeInt(wrong)
        parcel.writeInt(rights)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserResult> {
        override fun createFromParcel(parcel: Parcel): UserResult {
            return MutableUserResult(parcel)
        }

        override fun newArray(size: Int): Array<UserResult?> {
            return arrayOfNulls(size)
        }
    }
}