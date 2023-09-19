package com.example.flixter



import android.os.TestLooperManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class FlixsterRecylerViewAdapter(
    private val models: List<MovieObject>,
    private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<FlixsterRecylerViewAdapter.MovieViewHolder>() {
    class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: MovieObject? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.Name) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(R.id.Description) as TextView
        val mMovieImg: ImageView = mView.findViewById<View>(R.id.imageView) as ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_flix,parent, false)
        return MovieViewHolder(view)
    }


    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = models[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.description

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.imageURL)
            .centerInside()
            .into(holder.mMovieImg)

        holder.mView.setOnClickListener {
            holder.mItem?. let { movie ->
                mListener?. onItemClick(movie)
            }
        }
    }
}