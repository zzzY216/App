package com.software.app.data.repository

import com.software.app.data.local.mongo.TaskEntity
import com.software.app.data.local.mongo.toDomain
import com.software.app.domain.model.Task
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import javax.inject.Singleton

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun addTask(name: String)
    suspend fun updateTaskTitle(id: String, newTitle: String)
    suspend fun updateTaskStatus(id: String, isDone: Boolean)
}

@Singleton
class TaskRepositoryImpl(
    private val realm: Realm
) : TaskRepository {
    override fun getAllTasks(): Flow<List<Task>> {
        return realm.query<TaskEntity>().asFlow()
            .map { it.list.map { entity -> entity.toDomain() } }
    }

    override suspend fun addTask(name: String) {
        realm.write {
            copyToRealm(TaskEntity().apply {
                title = name
            })
        }
    }

    override suspend fun updateTaskTitle(id: String, newTitle: String) {
        realm.write {
            try {
                val objectId = ObjectId(id)
                val queryTask = query<TaskEntity>("_id == $0", objectId).first().find()
                queryTask?.title = newTitle
            } catch (e: IllegalArgumentException) {
                println("无效的 ID 格式: $id")
            }
        }
    }

    override suspend fun updateTaskStatus(id: String, isDone: Boolean) {
        realm.write {
            try {
                val taskObjectId = ObjectId(id)
                val queryTask = query<TaskEntity>("_id == $0", taskObjectId).first().find()

                queryTask?.let {
                    it.isDone = isDone
                }
            } catch (e: IllegalArgumentException) {
                println("无效的 ID 格式: $id")
            }
        }
    }
}