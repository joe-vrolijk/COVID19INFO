package com.team1.covid19info.ui.statistics

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.team1.covid19info.R
import com.team1.covid19info.ui.MainActivity
import kotlinx.android.synthetic.main.component_covid_data.*
import kotlinx.android.synthetic.main.component_heatmap.*
import android.location.Geocoder
import android.os.Build
import android.os.Handler
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartModel
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartType
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AAChartView
import com.aachartmodel.aainfographics.AAInfographicsLib.AAChartCreator.AASeriesElement
import com.aachartmodel.aainfographics.AAInfographicsLib.AAOptionsModel.AADataLabels
import com.anychart.APIlib
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import com.team1.covid19info.model.StatisticsCountry
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.google.android.gms.maps.model.TileOverlay
import com.team1.covid19info.model.StatisticsCountryDateData
import ir.farshid_roohi.linegraph.ChartEntity
import kotlinx.android.synthetic.main.component_piechart.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter


class StatisticsFragment : Fragment(), OnMapReadyCallback {

    val heatmapCoordinates = hashMapOf(
        "Afghanistan" to LatLng(33.93911, 67.709953),
        "Albania" to LatLng(41.153332, 20.168331),
        "Algeria" to LatLng(28.033886, 1.6596259999999998),
        "Andorra" to LatLng(42.506285, 1.521801),
        "Angola" to LatLng(-11.202691999999999, 17.873887),
        "Antigua and Barbuda" to LatLng(17.060816, -61.796428),
        "Argentina" to LatLng(-38.416097, -63.616671999999994),
        "Armenia" to LatLng(40.069099, 45.038189),
        "Australia" to LatLng(-25.274397999999998, 133.775136),
        "Austria" to LatLng(47.516231, 14.550072),
        "Azerbaijan" to LatLng(40.143105, 47.576927),
        "Bahamas" to LatLng(25.034280000000003, -77.39627999999999),
        "Bahrain" to LatLng(26.066699999999997, 50.5577),
        "Bangladesh" to LatLng(23.684994, 90.356331),
        "Barbados" to LatLng(13.193887, -59.543198),
        "Belarus" to LatLng(53.709807, 27.953388999999998),
        "Belgium" to LatLng(50.503887, 4.469936),
        "Belize" to LatLng(17.189877, -88.49765),
        "Benin" to LatLng(9.30769, 2.3158339999999997),
        "Bhutan" to LatLng(27.514162, 90.433601),
        "Bolivia" to LatLng(-16.290153999999998, -63.588652999999994),
        "Bosnia and Herzegovina" to LatLng(43.915886, 17.679076),
        "Botswana" to LatLng(-22.328474, 24.684866),
        "Brazil" to LatLng(-14.235004, -51.92528),
        "Brunei Darussalam" to LatLng(4.535277, 114.72766899999998),
        "Bulgaria" to LatLng(42.733883, 25.48583),
        "Burkina Faso" to LatLng(12.238332999999999, -1.561593),
        "Burundi" to LatLng(-3.373056, 29.918886),
        "Cambodia" to LatLng(12.565679, 104.990963),
        "Cameroon" to LatLng(7.369721999999999, 12.354721999999999),
        "Canada" to LatLng(56.130365999999995, -106.34677099999999),
        "Cape Verde" to LatLng(16.5388, -23.041800000000002),
        "Central African Republic" to LatLng(6.611110999999999, 20.939443999999998),
        "Chad" to LatLng(15.454165999999999, 18.732207),
        "Chile" to LatLng(-35.675146999999996, -71.542969),
        "China" to LatLng(35.86166, 104.195397),
        "Colombia" to LatLng(4.570868, -74.297333),
        "Comoros" to LatLng(-11.645500000000002, 43.3333),
        "Congo (Brazzaville)" to LatLng(-4.2633597, 15.242885299999998),
        "Congo (Kinshasa)" to LatLng(-4.038333, 21.758664),
        "Costa Rica" to LatLng(9.748916999999999, -83.753428),
        "Croatia" to LatLng(45.1, 15.2000001),
        "Cuba" to LatLng(21.521756999999997, -77.781167),
        "Cyprus" to LatLng(35.126413, 33.429859),
        "Czech Republic" to LatLng(49.817491999999994, 15.472961999999999),
        "Côte d'Ivoire" to LatLng(7.539988999999999, -5.547079999999999),
        "Denmark" to LatLng(56.26392, 9.501785),
        "Djibouti" to LatLng(11.825137999999999, 42.590275),
        "Dominica" to LatLng(15.414999000000002, -61.370976000000006),
        "Dominican Republic" to LatLng(18.735692999999998, -70.162651),
        "Ecuador" to LatLng(-1.831239, -78.18340599999999),
        "Egypt" to LatLng(26.820553, 30.802498000000003),
        "El Salvador" to LatLng(13.794184999999999, -88.89653),
        "Equatorial Guinea" to LatLng(1.650801, 10.267895),
        "Eritrea" to LatLng(15.179383999999997, 39.782334),
        "Estonia" to LatLng(58.595272, 25.013607099999998),
        "Ethiopia" to LatLng(9.145000000000001, 40.489672999999996),
        "Fiji" to LatLng(-17.713371, 178.06503200000003),
        "Finland" to LatLng(61.92410999999999, 25.748151099999998),
        "France" to LatLng(46.227638, 2.213749),
        "Gabon" to LatLng(-0.803689, 11.609444),
        "Gambia" to LatLng(13.443182, -15.310139000000001),
        "Georgia" to LatLng(32.1656221, -82.9000751),
        "Germany" to LatLng(51.165690999999995, 10.451526),
        "Ghana" to LatLng(7.946527, -1.023194),
        "Greece" to LatLng(39.074208, 21.824312),
        "Grenada" to LatLng(12.1165, -61.678999999999995),
        "Guatemala" to LatLng(15.783470999999999, -90.23075899999999),
        "Guinea" to LatLng(9.945587, -9.696645),
        "Guinea-Bissau" to LatLng(11.803749, -15.180412999999998),
        "Guyana" to LatLng(4.860416, -58.93018),
        "Haiti" to LatLng(18.971187, -72.285215),
        "Holy See (Vatican City State)" to LatLng(41.902916, 12.453389000000001),
        "Honduras" to LatLng(15.199999000000002, -86.241905),
        "Hungary" to LatLng(47.162493999999995, 19.503304099999998),
        "Iceland" to LatLng(64.963051, -19.020834999999998),
        "India" to LatLng(20.593684, 78.96288),
        "Indonesia" to LatLng(-0.789275, 113.92132699999999),
        "Iran, Islamic Republic of" to LatLng(32.427907999999995, 53.688046),
        "Iraq" to LatLng(33.223191, 43.679291),
        "Ireland" to LatLng(53.142367199999995, -7.6920535999999995),
        "Israel" to LatLng(31.046051, 34.851611999999996),
        "Italy" to LatLng(41.871939999999995, 12.56738),
        "Jamaica" to LatLng(18.109581, -77.297508),
        "Japan" to LatLng(36.204823999999995, 138.252924),
        "Kazakhstan" to LatLng(48.019573, 66.923684),
        "Kenya" to LatLng(-0.023559, 37.906193),
        "Korea (South)" to LatLng(35.907757, 127.76692200000001),
        "Kuwait" to LatLng(29.31166, 47.481766),
        "Kyrgyzstan" to LatLng(41.20438, 74.766098),
        "Lao PDR" to LatLng(19.85627, 102.49549599999999),
        "Latvia" to LatLng(56.879635, 24.603189),
        "Lebanon" to LatLng(33.854721, 35.862285),
        "Lesotho" to LatLng(-29.609988, 28.233608),
        "Liberia" to LatLng(6.428055, -9.429499000000002),
        "Libya" to LatLng(26.335099999999997, 17.228331),
        "Liechtenstein" to LatLng(47.166, 9.555373),
        "Lithuania" to LatLng(55.169438, 23.881275),
        "Luxembourg" to LatLng(49.815273, 6.129582999999999),
        "Macedonia, Republic of" to LatLng(42.0004748, 21.4283806),
        "Madagascar" to LatLng(-18.766947, 46.869107),
        "Malawi" to LatLng(-13.254308, 34.301525),
        "Malaysia" to LatLng(4.210484, 101.975766),
        "Maldives" to LatLng(3.202778, 73.22068),
        "Mali" to LatLng(17.570691999999998, -3.9961659999999997),
        "Malta" to LatLng(35.937495999999996, 14.375416),
        "Mauritania" to LatLng(21.00789, -10.940835),
        "Mauritius" to LatLng(-20.348404, 57.55215200000001),
        "Mexico" to LatLng(23.634501, -102.55278399999999),
        "Moldova" to LatLng(47.411631, 28.369885),
        "Monaco" to LatLng(43.738417600000005, 7.424615799999999),
        "Mongolia" to LatLng(46.862496, 103.846656),
        "Montenegro" to LatLng(42.708678, 19.37439),
        "Morocco" to LatLng(31.791702, -7.092619999999999),
        "Mozambique" to LatLng(-18.665695, 35.529562),
        "Myanmar" to LatLng(21.916221, 95.955974),
        "Namibia" to LatLng(-22.957639999999998, 18.49041),
        "Nepal" to LatLng(28.394857, 84.12400799999999),
        "Netherlands" to LatLng(52.132633, 5.291265999999999),
        "New Zealand" to LatLng(-40.900557, 174.88597099999998),
        "Nicaragua" to LatLng(12.865416, -85.207229),
        "Niger" to LatLng(17.607789, 8.081666),
        "Nigeria" to LatLng(9.081999, 8.675277),
        "Norway" to LatLng(60.47202399999999, 8.468945999999999),
        "Oman" to LatLng(21.4735329, 55.975412999999996),
        "Pakistan" to LatLng(30.375321000000003, 69.34511599999999),
        "Panama" to LatLng(8.537981, -80.782127),
        "Papua New Guinea" to LatLng(-6.314992999999999, 143.95555),
        "Paraguay" to LatLng(-23.442503, -58.443832),
        "Peru" to LatLng(-9.189967, -75.015152),
        "Philippines" to LatLng(12.879721, 121.77401700000001),
        "Poland" to LatLng(51.919438, 19.145136),
        "Portugal" to LatLng(39.399871999999995, -8.224454),
        "Qatar" to LatLng(25.354826, 51.183884),
        "Republic of Kosovo" to LatLng(42.602635899999996, 20.902977),
        "Romania" to LatLng(45.943160999999996, 24.966759999999997),
        "Russian Federation" to LatLng(61.52401, 105.318756),
        "Rwanda" to LatLng(-1.9402780000000002, 29.873887999999997),
        "Saint Kitts and Nevis" to LatLng(17.357822, -62.782998),
        "Saint Lucia" to LatLng(13.909443999999999, -60.978893),
        "Saint Vincent and Grenadines" to LatLng(12.984304999999999, -61.287228),
        "San Marino" to LatLng(43.94236, 12.457777),
        "Sao Tome and Principe" to LatLng(0.18636, 6.613080999999999),
        "Saudi Arabia" to LatLng(23.885942, 45.079162),
        "Senegal" to LatLng(14.497401000000002, -14.452361999999999),
        "Serbia" to LatLng(44.016521, 21.005858999999997),
        "Seychelles" to LatLng(-4.679574, 55.491977),
        "Sierra Leone" to LatLng(8.460555, -11.779888999999999),
        "Singapore" to LatLng(1.352083, 103.819836),
        "Slovakia" to LatLng(48.669025999999995, 19.699023999999998),
        "Slovenia" to LatLng(46.151241, 14.995462999999997),
        "Somalia" to LatLng(5.152149, 46.199616),
        "South Africa" to LatLng(-30.559482000000003, 22.937506),
        "South Sudan" to LatLng(6.876991899999999, 31.3069788),
        "Spain" to LatLng(40.46366700000001, -3.7492199999999998),
        "Sri Lanka" to LatLng(7.873053999999999, 80.77179699999999),
        "Sudan" to LatLng(12.862807, 30.217636),
        "Suriname" to LatLng(3.919305, -56.027783),
        "Swaziland" to LatLng(-26.522503, 31.465866),
        "Sweden" to LatLng(60.128161000000006, 18.643501),
        "Switzerland" to LatLng(46.818188, 8.227511999999999),
        "Syrian Arab Republic (Syria)" to LatLng(34.802074999999995, 38.996815),
        "Taiwan, Republic of China" to LatLng(23.69781, 120.96051500000002),
        "Tajikistan" to LatLng(38.861034, 71.276093),
        "Tanzania, United Republic of" to LatLng(-6.369028, 34.888822),
        "Thailand" to LatLng(15.870032000000002, 100.99254099999999),
        "Timor-Leste" to LatLng(-8.874217, 125.72753900000001),
        "Trinidad and Tobago" to LatLng(10.691803, -61.222502999999996),
        "Tunisia" to LatLng(33.886917, 9.537499),
        "Turkey" to LatLng(38.963744999999996, 35.243322),
        "Uganda" to LatLng(1.373333, 32.290275),
        "Ukraine" to LatLng(48.379433, 31.1655799),
        "United Arab Emirates" to LatLng(23.424076, 53.847818000000004),
        "United Kingdom" to LatLng(55.378051, -3.4359729999999997),
        "United States of America" to LatLng(37.09024, -95.712891),
        "Uruguay" to LatLng(-32.522779, -55.765834999999996),
        "Uzbekistan" to LatLng(41.377491, 64.585262),
        "Venezuela (Bolivarian Republic)" to LatLng(6.42375, -66.58973),
        "Viet Nam" to LatLng(14.058324, 108.277199),
        "Western Sahara" to LatLng(24.215526999999998, -12.885834),
        "Yemen" to LatLng(15.552727, 48.516388),
        "Zambia" to LatLng(-13.133897, 27.849332),
        "Zimbabwe" to LatLng(-19.015438, 29.154857)
        )

    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView
    private val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
    private var pieChart: Pie? = null

    private val heatMapWeightedList = arrayListOf<WeightedLatLng>()
    private var heatMapTileProvider: HeatmapTileProvider? = null
    private lateinit var overlay: TileOverlay

    private lateinit var viewModel: StatisticsViewModel
    private var selectedCountry = MutableLiveData<StatisticsCountry>()
    private lateinit var handler: Handler
    private var thread: Thread? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        handler = Handler(requireContext().mainLooper)
        val mapViewBundle = savedInstanceState?.getBundle(MAPVIEW_BUNDLE_KEY)
        mapView = requireView().findViewById(R.id.mvHeatmap)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
    }

    private fun initViewModel() {
        viewModel = StatisticsViewModel(requireContext())
        viewModel.globalStatistics.observe(viewLifecycleOwner, Observer {
            updateViews()
        })
        viewModel.countryStatistics.observe(viewLifecycleOwner, Observer {
//            thread = Thread {
                val geoCoder = Geocoder(context, Locale.getDefault())
                for ((i, country) in it.withIndex()) {
                    if (heatmapCoordinates.containsKey(country.country)) {
                        heatMapWeightedList.add(
                            WeightedLatLng(
                                heatmapCoordinates[country.country],
                                country.totalConfirmed.toDouble()
                            )
                        )
                    } else {
                        val geoDataList = geoCoder.getFromLocationName(country.country, 1)
                        if (geoDataList.isNotEmpty())
                        {
                            val geoData = geoDataList[0]
                            heatMapWeightedList.add(WeightedLatLng(LatLng(geoData.latitude, geoData.longitude), country.totalConfirmed.toDouble()))
                        }
                    }
                    if (heatMapTileProvider == null) {
                        initHeatMap()
                    }
                    else if (Thread.currentThread().isInterrupted) {
                        break
                    }
                }

                if (!Thread.currentThread().isInterrupted) {
                    updateHeatMap()
                    clLoadingHeatmap.visibility = View.INVISIBLE
                }
//            }
//            thread!!.start()
        })
        viewModel.getCovidData()
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initHeatMap() {
//        handler.post {
            heatMapTileProvider = HeatmapTileProvider.Builder()
                .weightedData(heatMapWeightedList) // load our weighted data
                .radius(20) // optional, in pixels, can be anything between 20 and 50
                .maxIntensity(1000.0) // set the maximum intensity
                .build()

            overlay = googleMap.addTileOverlay(TileOverlayOptions().tileProvider(heatMapTileProvider))
//        }
    }

    private fun updateHeatMap() {
//        handler.post {
            heatMapTileProvider!!.setWeightedData(heatMapWeightedList)
            overlay.clearTileCache()
//        }
    }

    private fun initViews() {
        var popupMenu: PopupMenu
        etSearchCountry.addTextChangedListener {text ->
            if (viewModel.countryStatistics.value == null) {
                // niks
            }
            else if (etSearchCountry.text.isEmpty()) {
                selectedCountry.postValue(null)
            }
            else if (etSearchCountry.text.toString() == selectedCountry.value?.translatedCountry) {
                // niks
            }
            else {
                val filteredCountries = viewModel.countryStatistics.value!!.stream()
                    .filter {
                        it.translatedCountry.toLowerCase(Locale.getDefault()).contains(text.toString().toLowerCase(Locale.getDefault()))
                    }.toArray()

                if (filteredCountries.isNotEmpty() && filteredCountries.size <= 3) {
                    println(filteredCountries)
                    popupMenu = PopupMenu(context, etSearchCountry)
                    for (filteredCountry in filteredCountries) {
                        popupMenu.menu.add((filteredCountry as StatisticsCountry).translatedCountry)
                    }
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        val clickedCountry = filteredCountries.find {
                            (it as StatisticsCountry).translatedCountry == menuItem.title
                        } as StatisticsCountry
                        selectedCountry.postValue(clickedCountry)
                        true
                    }
                    popupMenu.show()
                }
            }
            btClearText.setOnClickListener {
                etSearchCountry.setText("")
            }
        }
        selectedCountry.observe(viewLifecycleOwner, Observer {
            updateTitle()
            updateViews()
            if (it != null)
                viewModel.getCountryData(it.country)
        })
        viewModel.countryDateDataStatistics.observe(viewLifecycleOwner, Observer {
            updateLineChart(it)
        })
        selectedCountry.postValue(null)
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
        return if (selectedCountry.value != null) selectedCountry.value!!.translatedCountry else getString(R.string.DefaultStatisticsTitleAppendix)
    }

    private fun updateLineChart(data: List<StatisticsCountryDateData>) {
        val pattern = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val activeGraphData = arrayListOf<Double>()
        val confirmedGraphData = arrayListOf<Double>()
        val deathsGraphData = arrayListOf<Double>()
        val recoveredGraphData = arrayListOf<Double>()
        val legend = arrayListOf<String>()
        for (day in data) {
            activeGraphData.add(day.active.toDouble())
            confirmedGraphData.add(day.confirmed.toDouble())
            deathsGraphData.add(day.deaths.toDouble())
            recoveredGraphData.add(day.recovered.toDouble())
            legend.add(dateFormat.format(day.date))
        }
        val aaChartView =  requireView().findViewById<AAChartView>(R.id.lcCountryData)

        val aaChartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .title("Verloop")
            .subtitle("")
            .backgroundColor("#FFFFFFFF")
            .dataLabelsEnabled(true)
            .yAxisGridLineWidth(0f)
            .xAxisLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("Actief")
                    .data(activeGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Bevestigd")
                    .data(confirmedGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Overleden")
                    .data(deathsGraphData.toTypedArray()),
                AASeriesElement()
                    .name("Genezen")
                    .data(recoveredGraphData.toTypedArray())
            ))
            .categories(legend.toTypedArray())
        aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }

    private fun updateViews() {
        if (selectedCountry.value == null) {
            coHeatmap.visibility = View.VISIBLE
            coPiechart.visibility = View.GONE
            lcCountryData.visibility = View.GONE
            tvNewConfirmed.text = viewModel.globalStatistics.value?.newConfirmed.toString()
            tvTotalConfirmed.text = viewModel.globalStatistics.value?.totalConfirmed.toString()
            tvNewDeaths.text = viewModel.globalStatistics.value?.newDeaths.toString()
            tvTotalDeaths.text = viewModel.globalStatistics.value?.totalDeaths.toString()
            tvNewRecovered.text = viewModel.globalStatistics.value?.newRecovered.toString()
            tvTotalRecovered.text = viewModel.globalStatistics.value?.totalRecovered.toString()
        } else {
            coHeatmap.visibility = View.GONE
            coPiechart.visibility = View.VISIBLE
            lcCountryData.visibility = View.VISIBLE
            tvNewConfirmed.text = selectedCountry.value!!.newConfirmed.toString()
            tvTotalConfirmed.text = selectedCountry.value!!.totalConfirmed.toString()
            tvNewDeaths.text = selectedCountry.value!!.newDeaths.toString()
            tvTotalDeaths.text = selectedCountry.value!!.totalDeaths.toString()
            tvNewRecovered.text = selectedCountry.value!!.newRecovered.toString()
            tvTotalRecovered.text = selectedCountry.value!!.totalRecovered.toString()
            etSearchCountry.setText(selectedCountry.value!!.translatedCountry)
            cvChart.setZoomEnabled(true)
            val chartEntries = arrayListOf<DataEntry>()
            APIlib.getInstance().setActiveAnyChartView(cvChart)
            chartEntries.add(ValueDataEntry(getString(R.string.TotalConfirmed), selectedCountry.value!!.totalConfirmed))
            chartEntries.add(ValueDataEntry(getString(R.string.TotalDeaths), selectedCountry.value!!.totalDeaths))
            chartEntries.add(ValueDataEntry(getString(R.string.TotalRecoveries), selectedCountry.value!!.totalRecovered))
            if (pieChart == null) {
                pieChart = AnyChart.pie()
                cvChart.setChart(pieChart)
            }
            pieChart!!.data(chartEntries)
        }
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map!!
        MapsInitializer.initialize(activity)
        map.uiSettings.isZoomControlsEnabled = false
        map.uiSettings.isZoomGesturesEnabled = false
        initViewModel()
        initViews()
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
        killThreadIfPresent()
        super.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        killThreadIfPresent()
        super.onDestroy()
    }

    override fun onDetach() {
        killThreadIfPresent()
        super.onDetach()
    }

    private fun killThreadIfPresent() {
        if (thread != null) {
            thread!!.interrupt()
            thread = null
        }
    }
}
