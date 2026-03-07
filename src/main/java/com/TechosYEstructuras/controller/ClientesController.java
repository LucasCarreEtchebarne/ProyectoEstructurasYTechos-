package com.TechosYEstructuras.controller;

import com.TechosYEstructuras.domain.Clientes;
import com.TechosYEstructuras.service.ClientesService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var clientes = clientesService.getClientes();
        model.addAttribute("clientes", clientes);
        model.addAttribute("totalClientes", clientes.size());
        model.addAttribute("cliente", new Clientes());
        return "/clientes/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Clientes cliente, RedirectAttributes redirectAttributes) {
        clientesService.save(cliente);
        redirectAttributes.addFlashAttribute("todoOk", "Cliente guardado correctamente");
        return "redirect:/clientes/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(Integer idCliente, RedirectAttributes redirectAttributes) {
        try {
            clientesService.delete(idCliente);
            redirectAttributes.addFlashAttribute("todoOk", "Cliente eliminado correctamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el cliente");
        }
        return "redirect:/clientes/listado";
    }

    @GetMapping("/modificar/{idCliente}")
    public String modificar(@PathVariable("idCliente") Integer idCliente,
                            Model model,
                            RedirectAttributes redirectAttributes) {

        Optional<Clientes> clienteOpt = clientesService.getCliente(idCliente);

        if (clienteOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
            return "redirect:/clientes/listado";
        }

        model.addAttribute("cliente", clienteOpt.get());
        return "/clientes/modifica";
    }
}