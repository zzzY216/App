package com.software.app.data.local.mongo

import com.software.app.domain.model.FitPerson
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class FitPersonEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int = 0
    var gender: Int = 0 // 0未知 1男 2女
    var birthday: Long = 0L

    //身体数据
    var height: Double = 0.0
    var weight: Double = 0.0
    var targetWeight: Double = 0.0
    var bodyFat: Double = 0.0 // 体脂率

    //偏好设置
    var fitnessGoal: Int = 0 // 0未知 1增肌 2减脂 3塑形
    var activityLevel: Int = 0 // 0未知 1久坐 2轻微 3活跃

    //系统设置
    var createAt: Long = System.currentTimeMillis()
    var locale: String = ""
}

fun FitPersonEntity.toDomain(): FitPerson {
    return FitPerson(
        id = _id.toHexString(),
        name = name,
        age = age,
        gender = gender,
        birthday = birthday,
        height = height,
        weight = weight,
        targetWeight = targetWeight,
        bodyFat = bodyFat,
        fitnessGoal = fitnessGoal,
        activityLevel = activityLevel,
        createAt = createAt,
        locale = locale
    )
}