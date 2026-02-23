package com.software.app.domain.model

data class FitPerson(
    val id: String,
    val name: String,
    val age: Int,
    val gender: Int, // 0未知 1男 2女
    val birthday: Long = 0L,

    //身体数据
    val height: Double,
    val weight: Double,
    val targetWeight: Double,
    val bodyFat: Double, // 体脂率

    //偏好设置
    val fitnessGoal: Int,// 0未知 1增肌 2减脂 3塑形
    val activityLevel: Int, // 0未知 1久坐 2轻微 3活跃

    //系统设置
    val createAt: Long = System.currentTimeMillis(),
    val locale: String
)