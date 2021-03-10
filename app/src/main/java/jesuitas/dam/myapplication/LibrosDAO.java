package jesuitas.dam.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LibrosDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Libro libro);

    @Query("DELETE FROM Libro_table")
    void deleteAll();

    @Query("SELECT * from Libro_table")
    LiveData<List<Libro>> getAllLibros();

    @Delete
    void deleteLibro(Libro libro);
}
