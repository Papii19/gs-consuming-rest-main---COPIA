package com.example.consumingrest;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerZapatillas {
    
    
    @GetMapping("/consultarZapa")
    public String consultarZapatilla(@RequestParam(name = "idZapatilla") String idZapatilla, Model model) {
        try {
            Zapatillas zapa = ConsumingRestApplication.obtenerZapatilla(idZapatilla);  // Llamamos al método que consulta la zapatilla
            if (zapa != null) {
                model.addAttribute("out", "Estos son los datos de la zapatilla:");
                model.addAttribute("idZapatilla", zapa.getIdZapatilla());
                model.addAttribute("nombremodelo", zapa.getNombreModelo());
                model.addAttribute("precio", zapa.getPrecio());
                model.addAttribute("talla", zapa.getTalla());
                model.addAttribute("idtienda", zapa.getIdTienda());
                return "Resultados/ConsultaZapa";  // Vista que muestra los detalles de la zapatilla
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "Resultados/error";
        }
        return "Resultados/error";
    }

    @GetMapping("/borrarZapa")
public String eliminarZapatilla(@RequestParam(name = "idZapatilla") String idZapatilla, Model model) { // Cambié 'int' por 'String'
    String situacion = ConsumingRestApplication.eliminarzapa(idZapatilla); // Pasa el id como String
    model.addAttribute("out", situacion + " en las Zapatillas");
    return "Resultados/resultadoDeleteZapa";
     }

     @PostMapping("/insertarZapa")
     public String insertarZapatilla(@RequestParam("idZapatilla") String idZapatilla,
                                     @RequestParam("nombreModelo") String modelo, 
                                     @RequestParam("precio") BigDecimal precio,  
                                     @RequestParam("talla") Integer talla, 
                                     @RequestParam("idtienda") Integer idtienda, 
                                     Model model) {
         // Llamar al método que hace la inserción en el backend
         ConsumingRestApplication.insertarP(idZapatilla, modelo, precio, talla, idtienda);
     
         // Asegurarnos de que el mensaje de éxito se muestre
         model.addAttribute("mensaje", "Zapatilla insertada correctamente con ID: " + idZapatilla);
         return "Resultados/resultadoZapa";  // Redirige a la vista de resultados
     }
     
     
    @GetMapping("/resultadoZapa")
    public String mostrarResultado(Model model) {
        model.addAttribute("mensaje", "Zapatilla insertada correctamente.");
        return "Resultados/resultadoZapa";
    }

 
    @GetMapping("/consultarTodasZapas")
    public String consultarTodasZapatillas(Model model) {
        try {
            // Llamar al API para obtener todas las tiendas
            Zapatillas[] zapatillasArray = ConsumingRestApplication.obtenerTodasLasZapatillas();
    
            // Si hay tiendas, pasamos la lista al modelo
            if (zapatillasArray != null) {
                model.addAttribute("zapatillas", zapatillasArray);
                 return "Resultados/consultaTodasZapas";  // Vista que mostrará todas las zapatillas
            } else {
                model.addAttribute("error", "No hay tiendas disponibles.");
            }
           
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al obtener las zapatillas.");
        }
        return "Resultados/error";
    }
    @GetMapping("/iraconsultarTodasZapas")
    public String iraconsultarTodasZapas() {
        return "Operaciones/consultas";
    }

    @GetMapping("/irainsertarZapa")
    public String irainsert() {
        return "Operaciones/InsertarZapa";
    }

    @GetMapping("/iraborrarZapa")
    public String iraborrar() {
        return "Operaciones/borrarZapa";
    }
    @GetMapping("/iraconsultarZapa")
    public String iraconsultarZapa() {
        return "Operaciones/selectZapa";
    }

}
