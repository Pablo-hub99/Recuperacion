package jesuitas.dam.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LibroViewModel extends AndroidViewModel {

    private LibroRepository mRepository;
    private LiveData<List<Libro>> mAllLibros;

    public LibroViewModel(@NonNull Application application) {
        super(application);
        mRepository= new LibroRepository(application);
        mAllLibros= mRepository.getmAllLibros();
    }

    LiveData<List<Libro>> getAllLibros() {
        return mAllLibros;
    }

    public void insert(Libro libro) {   mRepository.insert(libro);
    }

    public void deleteAll() {mRepository.deleteAll();}

    public void deleteLibro(Libro libro) {mRepository.deleteLibro(libro);}
}
