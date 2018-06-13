package com.example.michaelurban.snake.OwnClass
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Line(var startX: Int, var startY: Int, var stopX: Int, var stopY: Int) : Serializable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt())
}


