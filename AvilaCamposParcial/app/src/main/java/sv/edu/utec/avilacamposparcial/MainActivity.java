package sv.edu.utec.avilacamposparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                bd.insert("Contactos", null, info);
                bd.close();

                Toast.makeText(getApplicationContext(), "Se insertaron los datos", Toast.LENGTH_LONG).show();
            }
        });
        busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial", null, 1);
                SQLiteDatabase bd=admin.getWritableDatabase();
                String tel=telefono.getText().toString();

                Cursor filas=bd.rawQuery("SELECT Nombre,Apellidos,Correo FROM Contactos WHERE Telefono="+tel, null);

                if(filas.moveToFirst()){
                    nombre.setText(filas.getString(0));
                    apellido.setText(filas.getString(1));
                    correo.setText(filas.getString(2));
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se encontro el registro", Toast.LENGTH_LONG).show();
                }
                bd.close();
            }
        });
        actua.setOnClickListener(new View.OnClickListener() {
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

                int cat=bd.update("Contactos", info, "Telefono="+tel, null);
                bd.close();
                if(cat==1){
                    Toast.makeText(getApplicationContext(), "Se modifico el registro", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se modifico el registro", Toast.LENGTH_LONG).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "Parcial", null, 1);
                SQLiteDatabase bd=admin.getWritableDatabase();
                String tel=telefono.getText().toString();

                int cat=bd.delete("Contactos", "Telefono="+tel, null);
                bd.close();
                nombre.setText("");
                apellido.setText("");
                correo.setText("");
                if(cat==1){
                    Toast.makeText(getApplicationContext(), "Se borro el registro", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No se borro el registro", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}