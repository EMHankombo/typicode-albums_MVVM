package com.example.typicodealbums.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.typicodealbums.MyApp
import com.example.typicodealbums.R
import com.example.typicodealbums.common.Util
import com.example.typicodealbums.di.albums.DaggerAlbumsActivityComponent
import com.example.typicodealbums.di.albums.AlbumsActivityModule
import com.example.typicodealbums.viewmodel.AlbumsViewModel
import kotlinx.android.synthetic.main.activity_albums.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class AlbumsActivity : AppCompatActivity() {

    @Inject
    lateinit var albumsActivityViewModel: AlbumsViewModel

    private lateinit var albumsAdapter: AlbumsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setUpRecyclerView()

        initDagger()

        albumsActivityViewModel.getAlbums(Util.isConnected(this))

        setObservers()


    }

    private fun initDagger() {
        DaggerAlbumsActivityComponent
            .builder()
            .appComponent((application as MyApp).component())
            .albumsActivityModule(AlbumsActivityModule(this))
            .build()
            .inject(this)
    }

    private fun setObservers() {
        albumsActivityViewModel.albumsObservable().observe(this,
            Observer {
                albumsAdapter.albums.clear()
                albumsAdapter.albums.addAll(it)
                albumsAdapter.notifyDataSetChanged()
            })

        albumsActivityViewModel.errorObservable().observe(this,
            Observer {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            })

        albumsActivityViewModel.progressObservable().observe(this, Observer {
            if (it == true) {
                pb_home_activity.visibility = View.VISIBLE
            } else {
                pb_home_activity.visibility = View.GONE
            }
        })
    }



    private fun setUpRecyclerView() {
        albumsAdapter = AlbumsAdapter(mutableListOf())

        rv_albums.layoutManager = LinearLayoutManager(this)
        rv_albums.adapter = albumsAdapter

    }


}
