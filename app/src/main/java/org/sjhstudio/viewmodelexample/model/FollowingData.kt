package org.sjhstudio.viewmodelexample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FollowingData(
    var id: String = "",
    var name: String = "",
    var unFollowing: Boolean = false
): Parcelable