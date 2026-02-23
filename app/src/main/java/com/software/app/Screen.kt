package com.software.app

import kotlinx.serialization.Serializable

@Serializable
object RouteMainAppEntry

@Serializable
object RouteFitAppEntry

@Serializable
object RouteBiliAppEntry

// Home
@Serializable
object RouteMainHome


// Fit
@Serializable
object RouteFitHome


// Bili
@Serializable
object RouteBiliLogin

@Serializable
object RouteBiliHome

@Serializable
object RouteBiliDynamic

@Serializable
object RouteBiliRecommend

@Serializable
object RouteBiliProfile

@Serializable
class RouteBiliDetail(
    val avid: String = "",
    val cid: String = "",
    val qn: Int = 64,
    val type: String = "mp4",
    val platform: String = "html5"
)

@Serializable
object RouteFitProfile

@Serializable
object RouteFitWorkout

@Serializable
object RouteFitNutrition

@Serializable
object RouteFitSettings

@Serializable
object RouteFitNote
