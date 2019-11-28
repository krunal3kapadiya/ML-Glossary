package app.krunal3kapadiya.mlglossary.data.local.db

import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MLDefinitionsRepository {
    lateinit var mlDefinitionDao: DefinitionDao
    @Inject
    fun MLDefinitionsRepository(mlDefinitionDao: DefinitionDao) {
        this.mlDefinitionDao = mlDefinitionDao
    }

    fun insert(mldefinitions: Mldefinitions) {
        mlDefinitionDao.insert(mldefinitions)
    }

    fun insertAll(mldefinitionArray: ArrayList<Mldefinitions>) {
        mlDefinitionDao.insertAll(mldefinitionArray)
    }

    fun getAll(): Single<List<Mldefinitions?>?>? {
        return mlDefinitionDao.loadAll()
    }

    fun remove(mldefinitions: Mldefinitions) {
        mlDefinitionDao.delete(mldefinitions)
    }
}