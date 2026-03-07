package com.TechosYEstructuras.controller;

import com.TechosYEstructuras.domain.Cotizaciones;
import com.TechosYEstructuras.service.ClientesService;
import com.TechosYEstructuras.service.CotizacionesService;
import com.TechosYEstructuras.service.ProyectosService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cotizaciones")
public class CotizacionesController {

    private final CotizacionesService cotizacionesService;
    private final ClientesService clientesService;
    private final ProyectosService proyectosService;

    public CotizacionesController(CotizacionesService cotizacionesService,
                                  ClientesService clientesService,
                                  ProyectosService proyectosService) {
        this.cotizacionesService = cotizacionesService;
        this.clientesService = clientesService;
        this.proyectosService = proyectosService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var cotizaciones = cotizacionesService.getCotizaciones();
        var clientes = clientesService.getClientes();
        var proyectos = proyectosService.getProyectos();

        model.addAttribute("cotizaciones", cotizaciones);
        model.addAttribute("clientes", clientes);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("totalCotizaciones", cotizaciones.size());
        model.addAttribute("cotizacion", new Cotizaciones());

        return "/cotizaciones/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Cotizaciones cotizacion, RedirectAttributes redirectAttributes) {
        cotizacionesService.save(cotizacion);
        redirectAttributes.addFlashAttribute("todoOk", "Cotización guardada correctamente");
        return "redirect:/cotizaciones/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Integer idCotizacion, RedirectAttributes redirectAttributes) {
        try {
            cotizacionesService.delete(idCotizacion);
            redirectAttributes.addFlashAttribute("todoOk", "Cotización eliminada correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar la cotización");
        }
        return "redirect:/cotizaciones/listado";
    }

    @GetMapping("/modificar/{idCotizacion}")
    public String modificar(@PathVariable("idCotizacion") Integer idCotizacion,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        Optional<Cotizaciones> cotizacionOpt = cotizacionesService.getCotizacion(idCotizacion);

        if (cotizacionOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cotización no encontrada");
            return "redirect:/cotizaciones/listado";
        }

        var clientes = clientesService.getClientes();
        var proyectos = proyectosService.getProyectos();

        model.addAttribute("cotizacion", cotizacionOpt.get());
        model.addAttribute("clientes", clientes);
        model.addAttribute("proyectos", proyectos);

        return "/cotizaciones/modifica";
    }
}