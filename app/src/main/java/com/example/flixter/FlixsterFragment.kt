package com.example.flixter


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

class FlixsterFragment : Fragment(), OnListFragmentInteractionListener{


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragrament_list, container, false)
        val progressBar = view.findViewById<View>(R.id.ProgressBar) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.List) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }


    private fun  updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        client[
            "https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1",
            params,
            object: JsonHttpResponseHandler()

            {
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String,
                    throwable: Throwable?
                ) {
                    progressBar.hide()

                    throwable?.message?.let {
                        Log.e("FlixsterFragment", response)
                    }
                }


                override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                    progressBar.hide()

                    val resultJSON: String = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayMovieType = object: TypeToken<List<MovieObject>>(){}.type
                    val models: List<MovieObject> = gson.fromJson(resultJSON, arrayMovieType)
                    recyclerView.adapter = FlixsterRecylerViewAdapter(models, this@FlixsterFragment)

                }
            }
        ]


    }
    override fun onItemClick(item: MovieObject) {
        Toast.makeText(context, "Now showing " + item.title, Toast.LENGTH_SHORT).show()
    }


}