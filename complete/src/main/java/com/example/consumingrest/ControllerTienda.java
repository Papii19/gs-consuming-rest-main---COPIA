package com.example.consumingrest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerTienda {

    @GetMapping("/inicio")
    public String cargarInicio() {
        return "index";
    }

    @GetMapping("/") // Maneja la raíz del sitio
    public String cargarInicio2() {
        return "index";
    }

    @GetMapping("/consultar")
    public String consultarTienda(@RequestParam(name = "id") int id, Model model) {
        try {
            Tienda tien = ConsumingRestApplication.obtenerTienda(id);  // Cambié 'ID_Tienda' por 'id'
            if (tien != null) {
                model.addAttribute("out", "Estos son los datos de la tienda:");
                model.addAttribute("id", tien.getId());  // Cambié 'ID_Tienda' por 'id'
                model.addAttribute("Nombre_Tienda", tien.getNombreTienda());  // Cambié 'getNombre_Tienda' a 'getNombreTienda'
                model.addAttribute("Ubicacion", tien.getUbicacion());
                model.addAttribute("URL", tien.getURL());
                return "Resultados/resultado";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "Resultados/error";
        }
        return "Resultados/error";
    }

    @GetMapping("/borrar")
    public String eliminarTienda(@RequestParam(name = "id") int id, Model model) {
        String situacion = ConsumingRestApplication.eliminarTienda(id);  // Cambié 'ID_Tienda' por 'id'
        model.addAttribute("out", situacion + " en la Tienda");
        return "Resultados/resultadoDelete";
    }

    @PostMapping("/insertar")
    public String insertarTienda(@RequestParam(name = "Nombre_Tienda") String Nombre_Tienda, 
                                 @RequestParam(name = "Ubicacion") String Ubicacion, 
                                 @RequestParam(name = "URL") String URL, Model model) {
        Tienda tien = ConsumingRestApplication.insertarTienda(Nombre_Tienda, Ubicacion, URL);  // Ahora no pasamos el id
        if (tien != null) {
            model.addAttribute("out", "Perfecto! Estos son los datos que has introducido de la Tienda:");
            model.addAttribute("id", tien.getId());  // Cambié 'ID_Tienda' por 'id'
            model.addAttribute("Nombre_Tienda", tien.getNombreTienda());  // Cambié 'getNombre_Tienda' a 'getNombreTienda'
            model.addAttribute("Ubicacion", tien.getUbicacion());
            model.addAttribute("URL", tien.getURL());
            return "Resultados/resultadoInsert";
        }
        return "Resultados/error";
    }
    @PostMapping("/actualizar")
public String actualizarTienda(@RequestParam(name = "id") int id,  
                               @RequestParam(name = "Nombre_Tienda") String Nombre_Tienda, 
                               @RequestParam(name = "Ubicacion") String Ubicacion, 
                               @RequestParam(name = "URL") String URL, 
                               Model model) {
    // Crear el objeto Tienda con los datos del formulario
    Tienda tiendaActualizada = new Tienda();
    tiendaActualizada.setId(id);
    tiendaActualizada.setNombreTienda(Nombre_Tienda);
    tiendaActualizada.setUbicacion(Ubicacion);
    tiendaActualizada.setURL(URL);

    // Llamar al backend para actualizar la tienda utilizando PUT
    Tienda tiendaModificada = ConsumingRestApplication.actualizarTienda(id, tiendaActualizada);

    if (tiendaModificada != null) {
        model.addAttribute("out", "Perfecto! Estos son los datos nuevos de la tienda:");
        model.addAttribute("id", tiendaModificada.getId());
        model.addAttribute("Nombre_Tienda", tiendaModificada.getNombreTienda());
        model.addAttribute("Ubicacion", tiendaModificada.getUbicacion());
        model.addAttribute("URL", tiendaModificada.getURL());
        return "Resultados/resultadomod";
    }
    return "Resultados/error";
}

    
       

    @GetMapping("/consultarTodas")
    public String consultarTodasTiendas(Model model) {
        try {
            // Llamar al API para obtener todas las tiendas
            Tienda[] tiendasArray = ConsumingRestApplication.obtenerTodasLasTiendas();
    
            // Si hay tiendas, pasamos la lista al modelo
            if (tiendasArray != null && tiendasArray.length > 0) {
                model.addAttribute("tiendas", tiendasArray);
            } else {
                model.addAttribute("error", "No hay tiendas disponibles.");
            }
            return "Resultados/consultaTodas";  // Vista que mostrará todas las tiendas
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener las tiendas.");
            return "Resultados/error";  // Vista de error
        }
    }

    @GetMapping("/irainsert")
    public String irainsert() {
        return "Operaciones/insert";
    }

    @GetMapping("/iraconsultar")
    public String iraconsultar() {
        return "Operaciones/select";
    }

    @GetMapping("/iraactualizar")
    public String iraactualizar() {
        return "Operaciones/update";
    }

    @GetMapping("/iraborrar")
    public String iraborrar() {
        return "Operaciones/delete";
    }
    @GetMapping("/iraconsultartodas")
    public String iraconsultartodas() {
        return "Operaciones/consultas";
    }
}
