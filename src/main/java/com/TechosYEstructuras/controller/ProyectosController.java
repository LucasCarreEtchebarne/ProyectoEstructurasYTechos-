package com.TechosYEstructuras.controller;

import com.TechosYEstructuras.domain.Proyectos;
import com.TechosYEstructuras.service.ClientesService;
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
@RequestMapping("/proyectos")
public class ProyectosController {

    private final ProyectosService proyectosService;
    private final ClientesService clientesService;

    public ProyectosController(ProyectosService proyectosService, ClientesService clientesService) {
        this.proyectosService = proyectosService;
        this.clientesService = clientesService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var proyectos = proyectosService.getProyectos();
        var clientes = clientesService.getClientes();

        model.addAttribute("proyectos", proyectos);
        model.addAttribute("clientes", clientes);
        model.addAttribute("totalProyectos", proyectos.size());
        model.addAttribute("proyecto", new Proyectos());

        return "/proyectos/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Proyectos proyecto, RedirectAttributes redirectAttributes) {
        proyectosService.save(proyecto);
        redirectAttributes.addFlashAttribute("todoOk", "Proyecto guardado correctamente");
        return "redirect:/proyectos/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Integer idProyecto, RedirectAttributes redirectAttributes) {
        try {
            proyectosService.delete(idProyecto);
            redirectAttributes.addFlashAttribute("todoOk", "Proyecto eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el proyecto");
        }
        return "redirect:/proyectos/listado";
    }

    @GetMapping("/modificar/{idProyecto}")
    public String modificar(@PathVariable("idProyecto") Integer idProyecto,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        Optional<Proyectos> proyectoOpt = proyectosService.getProyecto(idProyecto);

        if (proyectoOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Proyecto no encontrado");
            return "redirect:/proyectos/listado";
        }

        var clientes = clientesService.getClientes();

        model.addAttribute("proyecto", proyectoOpt.get());
        model.addAttribute("clientes", clientes);

        return "/proyectos/modifica";
    }
}