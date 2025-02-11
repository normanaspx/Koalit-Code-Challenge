package com.ferhatozcelik.codechallenge.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Long = 0,
    val email: String,
    val password: String
) : Parcelable
