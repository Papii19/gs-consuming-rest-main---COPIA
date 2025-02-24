package com.example.consumingrest;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    private static RestTemplate restTemplate;

    private final RestTemplateBuilder restTemplateBuilder;

    public ConsumingRestApplication(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @PostConstruct
    private void init() {
        restTemplate = restTemplateBuilder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumingRestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return restTemplateBuilder.build();
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
        };
    }

    public static Tienda obtenerTienda(int id) {
        String url = "http://localhost:8099/api/tienda/" + id;  
        try {
            Tienda tienda = restTemplate.getForObject(url, Tienda.class);
            return tienda;
        } catch (Exception e) {
            log.error("Error obteniendo la tienda con ID " + id, e);
            return null;
        }
    }

    public static String eliminarTienda(int id) {
        Tienda tienda = obtenerTienda(id);
        if (tienda == null) {
            return "La tienda no existe";
        }

        restTemplate.delete("http://localhost:8099/api/tienda/" + id);

        Tienda tiendaEliminada = obtenerTienda(id);
        if (tiendaEliminada == null) {
            return "La tienda se ha eliminado correctamente";
        } else {
            return "No se ha podido eliminar la tienda";
        }
    }

    public static Tienda actualizarTienda(int id, Tienda tienda) {
        String url = "http://localhost:8099/api/tienda/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Tienda> requestEntity = new HttpEntity<>(tienda, headers);
        
        RestTemplate restTemplate = new RestTemplate();
        
        try {
            ResponseEntity<Tienda> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Tienda.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                log.error("Error actualizando la tienda con ID " + id + ". Código de respuesta: " + response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("Error actualizando la tienda con ID " + id, e);
            return null;
        }
    }

    public static Tienda[] obtenerTodasLasTiendas() {
        String url = "http://localhost:8099/api/tienda";  
        try {
            Tienda[] tiendas = restTemplate.getForObject(url, Tienda[].class);
            if (tiendas == null || tiendas.length == 0) {
                log.warn("No se encontraron tiendas en la respuesta.");
                return new Tienda[0];
            }
            return tiendas;
        } catch (Exception e) {
            log.error("Error obteniendo todas las tiendas desde " + url, e);
            return null;  
        }
    }

    public static Tienda insertarTienda(String nombreTienda, String ubicacion, String url) {
        Tienda tienda = new Tienda();
        tienda.setNombreTienda(nombreTienda);  
        tienda.setUbicacion(ubicacion);
        tienda.setURL(url);
    
        return restTemplate.postForObject(
                "http://localhost:8099/api/tienda",  
                tienda,  
                Tienda.class);  
    }
    public static Zapatillas obtenerZapatilla(String idZapatilla) {
        String url = "http://localhost:8099/api/zapatillas/" + idZapatilla;
        try {
            Zapatillas zapatilla = restTemplate.getForObject(url, Zapatillas.class);
            return zapatilla;
        } catch (Exception e) {
            log.error("Error obteniendo la zapatilla con ID " + idZapatilla, e);
            return null;
        }
    }
    public static Zapatillas[] obtenerTodasLasZapatillas() {
        String url = "http://localhost:8099/api/zapatillas";  
        try {
            Zapatillas[] zapatillas = restTemplate.getForObject(url, Zapatillas[].class);
            if (zapatillas == null || zapatillas.length == 0) {
                log.warn("No se encontraron zapatillas en la respuesta.");
                return new Zapatillas[0];  // Retorna un arreglo vacío si no hay resultados
            }
            return zapatillas;
        } catch (Exception e) {
            log.error("Error obteniendo todas las zapatillas desde " + url, e);
            return null;  // Si ocurre un error, retorna null
        }
    }


                           /*HOLA esto es un comentario para el commit de github*/



                           /*Este es un nuevo comentario para el segundo commit */


    public static void insertarP(String idZapatilla, String nombremodelo, BigDecimal precio, Integer talla, Integer idTienda) {
        // Crear la zapatilla como un objeto JSON
        Zapatillas nuevaZapatilla = new Zapatillas(idZapatilla, nombremodelo, precio, talla, idTienda);
    
        // Crear los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        // Crear la solicitud POST con los datos en JSON
        HttpEntity<Zapatillas> request = new HttpEntity<>(nuevaZapatilla, headers);
    
        try {
            restTemplate.exchange(
                    "http://localhost:8099/api/zapatillas", 
                    HttpMethod.POST,
                    request,
                    Void.class
            );
        } catch (Exception e) {
            log.error("Error al insertar la zapatilla: ", e);
        }
    }
    
    public static String eliminarzapa(String idZapatilla) {
        Zapatillas zapatilla = obtenerZapatilla(idZapatilla); // Método para obtener la zapatilla
        if (zapatilla == null) {
            return "La zapatilla no existe";  // Verifica si la zapatilla no existe
        }
    
        // Realiza la eliminación de la zapatilla
        restTemplate.delete("http://localhost:8099/api/zapatillas/" + idZapatilla); 
    
        // Evita hacer el GET posterior, directamente actualiza la UI
        // Aquí, puedes eliminar la zapatilla de la lista local de zapatillas si es necesario.
        // Por ejemplo, si tienes una lista llamada 'zapatillas' que contiene todas las zapatillas en la UI, puedes hacer lo siguiente:
    
        // zapatillas.removeIf(z -> z.getId().equals(id));  // Elimina la zapatilla de la lista local
    
        return "La zapatilla se ha eliminado correctamente";
    }
    
}



