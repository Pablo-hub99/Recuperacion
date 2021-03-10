package jesuitas.dam.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LibroRepository {

    private LibrosDAO mlibroDAO;
    private LiveData<List<Libro>> mAllLibros;

    LibroRepository(Application application) {
        LibroRoomDatabase db = LibroRoomDatabase.getDatabase(application);
        mlibroDAO = db.LibroDao();
        mAllLibros = mlibroDAO.getAllLibros();
    }
    LiveData<List<Libro>> getmAllLibros() {
        return mAllLibros;
    }

    public void insert (Libro libro) {
        new insertAsyncTask(mlibroDAO).execute(libro);
    }

    private static class insertAsyncTask extends AsyncTask<Libro, Void, Void> {

        private LibrosDAO mAsyncTaskDao;

        insertAsyncTask(LibrosDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Libro... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllLibrosAsyncTask extends AsyncTask<Void, Void, Void> {
        private LibrosDAO mAsyncTaskDao;

        deleteAllLibrosAsyncTask(LibrosDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllLibrosAsyncTask(mlibroDAO).execute();
    }

    private static class deleteLibroAsyncTask extends AsyncTask<Libro, Void, Void> {
        private LibrosDAO mAsyncTaskDao;

        deleteLibroAsyncTask(LibrosDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Libro... params) {
            mAsyncTaskDao.deleteLibro(params[0]);
            return null;
        }
    }

    public void deletePelicula(Libro pelicula)  {
        new deleteLibroAsyncTask(mlibroDAO).execute(pelicula);
    }

}
