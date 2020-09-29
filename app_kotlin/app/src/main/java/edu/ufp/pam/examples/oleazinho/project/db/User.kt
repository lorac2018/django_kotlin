package edu.ufp.pam.examples.oleazinho.project.db

import android.graphics.Bitmap
import androidx.room.*


@Entity(
    tableName = "users",
    indices = arrayOf(Index(value = ["customername"]))
)

data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid") var userId: Int = 0,
    @ColumnInfo(name = "username") var Name: String? = "",
    @ColumnInfo(name = "password") var Password: String? = "",
    @ColumnInfo(name = "points") var Points: String? = "",
    @ColumnInfo(name = "address") var Address: String? = ""
    //@ColumnInfo(name = "customercity") var customerCity: String? = ""
    //@Ignore var picture: Bitmap? = null
) {
    constructor() : this(0, "", "", "", "")
}