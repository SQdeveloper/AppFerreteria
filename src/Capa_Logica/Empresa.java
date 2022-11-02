/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Capa_Presentacion.MenuPrincipal;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import Capa_Presentacion.Login;

/**
 *
 * @author PC
 */
public class Empresa {
    int indice;
    //Variables para Login
    String id, contraseña;
    //Variables para CrearCuenta
    String nivelCrear, idCrear, contraseñaCrear;
    public static String usuario, nivel;
    //Variables para la Tabla(Menu Principal)
    String producto, codigo, precio, cantidad, fecha;
    private String rutaCuentas = System.getProperty("user.dir") + "/src/Capa_datos/Cuentas.txt";
    private String rutaVentas = System.getProperty("user.dir") + "/src/Capa_datos/Ventas.txt";

    public Empresa(String producto, String codigo, String precio, String cantidad, String fecha) {
        this.producto = producto;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    
    public Empresa(String nivelCrear, String idCrear, String contraseñaCrear) {
        this.nivelCrear = nivelCrear;
        this.idCrear = idCrear;
        this.contraseñaCrear = contraseñaCrear;
    }

    public Empresa() {
        
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String getNivelCrear() {
        return nivelCrear;
    }

    public void setNivelCrear(String nivelCrear) {
        this.nivelCrear = nivelCrear;
    }

    public String getIdCrear() {
        return idCrear;
    }

    public void setIdCrear(String idCrear) {
        this.idCrear = idCrear;
    }

    public String getContraseñaCrear() {
        return contraseñaCrear;
    }

    public void setContraseñaCrear(String contraseñaCrear) {
        this.contraseñaCrear = contraseñaCrear;
    }

    public Empresa(String id, String contraseña) {
        this.id = id;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    //OTROS METODOS
    public boolean consultar(String codigo, ArrayList<Empresa> lista){
        boolean ret = false;
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getCodigo().equals(codigo)){
                ret = true;
                indice = i;
            }
        }
        return ret;
    }
    public int indice(){
        return indice;
    }
    
    public int codigo_compra(ArrayList<Empresa> lista){
        boolean flag = false;
        int codigoAlea = 0;
        while(flag == false){
            codigoAlea = (int)(Math.random()*(9999-1000+1)+1000);
            for(int i=0; i<lista.size(); i++){
                if(Integer.parseInt(lista.get(i).getCodigo()) == codigoAlea){
 
                }else{                   
                    flag = true;
                }
            }
        }
        return codigoAlea;
    }
    
    public int lectura_login(String ID, String contra){
        FileReader fr = null;
        int flag = 0;
        try {
            fr = new FileReader(rutaCuentas);
            BufferedReader buffer = new BufferedReader(fr);
            String linea = buffer.readLine();
            
            while(linea != null){
                String []vc = linea.split(",");
                //ID = ID.replace(" ", "");
                //System.out.println(vc[0]);
                if(ID.isEmpty() && contra.isEmpty()){
                    //JOptionPane.showMessageDialog(null, "Por favor Ingrese su cuenta en las casillas correspondientes.");
                    flag = 1;
                }
                
                else if (vc[0].equals(ID) && vc[1].equals(contra)){
                    MenuPrincipal mess = new MenuPrincipal();
                    mess.setVisible(true);
                    flag = 4;
                    usuario = ID;
                    nivel = vc[2];
                }else if(vc[0].equals(ID) && vc[1] != contra){
                    flag = 3;
                    //JOptionPane.showMessageDialog(null, "Error, la contraseña ingresada es incorrecta.", "Incorrecto", JOptionPane.WARNING_MESSAGE);
                }else if(vc[0] != ID && vc[1].equals(contra)){
                    flag = 2;
                    //JOptionPane.showMessageDialog(null, "Error, el id de usuario es incorrecto.", "Incorrecto", JOptionPane.WARNING_MESSAGE);
                }
                
                linea = buffer.readLine();
            }
            buffer.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }
    public void guardar_cuentas(ArrayList<Empresa> lista){
        FileWriter fw = null;
        try {
            fw = new FileWriter(rutaCuentas);
            BufferedWriter buffer = new BufferedWriter(fw);
            for(Empresa t : lista){
                buffer.write(t.idCrear.replace(" ", "") + "," + t.contraseñaCrear + "," + t.nivelCrear.replace(" ", "") + "\n");
            }
            buffer.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Guardar_Ventas(ArrayList<Empresa> lista){
        FileWriter fw = null;
        try {
            fw = new FileWriter(rutaVentas);
            BufferedWriter buffer = new BufferedWriter(fw);
            
            for(Empresa t: lista){
                buffer.write(t.producto + "," + t.cantidad + "," + t.precio + "," + t.codigo + "," + t.fecha + "\n");
            }
            buffer.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
    public void Lectura_Ventas(ArrayList<Empresa> lista){
        FileReader fr = null;
        try {
            fr = new FileReader(rutaVentas);
            BufferedReader leer = new BufferedReader(fr);
            String linea = leer.readLine();
            
            while(linea!=null){
                String []vc = linea.split(",");
                Empresa t = new Empresa();
                t.setProducto(vc[0]);
                t.setCantidad(vc[1]);
                t.setPrecio(vc[2]);
                t.setCodigo(vc[3]);
                t.setFecha(vc[4]);
                lista.add(t);
                linea = leer.readLine();
            }
            leer.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(Empresa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
