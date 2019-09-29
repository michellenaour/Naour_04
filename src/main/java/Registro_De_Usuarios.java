import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Registro_De_Usuarios {
    static FileWriter writer;
    static Scanner teclado=new Scanner(System.in);

    public static void main (String[]args){
        crearRegistroUsuarios();
        menu();
        cerrarArchivo();

    }

    public static void crearUsuario(){
        String nombreCompleto=leerNombreCompleto();
        String rut=leerRut();
        String direccion=leerDireccionCompleta();
        String usuario= unirDatos(nombreCompleto,rut,direccion);
        guardarDatosUsuario(usuario);
    }

    public static String unirDatos(String nombre,String rut,String direc){
        String usuario=nombre+","+rut+","+direc;
        return usuario;
    }
    //retorna nombre y apellido validado y unido
    public static String leerNombreCompleto(){
        boolean flagNombreCompleto=false;
        String nombreCompleto="";
        while(!flagNombreCompleto) {
            String nombre = leerNombre();
            String apellido = leerApellido();
            nombreCompleto = nombre + " " + apellido;
            flagNombreCompleto=validarNombreCompleto(nombreCompleto);
            if (!flagNombreCompleto){System.out.println("Error!máximo 20 caracteres");}
        }
        return nombreCompleto;
    }
    public static String leerNombre(){
        boolean flag=false;
        String nombre="";
        while(!flag){
            nombre=leerString("Ingrese su nombre");
            flag=validarNombre(nombre);
            if (!flag){System.out.println("error! ingrese solo letras");}
        }
        return nombre;
    }
    public static String leerApellido(){
        boolean flag=false;
        String apellido="";
        while(!flag){
            apellido=leerString("Ingrese su apellido");
            flag=validarNombre(apellido);
            if(!flag){System.out.println("error! ingrese solo letras");}
        }
        return apellido;
    }
    public static String leerRut(){
        boolean flag=false;
        String rut="";
        while(flag==false){
            rut=leerString("Ingrese su rut sin punto y con guión \nEjemplo:11111111-1");
            flag=validarRut(rut);
            if(flag==false){System.out.println("error! ingrese su rut como se le indica");}
        }
        return rut;
    }
    //retorna direccion completa validada
    public static String leerDireccionCompleta(){
        boolean flag=false;
        String calle=leerCalle();
        int nroCasa=leerNroCasa();
        String comuna=leerComuna();
        String region=leerRegion();
        String direcCompleta=calle+","+Integer.toString(nroCasa)+","+comuna+","+region;
        return direcCompleta;
    }
    //debe llamarse usuarios.cvs pero cambialo dsps
    public static void crearRegistroUsuarios(){
        try {
            writer = new FileWriter("test2.csv");
            writer.append("nombre,rut,direccion\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarDatosUsuario(String usuario){
        try {
            writer.append(usuario);
            writer.append("\n");
        } catch (IOException e) {}
    }
    public static String leerString(String txt){
        System.out.println(txt);
        String c= teclado.next();
        return c;
    }
    public static void cerrarArchivo(){
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {}
    }
    public static boolean validarNombre(String nombre){
        boolean palabra=StringSoloLetrasoEspacios(nombre);
        if (palabra){
            return true;
        }
        return false;
    }
    public static boolean StringSoloLetrasoEspacios(String str){
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)&&c!=' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean validarNombreCompleto(String nombreCompleto){
        boolean flag=false;
        if (nombreCompleto.length()<=21 && nombreCompleto.length()>=3 ){
            flag=true;
        }
        return flag;}

    public static boolean validarRut(String rut){
        boolean flag=false;
        boolean esRut=formadeRut(rut);
        if (esRut && rut.length()>3 && rut.length()<11){
            flag=true;
        }
        return flag;
    }
    public static boolean formadeRut(String rut){
        char[] chars = rut.toCharArray();
        int largo=chars.length;
        for (int i=0;i<chars.length-2;i++){
            if (!Character.isDigit(chars[i])){ return false; }
        }
        if(chars[largo-2]!='-'){return false;}
        if(!Character.isDigit(chars[largo-1])&& chars[largo-2]!='K'&&chars[largo-2]!='k'){return false;}
        return true;
    }
    public static void menu() {
        int opcion = 0;
        while (opcion !=2) {
            mostrarMenu();
            opcion = recibirNro("Ingrese una opción: 1 o 2", 1, 2);
            ejecutarOpcion(opcion);
        }
    }
    public static void mostrarMenu(){
        System.out.println("---------------Menú---------------");
        System.out.println(" ( 1 )  Añadir un nuevo usuario.");
        System.out.println(" ( 2 )  Salir. \n");
    }
    public static void ejecutarOpcion(int op){
        if (op == 1) {
            System.out.println("------Crear un Nuevo Ususario------");
            crearUsuario();
        }
        if (op == 2) {
            System.out.println("-------Salir del programa-------");
        }
    }
    public static int leerNro(String txt) {
        System.out.println(txt);
        while (!teclado.hasNextInt()) {
            System.out.println("Error! debe ingresar un número");
            teclado.nextLine();
        }
        int number = teclado.nextInt();
        return number;

    }
    public static int recibirNro(String txt, int min, int max) {
        int nro = 0;
        boolean flag = false;
        while (flag == false) {
            nro = leerNro( txt);
            flag = validarNro(nro, min, max);
            if (flag == false) {
                System.out.println("Error, ingrese un número entre " + min + " y " + max);
            }
        }
        return nro;
    }
    public static boolean validarNro(int n, int min, int max) {
        boolean valido = false;
        if (n >= min && n <= max) {
            valido = true;
        }
        return valido;
    }

    public static String leerCalle(){
        boolean flag=false;
        String Calle="";
        while(!flag){
            Calle=leerString("ingrese su calle");
            flag=validarCalle(Calle);
            if (!flag){System.out.println("error! no puede ingresar números");}
        }
        return Calle;
    }
    public static int leerNroCasa(){
        boolean flag=false;
        int nrocasa=0;
        while (!flag){
            nrocasa=leerNro("ingrese el número de calle");
            flag=validarNro(nrocasa,1,9999);
            if(!flag){System.out.println("Error! solo puede ingresar números");}
        }
        return nrocasa;
    }
    public static String leerComuna(){
        boolean flag=false;
        String comuna="";
        while(!flag){
            comuna=leerString("ingrese su Comuna: ");
            flag=validarComuna(comuna);
            if (!flag){System.out.println("error! no puede ingresar números");}
        }
        return comuna;
    }
    public static String leerRegion(){
        boolean flag=false;
        String Region="";
        while(!flag){
            Region=leerString("ingrese su Region:RM,I,II,III,IV,V,VI,VII,VIII,IX,X,XI,XII,XIV,XV,XVI");
            flag=validarRegion(Region);
            if (!flag){System.out.println("error! ingrese valores válidos para la región");}
        }
        return Region;
    }

    public static boolean validarCalle(String calle){
        if (StringSoloLetrasoEspacios(calle)){
            return true;
        }
        return false;}
    public static boolean validarComuna(String comuna){
        if (StringSoloLetrasoEspacios(comuna)){
            return true;
        }
        return false;
    }
    public static boolean validarRegion(String region){
        if (region.equals("I")||region.equals("II")||region.equals("III")||region.equals("IV")||region.equals("V")||region.equals("VI")||region.equals("VII")||region.equals("VII")||region.equals("IX")||region.equals("X")||region.equals("XI")||region.equals("XII")||region.equals("RM")||region.equals("XIV")||region.equals("XV")||region.equals("XVI")){
            return true;
        }
        return false;
    }
    public static boolean SoloContieneNros(String str){
        char[] chars = str.toCharArray();
        for (int i=0;i<chars.length;i++){
            if (!Character.isDigit(chars[i])){ return false; }
        }
        return true;
    }

}
