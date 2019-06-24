package com.futureprocessing.qe.workshops.util;

import android.util.Log;
import com.futureprocessing.qe.workshops.database.AppDatabase;
import com.futureprocessing.qe.workshops.database.model.ItemEntity;
import com.futureprocessing.qe.workshops.database.model.UserEntity;
import com.futureprocessing.qe.workshops.model.Category;
import com.futureprocessing.qe.workshops.model.Priority;
import com.futureprocessing.qe.workshops.model.User;
import com.futureprocessing.qe.workshops.model.UserSession;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.util.Objects;


public class TestDataHelper {

    private final AppDatabase databaseFacade;

    public TestDataHelper(AppDatabase databaseFacade) {
        this.databaseFacade = databaseFacade;
    }

    public UserEntity loginUser(String username, String password, UserSession userSession) {
        UserEntity user = addUserToDatabase(username, password);
        userSession.start(new User(Objects.requireNonNull(user.getLogin()), Objects.requireNonNull(user.getPassword()), user.getId()));
        return user;
    }

    public UserEntity addUserToDatabase(String username, String password) {
        UserEntity user = new UserEntity(0,username, password);

        return Single.just(user)
                .doOnSuccess(
                        new Consumer<UserEntity>() {
                            @Override
                            public void accept(UserEntity userEntity) throws Exception {
                                databaseFacade.userDao().insert(userEntity);
                            }
                        }
                )
                .flatMap(
                        new Function<UserEntity, SingleSource<UserEntity>>() {
                            @Override
                            public SingleSource<UserEntity> apply(UserEntity userEntity) throws Exception {
                                return databaseFacade.userDao().findByName(Objects.requireNonNull(userEntity.getLogin()));
                            }
                        }
                )
                .blockingGet();
    }

    public ItemEntity addItemToDatabase(String name, Category category, Priority priority, boolean isChecked, UserEntity user) {
        ItemEntity item = new ItemEntity(0, name, isChecked, category, priority, user.getId());

        return Single.just(databaseFacade.itemDao().insert(item))
                .flatMap(
                        new Function<Long, SingleSource<ItemEntity>>() {
                            @Override
                            public SingleSource<ItemEntity> apply(Long id) throws Exception {
                                return databaseFacade.itemDao().getItem(id.intValue());
                            }
                        }
                )
                .blockingGet();
    }

    public void dropDatabase() {
        databaseFacade.clearAllTables();
        Log.d("DB_MSG", "Database cleared");
    }
}