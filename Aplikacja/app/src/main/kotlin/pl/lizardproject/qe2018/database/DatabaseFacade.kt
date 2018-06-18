package pl.lizardproject.qe2018.database

import android.content.Context
import io.requery.Persistable
import io.requery.android.sqlitex.SqlitexDatabaseSource
import io.requery.rx.RxSupport
import io.requery.sql.EntityDataStore
import io.requery.sql.TableCreationMode
import pl.lizardproject.qe2018.BuildConfig
import pl.lizardproject.qe2018.database.model.DbItemEntity
import pl.lizardproject.qe2018.database.model.DbUserEntity
import pl.lizardproject.qe2018.database.model.Models
import rx.Single
import rx.lang.kotlin.toSingle
import rx.schedulers.Schedulers
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class DatabaseFacade(private val context: Context) {

    companion object {
        private val DATABASE_NAME = "quality_excites_database"
    }

    private val scheduler = Schedulers.from(Executors.newSingleThreadExecutor())

    val storage by lazy {
        // override onUpgrade to handle migrating to a new version
        val source = SqlitexDatabaseSource(context, Models.DEFAULT, DATABASE_NAME, 1)
        source.setLoggingEnabled(true)

        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE)
        }

        RxSupport.toReactiveStore(EntityDataStore<Persistable>(source.configuration))
    }

    fun saveItem(item: DbItemEntity): Single<DbItemEntity> {
        val single: Single<DbItemEntity>
        if (item.id > 0) {
            single = storage.update(item)
        } else {
            single = storage.insert(item)
        }

        return single.subscribeOn(scheduler)
    }

    fun loadItems(userId: Int) = storage.select(DbItemEntity::class.java)
            .where(DbItemEntity.USER_ID.eq(userId))
            .orderBy(DbItemEntity.CHECKED.asc())
            .get()
            .toSelfObservable()
            .subscribeOn(scheduler)

    fun deleteItem(item: DbItemEntity) {
        storage.delete(item)
                .subscribeOn(scheduler)
                .subscribe { }
    }

    fun loadItem(itemId: Int?) = storage.findByKey(DbItemEntity::class.java, itemId)
            .subscribeOn(scheduler)

    fun loadUser(username: String) = storage.select(DbUserEntity::class.java)
            .where(DbUserEntity.NAME.lower().eq(username))
            .get()
            .toSingle()
            .subscribeOn(scheduler)
            .map { it.firstOrNull() }

    fun loadUser(username: String, password: String) = storage.select(DbUserEntity::class.java)
            .where(DbUserEntity.NAME.lower().eq(username))
            .and(DbUserEntity.PASSWORD.eq(password))
            .get()
            .toSingle()
            .subscribeOn(scheduler)
            .map { it.firstOrNull() }

    fun saveUser(user: DbUserEntity) =
            storage.insert(user)
                    .subscribeOn(scheduler)

    fun drop() {
        storage.delete(DbUserEntity::class.java).get().call()
        storage.delete(DbItemEntity::class.java).get().call()
    }
}