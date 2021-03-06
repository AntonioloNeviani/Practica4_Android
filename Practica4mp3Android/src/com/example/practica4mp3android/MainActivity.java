package com.example.practica4mp3android;


import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lista;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<Mp3Audio> datos = new ArrayList<Mp3Audio>();  
        
        datos.add(new Mp3Audio(R.drawable.im_104, "Josh Groban", "You Raise Me Up"));
        datos.add(new Mp3Audio(R.drawable.im_105, "Adele", "Rolling In The Deep"));
        datos.add(new Mp3Audio(R.drawable.im_106, "Eric Saade", "Forgive Me"));
        datos.add(new Mp3Audio(R.drawable.im_107, "David Bustamante", "Ojo Por Ojo"));
        datos.add(new Mp3Audio(R.drawable.im_108, "Eric Saade", "Sky Falls Down (Feat. J-Son)"));
        datos.add(new Mp3Audio(R.drawable.im_104, "Josh Groban", "Remember When It Rained"));
        datos.add(new Mp3Audio(R.drawable.im_110, "Eric Saade", "Popular (Remix)"));
        
        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new Mp3AudiosAdapter(this, R.layout.listview_item, datos){
			@Override
			public void onEntrada(Object listview_item, View view) {
		        if (listview_item != null) {
		            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
		            if (texto_superior_entrada != null) 
		            	texto_superior_entrada.setText(((Mp3Audio) listview_item).get_textoEncima()); 
		              
		            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
		            if (texto_inferior_entrada != null)
		            	texto_inferior_entrada.setText(((Mp3Audio) listview_item).get_textoDebajo()); 
		              
		            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
		            if (imagen_entrada != null)
		            	imagen_entrada.setImageResource(((Mp3Audio) listview_item).get_idImagen());
		        }
			}
		});
        
        lista.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Mp3Audio elegido = (Mp3Audio) pariente.getItemAtPosition(posicion); 
                
                CharSequence texto = "Abriendo: " + elegido.get_textoEncima();
                Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();

                nuevo(pariente,view,posicion,id);
			}
        });

    }
    public void nuevo(AdapterView<?> pariente, View view, int posicion, long id){
    	Intent intent = new Intent(this, ItemActivity.class );
    	Mp3Audio item = (Mp3Audio) lista.getAdapter().getItem(posicion);
    	intent.putExtra("imagen",item.get_idImagen());
    	intent.putExtra("debajo",item.get_textoDebajo().toString());
    	intent.putExtra("encima",item.get_textoEncima().toString()); 
    	intent.putExtra("posicion",posicion);
        startActivity(intent);
 
    }
}

