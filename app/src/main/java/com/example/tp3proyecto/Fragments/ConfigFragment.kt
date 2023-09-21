package com.example.tp3proyecto.Fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tp3proyecto.Entidades.RegistroPeso
import com.example.tp3proyecto.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
//import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class ConfigFragment : Fragment() {

    lateinit var v: View
    //lateinit var grp:GraphView
    lateinit var btn:Button
    lateinit var chart:LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var z = ConfigFragmentArgs.fromBundle(requireArguments())
        v= inflater.inflate(R.layout.fragment_config, container, false)
        //grp= v.findViewById(R.id.graph)
        chart=v.findViewById(R.id.chart)
        btn=v.findViewById(R.id.btnIrIngPes)

        var dataValues:ArrayList<Entry>
        dataValues= ArrayList()

        for(registro in z.receptor.historialPeso){

            dataValues.add( Entry(z.receptor.historialPeso.indexOf(registro).toFloat(),registro.peso.toFloat()))


        }


        var lineDataSet1:LineDataSet
        lineDataSet1=LineDataSet(dataValues,"Evolucion de peso entre actualizaciones")

        var dataSets:ArrayList<ILineDataSet>
        dataSets= ArrayList()
        dataSets.add(lineDataSet1)

        var data:LineData
        data=LineData(dataSets)
        chart.data=data
        chart.invalidate()
        chart.setNoDataText("No data")
        var desc:Description
        desc=Description()

        chart.description=desc
        chart.getXAxis().setDrawLabels(false);
       // chart.getLegend().setEnabled(false);

        return v
    }


    override fun onStart() {
        super.onStart()
        var z = ConfigFragmentArgs.fromBundle(requireArguments())

        /*
        var series:LineGraphSeries<DataPoint>
        series = LineGraphSeries<DataPoint>()
        series.title="Evolucion"
        series.isDrawDataPoints=true
        series.thickness=8

        grp.title="Evolucion"
        grp.titleTextSize = 60F
        var grid:GridLabelRenderer
        grid=grp.gridLabelRenderer
        grid.verticalAxisTitle="Peso en Kg"
        grid.horizontalAxisTitle="Nro de Mes"
        grid.textSize=20f


        grp.getViewport().setXAxisBoundsManual(true);
        grp.getGridLabelRenderer().setNumHorizontalLabels(3);
        grp.getViewport().setMinX(z.receptor.historialPeso.get(0).fecha.time.toDouble());
        grp.getViewport().setMaxX(z.receptor.historialPeso.get(z.receptor.historialPeso.lastIndex).fecha.time.toDouble() + 3*24*60*60*1000); // + 3 days




        for(Registro  in z.receptor.historialPeso){
            Log.d( "prueba", "fecha:  ${Registro.fecha} mes ${Registro.fecha.month}")
            series.appendData(DataPoint(Registro.fecha,Registro.peso),true, z.receptor.historialPeso.size)


        }
        grp.addSeries(series)
        series.color=Color.RED

         */
        btn.setOnClickListener(){
            val action = ConfigFragmentDirections.actionConfigFragmentToRegistroPesajeHoyFragment(z.receptor)
            findNavController().navigate(action)

        }


    }


}