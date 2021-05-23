package com.wxd.wanandroidmvp.app;

import com.wxd.wanandroidmvp.dao.LoginDao;
import com.wxd.wanandroidmvp.entity.Login;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * entities可关联多张表
 * exportSchema设置是否导出到文件夹
 */
@Database(entities = {Login.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DBName = "Wananzhuo.db";
    private volatile static AppDatabase instance;

    public static AppDatabase getInstance() {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = createDB();
                }
            }
        }
        return instance;
    }

    @NonNull
    private static AppDatabase createDB() {
        return Room.databaseBuilder(MyApplication.application, AppDatabase.class, DBName)
                //.fallbackToDestructiveMigration() //直接删除旧版数据
                .allowMainThreadQueries()   //可以在主线程操作
                //每次添加都需要更新，测试时可持续添加 发布版本时删除所有旧版本
                //.addMigrations(new MyMigration(1, 2), new MyMigration(2, 3)) 数据库升级操作
                .build();
    }

    private static class MyMigration extends Migration {

        MyMigration(int startVersion, int endVersion) {
            super(startVersion, endVersion);
        }

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            switch (startVersion) {
                case 1:
                    database.execSQL("ALTER TABLE User ADD COLUMN age integer");
                    break;
                case 2:
                    database.execSQL("CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)");
                    break;
            }
        }
    }

    public abstract LoginDao getLoginDao();

}
