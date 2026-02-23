package com.software.app.data.local.mongo

import com.software.app.domain.model.Task
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TaskEntity : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var title: String = ""
    var isDone: Boolean = false
}

fun TaskEntity.toDomain(): Task {
    return Task(
        id = this._id.toHexString(),
        title = this.title,
        isDone = this.isDone
    )
}