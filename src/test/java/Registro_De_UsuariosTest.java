import org.junit.Test;

import static org.junit.Assert.*;

public class Registro_De_UsuariosTest {

    @Test
    public void validarNombreCompleto() {
        assertFalse(Registro_De_Usuarios.validarNombreCompleto("Michelle Alexandra Naour Lagos"));
        assertTrue(Registro_De_Usuarios.validarNombreCompleto("Nombre Apellido"));
    }

    @Test
    public void validarRegion() {
        assertFalse(Registro_De_Usuarios.validarRegion("9"));
        assertTrue(Registro_De_Usuarios.validarRegion("RM"));
        assertFalse(Registro_De_Usuarios.validarRegion("Region Metropolitana"));
        assertTrue(Registro_De_Usuarios.validarRegion("ix"));
    }

    @Test
    public void validarNro() {
        assertFalse(Registro_De_Usuarios.validarNro(3,1,2));
        assertTrue(Registro_De_Usuarios.validarNro(3,1,3));
    }

    @Test
    public void formadeRut() {
        assertTrue(Registro_De_Usuarios.formadeRut("20412274-1"));
        assertTrue(Registro_De_Usuarios.formadeRut("256758-k"));
        assertTrue(Registro_De_Usuarios.formadeRut("2041712365-K"));
        assertFalse(Registro_De_Usuarios.formadeRut("lallalal"));
        assertFalse(Registro_De_Usuarios.formadeRut("2041227541-2"));
        assertFalse(Registro_De_Usuarios.formadeRut("20.412.274-1"));
    }

    @Test
    public void stringSoloLetrasoEspacios() {
        assertTrue(Registro_De_Usuarios.StringSoloLetrasoEspacios("Michelle Naour"));
        assertTrue(Registro_De_Usuarios.StringSoloLetrasoEspacios("Temuco"));
        assertFalse(Registro_De_Usuarios.StringSoloLetrasoEspacios("Camil0"));
        assertFalse(Registro_De_Usuarios.StringSoloLetrasoEspacios(""));
        assertFalse(Registro_De_Usuarios.StringSoloLetrasoEspacios("hola!"));
    }
}