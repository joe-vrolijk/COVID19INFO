package com.team1.covid19info.ui.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.team1.covid19info.R
import com.team1.covid19info.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_covid_data.*
import kotlinx.android.synthetic.main.component_heatmap.*
import android.R.string.no
import android.location.Geocoder
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class StatisticsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private val heatMapData = MutableLiveData<List<WeightedLatLng>>()

    companion object {
        fun newInstance() = StatisticsFragment()
    }

    private lateinit var viewModel: StatisticsViewModel
    private var selectedCountry = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = requireView().findViewById(R.id.mvHeatmap)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = StatisticsViewModel(requireContext())
        viewModel.globalStatistics.observe(viewLifecycleOwner, Observer {
            tvNewConfirmed.text = it.newConfirmed.toString()
            tvTotalConfirmed.text = it.totalConfirmed.toString()
            tvNewDeaths.text = it.newDeaths.toString()
            tvTotalDeaths.text = it.totalDeaths.toString()
            tvNewRecovered.text = it.newRecovered.toString()
            tvTotalRecovered.text = it.totalRecovered.toString()
        })
        viewModel.countryStatistics.observe(viewLifecycleOwner, Observer {
            val scope = CoroutineScope(Dispatchers.Default)

            scope.launch {
                val data = ArrayList<WeightedLatLng>()
                for (country in it) {
                    val geoCoder = Geocoder(context, Locale.getDefault())
                    val geoDataList = geoCoder.getFromLocationName(country.country, 1)
                    if (geoDataList.isNotEmpty())
                    {
                        val geoData = geoDataList[0]
                        data.add(WeightedLatLng(LatLng(geoData.latitude, geoData.longitude), country.totalConfirmed.toDouble()))
                    }
                }
                heatMapData.postValue(data)
            }
        })
        viewModel.getCovidData()
        selectedCountry.observe(viewLifecycleOwner, Observer {
            updateTitle()
        })
        selectedCountry.postValue("")
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
        heatMapData.observe(viewLifecycleOwner, Observer {
            val heatMapProvider = HeatmapTileProvider.Builder()
                .weightedData(it) // load our weighted data
                .radius(20) // optional, in pixels, can be anything between 20 and 50
                .maxIntensity(1000.0) // set the maximum intensity
                .build()

            googleMap.addTileOverlay(TileOverlayOptions().tileProvider(heatMapProvider))
            clLoadingHeatmap.visibility = View.INVISIBLE
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        var mapViewBundle = outState.getBundle(getString(R.string.google_maps_key))
        if (mapViewBundle == null)
        {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    private fun updateTitle()
    {
        (requireActivity() as MainActivity).setToolbarTitle("${getString(R.string.Bottom_Nav_Statistics)} ${getTitleAppendix()}")
    }

    private fun getTitleAppendix() : String
    {
        return if (selectedCountry.value!!.isNotEmpty()) selectedCountry.value!! else getString(R.string.DefaultStatisticsTitleAppendix)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map!!
        MapsInitializer.initialize(activity)
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStart() {
        mapView.onStart()
        super.onStart()
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }
}
