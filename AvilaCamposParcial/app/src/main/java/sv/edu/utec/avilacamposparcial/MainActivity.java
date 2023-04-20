package sv.edu.utec.avilacamposparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sv.edu.utec.avilacamposparcial.Helper.AdminSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
EditText nombre, apellido,telefono, correo;
Button insert, busca, actua, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.txtNombre);
        apellido=findViewById(R.id.txtApellidos);
        telefono=findViewById(R.id.txtTelefono);
        correo=findViewById(R.id.txtCorreo);
        insert=findViewById(R.id.btnInsertar);
        busca=findViewById(R.id.btnBuscar);
        actua=findViewById(R.id.btnActua);
        delete=findViewById(R.id.btnDelete);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial", null, 1);
                SQLiteDatabase bd=admin.getWritableDatabase();
                String nom=nombre.getText().toString();
                String ape=apellido.getText().toString();
                String tel=telefono.getText().toString();
                String cor=correo.getText().toString();

                ContentValues info=new ContentValues();
                info.put("Nombre", nom);
                info.put("Apellidos", ape);
                info.put("Telefono", tel);
                info.put("Correo", cor);
            }
        });
    }
}