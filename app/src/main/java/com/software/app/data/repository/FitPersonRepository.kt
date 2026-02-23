package com.software.app.data.repository

import com.software.app.data.local.mongo.FitPersonEntity
import com.software.app.data.local.mongo.toDomain
import com.software.app.domain.model.FitPerson
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface FitPersonRepository {
    fun getFitPerson(): Flow<FitPerson?>
    suspend fun updateFitPersonName(name: String)
    suspend fun updateFitPersonAge(age: Int)
    suspend fun updateFitPersonGender(gender: Int)
    suspend fun updateFitPersonBirthday(birthday: Long)
    suspend fun updateFitPersonHeight(height: Double)
    suspend fun updateFitPersonWeight(weight: Double)
    suspend fun updateFitPersonTargetWeight(targetWeight: Double)
    suspend fun updateFitPersonBodyFat(bodyFat: Double)
    suspend fun updateFitPersonFitnessGoal(fitnessGoal: Int)
    suspend fun updateFitPersonActivityLevel(activityLevel: Int)
    suspend fun updateFitPersonLocale(locale: String)
    suspend fun updateFitPerson(fitPerson: FitPerson)
}


class FitPersonRepositoryImpl(
    private val realm: Realm
) : FitPersonRepository {
    override fun getFitPerson(): Flow<FitPerson?> {
        return realm.query<FitPersonEntity>().asFlow().map { change ->
            change.list.firstOrNull()?.toDomain()
        }
    }

    private suspend fun updateField(block: (FitPersonEntity) -> Unit) {
        realm.write {
            val person = query<FitPersonEntity>().first().find()
            if (person != null) {
                block(person)
            } else {
                val newPerson = FitPersonEntity()
                block(newPerson)
                copyToRealm(newPerson)
            }
        }
    }

    override suspend fun updateFitPersonName(name: String) {
        updateField {
            it.name = name
        }
    }

    override suspend fun updateFitPersonAge(age: Int) {
        updateField {
            it.age = age
        }
    }

    override suspend fun updateFitPersonGender(gender: Int) {
        updateField {
            it.gender = gender
        }
    }

    override suspend fun updateFitPersonBirthday(birthday: Long) {
        updateField {
            it.birthday = birthday
        }
    }

    override suspend fun updateFitPersonHeight(height: Double) {
        updateField {
            it.height = height
        }
    }

    override suspend fun updateFitPersonWeight(weight: Double) {
        updateField {
            it.weight = weight
        }
    }

    override suspend fun updateFitPersonTargetWeight(targetWeight: Double) {
        updateField {
            it.targetWeight = targetWeight
        }
    }

    override suspend fun updateFitPersonBodyFat(bodyFat: Double) {
        updateField {
            it.bodyFat = bodyFat
        }
    }

    override suspend fun updateFitPersonFitnessGoal(fitnessGoal: Int) {
        updateField {
            it.fitnessGoal = fitnessGoal
        }
    }

    override suspend fun updateFitPersonActivityLevel(activityLevel: Int) {
        updateField {
            it.activityLevel = activityLevel
        }
    }

    override suspend fun updateFitPersonLocale(locale: String) {
        updateField {
            it.locale = locale
        }
    }

    override suspend fun updateFitPerson(fitPerson: FitPerson) {
        updateField {
            it.name = fitPerson.name
            it.age = fitPerson.age
            it.gender = fitPerson.gender
            it.birthday = fitPerson.birthday
            it.height = fitPerson.height
            it.weight = fitPerson.weight
            it.targetWeight = fitPerson.targetWeight
            it.bodyFat = fitPerson.bodyFat
            it.fitnessGoal = fitPerson.fitnessGoal
            it.activityLevel = fitPerson.activityLevel
            it.locale = fitPerson.locale
        }
    }
}