package jesuitas.dam.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Libro.class}, version = 1, exportSchema = false)
public abstract class LibroRoomDatabase extends RoomDatabase {
    public abstract LibrosDAO libroDao();

    private static LibroRoomDatabase INSTANCE;

    public static LibroRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LibroRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LibroRoomDatabase.class, "libro_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    
                }
            };

}
