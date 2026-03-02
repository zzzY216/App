package com.software.app.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // 基本信息
    val name: String,
    val teacher: String? = null,// 老师
    val location: String? = null,// 地点

    val dayOfWeek: Int,
    val startSection: Int,
    val sectionSpan: Int = 1, // 跨越几节课

    val startWeek: Int = 1,
    val endWeek: Int = 20,
    val weekType: Int = 0, // 0每周 1单周 2双周

    val color: Int,
    val remark: String? = null
)