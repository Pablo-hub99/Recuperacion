package jesuitas.dam.myapplication;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Libro_table")
public class Libro {

    @PrimaryKey
    @NonNull
    private String titulo;
    private String Autor;
    private String año;
    private Boolean leido;

    public Libro(String titulo, String año,  Boolean leido, String Autor){
        this.titulo=titulo;
        this.año=año;
        this.leido=leido;
        this.Autor = Autor;
    }


    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return Autor;
    }

    public String getAño() {
        return año;
    }

    public boolean getleido(){return  leido;}
}


