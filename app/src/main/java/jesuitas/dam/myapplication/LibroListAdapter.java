package jesuitas.dam.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class LibroListAdapter extends RecyclerView.Adapter<LibroListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Libro> mLibros;
    private TextView mTitulo;
    private TextView mAutor;
    private TextView mAño;
    private TextView mLeido;
    LibroListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mLibros != null) {
            Libro current = mLibros.get(position);
            holder.wordItemView.setText(current.getTitulo());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Libros");
        }
    }

    void setLibros(List<Libro> Libros){
        mLibros = Libros;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mLibros != null)
            return mLibros.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView_titulo);
        }
    }

    public Libro getLibroAtPosition (int position) {
        return mLibros.get(position);
    }
}
