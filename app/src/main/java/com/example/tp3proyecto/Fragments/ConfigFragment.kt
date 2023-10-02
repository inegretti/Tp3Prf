package com.example.tp3proyecto.Fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
import com.google.android.material.snackbar.Snackbar
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
    lateinit var bmiI:TextView
    // la libreria no lo toma desde el viewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var z = ConfigFragmentArgs.fromBundle(requireArguments())
        v= inflater.inflate(R.layout.fragment_config, container, false)
        //grp= v.findViewById(R.id.graph)
        chart=v.findViewById(R.id.chart)
        bmiI=v.findViewById(R.id.bmi)
        btn=v.findViewById(R.id.btnIrIngPes)

        var dataValues:ArrayList<Entry>
        dataValues= ArrayList()

        for(registro in z.receptor.historialPeso){

            dataValues.add( Entry(z.receptor.historialPeso.indexOf(registro).toFloat(),registro.peso.toFloat()))


        }


        var lineDataSet1:LineDataSet
        lineDataSet1=LineDataSet(dataValues,"Evolucion de peso entre actualizaciones")
        lineDataSet1.setColors(Color.RED)
        lineDataSet1.setCircleColor(Color.BLACK)
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

        var bmi:Float

        bmi=(z.receptor.pesoActual/(z.receptor.altura*z.receptor.altura)).toFloat()
        bmiI.text="BMI/IMC:  ${bmi.toString()}"

        return v
    }


    override fun onStart() {
        super.onStart()
        var z = ConfigFragmentArgs.fromBundle(requireArguments())
        btn.setOnClickListener(){
            val action = ConfigFragmentDirections.actionConfigFragmentToRegistroPesajeHoyFragment(z.receptor)
            findNavController().navigate(action)

        }


    }


}