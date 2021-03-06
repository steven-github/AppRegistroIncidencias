package ulacit.ed.appregistroincidencias;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraficosActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    BarGraphSeries<DataPoint> barSeries;
    ConexionSQLiteHelper admin = new ConexionSQLiteHelper(this,"Usuarios",null,1);
    Date date;
    String temp;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        sharedpreferences = getSharedPreferences(Login.sessionPrefs, Context.MODE_PRIVATE);
        if(sharedpreferences.contains(Login.id)){

        }
        else{
            Intent loginRedirect = new Intent(this,Login.class);
            startActivity(loginRedirect);
        }

        //Snippet para insercion inicial de datos
        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor fila = base.rawQuery("SELECT COUNT(id) , date(fechaDeCreacion) FROM Usuarios GROUP BY date(fechaDeCreacion)",null);
        if(fila.moveToFirst()){
            //Toast.makeText(this,"Nada que insertar", Toast.LENGTH_SHORT).show();
        }
        else{
            //Insert de Usuarios
            admin.insertDataUsuarios(1,"Asdasda","Cartago","Asdasda@gmail.com","HolaMundo","Femenino","2018-04-21 08:14:17");
            admin.insertDataUsuarios(2,"Hola","San Jose","Asdasda1@gmail.com","123456","Femenino","2018-04-21 09:14:17");
            admin.insertDataUsuarios(3,"Mundo","Cartago","Asdasda2@gmail.com","123456","Masculino","2018-04-21 10:14:17");
            admin.insertDataUsuarios(4,"FASdasda","Alajuela","Asdasda3@gmail.com","123456","Femenino","2018-04-21 11:14:17");
            admin.insertDataUsuarios(5,"Asdasda","San Jose","Asdasda4@gmail.com","123456","Femenino","2018-04-22 08:14:17");
            admin.insertDataUsuarios(6,"Hola","San Jose","Asdasda5@gmail.com","123456","Masculino","2018-04-22 09:14:17");
            admin.insertDataUsuarios(7,"Mundo","Heredia","Asdasda6@gmail.com","123456","Masculino","2018-04-23 10:14:17");
            admin.insertDataUsuarios(8,"FASdasda","Puntarenas","Asdasda7@gmail.com","123456","Masculino","2018-04-24 11:14:17");
            admin.insertDataUsuarios(9,"Asdasda","Limon","Asdasda8@gmail.com","123456","Femenino","2018-04-21 08:14:17");

            //Insert de Reportes
            admin.insertDataReportes(1,9,"San Jose","2018-04-21 08:14:17");
            admin.insertDataReportes(2,8,"Cartago","2018-04-21 08:14:17");
            admin.insertDataReportes(3,7,"Heredia","2018-04-21 08:14:17");
            admin.insertDataReportes(4,6,"San Jose","2018-04-22 08:14:17");
            admin.insertDataReportes(5,9,"Heredia","2018-04-22 08:14:17");
            admin.insertDataReportes(6,6,"Puntarenas","2018-04-23 08:14:17");
            admin.insertDataReportes(7,9,"Guanacaste","2018-04-23 08:14:17");
            admin.insertDataReportes(8,9,"Alajuela","2018-04-23 08:14:17");
            Toast.makeText(this,"Data Insertada",Toast.LENGTH_LONG).show();
        }

        createGraphs();
    }
/*
TODO: Hacer Responsive
TODO: Cambiar aspectos de configuracion a uso por recursos
 */
    private void createGraphs(){
        crearGraficoUsuariosPorFecha();
        crearGraficoReportesPorArea();
        crearGraficoReportesPorFecha();
    }


    private void crearGraficoUsuariosPorFecha() {
        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor fila = base.rawQuery("SELECT COUNT(id) , date(fechaDeCreacion) FROM Usuarios GROUP BY date(fechaDeCreacion)",null);
        GraphView usuarios_por_fecha = (GraphView) findViewById(R.id.graph_usuarios_por_fecha);
        series = new LineGraphSeries<DataPoint>();
        for (int i=0; i<fila.getCount();i++){
            fila.moveToNext();
            temp = fila.getString(1);
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                series.appendData(new DataPoint(date,Integer.parseInt(fila.getString(0))),true,20);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        usuarios_por_fecha.addSeries(series);
        usuarios_por_fecha.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraficosActivity.this));
        usuarios_por_fecha.getGridLabelRenderer().setHumanRounding(false);
    }

    private void crearGraficoReportesPorArea() {
        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor filaArea = base.rawQuery("SELECT COUNT(id) , CASE WHEN area='San Jose' THEN 1 " +
                "WHEN area = 'Cartago' THEN 2 " +
                "WHEN area = 'Heredia' THEN 3 " +
                "WHEN area = 'Alajuela' THEN 4 " +
                "WHEN area = 'Puntarenas' THEN 5 " +
                "WHEN area = 'Guanacaste' THEN 6 " +
                "WHEN area = 'Limon' THEN 7 END " +
                "FROM Reportes GROUP BY area " +
                "ORDER BY 2 ASC",null);
        GraphView reportes_area = (GraphView) findViewById(R.id.graph_area_trend);
        barSeries = new BarGraphSeries<DataPoint>();
        for (int i=0; i<filaArea.getCount();i++){
            filaArea.moveToNext();
            barSeries.appendData(new DataPoint(Integer.parseInt(filaArea.getString(1)),Integer.parseInt(filaArea.getString(0))),true,20);
        }
        reportes_area.addSeries(barSeries);
        reportes_area.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                //System.out.println(value);
                if(isValueX) {
                    switch ((int) value) {
                        case 1:
                            return "San Jose";
                        case 2:
                            return "Cartago";
                        case 3:
                            return "Heredia";
                        case 4:
                            return "Alajuela";
                        case 5:
                            return "Puntarenas";
                        case 6:
                            return "Guanacaste";
                        case 7:
                            return "Limon";
                    }
                }
                else{
                    return super.formatLabel(value, isValueX);
                }
                return super.formatLabel(value, isValueX);
            }
        });
        reportes_area.getGridLabelRenderer().setNumHorizontalLabels(7);
    }

    private void crearGraficoReportesPorFecha() {
        SQLiteDatabase base = admin.getWritableDatabase();
        Cursor fila = base.rawQuery("SELECT COUNT(id) , date(fechaDeCreacion) FROM Reportes GROUP BY date(fechaDeCreacion)",null);
        GraphView reportes_por_fecha = (GraphView) findViewById(R.id.graph_reportes_fecha);

        series = new LineGraphSeries<DataPoint>();
        for (int i=0; i<fila.getCount();i++){
            fila.moveToNext();
            temp = fila.getString(1);
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(temp);
                series.appendData(new DataPoint(date,Integer.parseInt(fila.getString(0))),true,20);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        reportes_por_fecha.addSeries(series);
        reportes_por_fecha.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(GraficosActivity.this));
        reportes_por_fecha.getGridLabelRenderer().setHumanRounding(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
