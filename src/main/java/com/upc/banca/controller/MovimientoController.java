package com.upc.banca.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.upc.banca.models.entity.CuentaBancaria;
import com.upc.banca.models.entity.Movimiento;
import com.upc.banca.service.IClienteService;
import com.upc.banca.service.ICuentaBancariaService;
import com.upc.banca.service.IMovimientoService;

@Controller
@RequestMapping("/movimiento")
@SessionAttributes("movimiento")
public class MovimientoController {

	@Autowired
	private IMovimientoService movimientoService;
	
	@Autowired
	private ICuentaBancariaService cuentaService;
	
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		// clienteService.findFacturaById(id);
		Movimiento movimiento = movimientoService.fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(id); 

		if (movimiento == null) {
			flash.addFlashAttribute("error", "El movimiento no existe en la base de datos!");
			return "redirect:/listar";
		}

		model.addAttribute("movimiento", movimiento);
		model.addAttribute("titulo", "Movimiento: "+movimiento.getTipo()+" "+movimiento.getId().toString());
		return "movimiento/ver";
	}

	@GetMapping("/form/{cuentaId}")
	public String crear(@PathVariable(value = "cuentaId") Long cuentaId, Model model,
			RedirectAttributes flash) {

		CuentaBancaria cuenta = cuentaService.findById(cuentaId);

		if (cuenta == null) {
			flash.addFlashAttribute("error", "La cuenta no existe en la base de datos");
			return "redirect:/listar";
		}

		Movimiento movimiento = new Movimiento();
		movimiento.setCuenta(cuenta);

		model.addAttribute("movimiento", movimiento);
		model.addAttribute("titulo", "Crear movimiento");

		return "movimiento/form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Movimiento movimiento, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear movimiento");
			return "movimiento/form";
		}
		movimientoService.save(movimiento);
		status.setComplete();

		flash.addFlashAttribute("success", "Movimiento creado con éxito!");

		return "redirect:/ver/" + movimiento.getCuenta().getId();
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		Movimiento movimiento = movimientoService.findById(id);

		if (movimiento != null) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Movimiento eliminado con éxito!");
			return "redirect:/ver/" + movimiento.getCuenta().getId();
		}
		flash.addFlashAttribute("error", "El Movimiento no existe en la base de datos, no se pudo eliminar!");

		return "redirect:/listar";
	}

}