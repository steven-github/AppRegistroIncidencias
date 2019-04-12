package ulacit.ed.appregistroincidencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraficosActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void onStart() {
        super.onStart();
        createGraphs();
    }

    private void createGraphs(){
        double x,y;
        //Grafico de reportes por usuarios
        x = -10.0;
        GraphView reportes_usuarios = (GraphView) findViewById(R.id.graph_reportes_por_usuarios);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i<500; i++){
            x = x+0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x,y),true,500);
        }
        reportes_usuarios.addSeries(series);
        //Grafico de trend de reportes por area
        x = -25.0;
        GraphView reportes_area = (GraphView) findViewById(R.id.graph_area_trend);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i<500; i++){
            x = x+0.5;
            y = Math.cos(x);
            series.appendData(new DataPoint(x,y),true,500);
        }
        reportes_area.addSeries(series);

        //Grafico de trend de reportes por area
        x = -25.0;
        GraphView reportes_fecha = (GraphView) findViewById(R.id.graph_MoM_trend);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i<500; i++){
            x = x+0.10;
            y = Math.tan(x);
            series.appendData(new DataPoint(x,y),true,500);
        }
        reportes_fecha.addSeries(series);
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
