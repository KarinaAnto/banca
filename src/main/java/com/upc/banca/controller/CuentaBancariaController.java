package com.upc.banca.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.upc.banca.models.entity.Banco;
import com.upc.banca.models.entity.Cliente;
import com.upc.banca.models.entity.CuentaBancaria;
import com.upc.banca.service.IBancoService;
import com.upc.banca.service.IClienteService;
import com.upc.banca.service.ICuentaBancariaService;

@Controller
@RequestMapping("/cuenta")
@SessionAttributes("cuenta")
public class CuentaBancariaController {

	@Autowired
	private ICuentaBancariaService cuentaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IBancoService bancoService;


	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {

		// clienteService.findFacturaById(id);
		CuentaBancaria cuenta = cuentaService.fetchCuentaBancariaByIdWithClienteWhitMovimientoWithBanco(id); 

		if (cuenta == null) {
			flash.addFlashAttribute("error", "La cuenta no existe en la base de datos!");
			return "redirect:/listar";
		}

		model.addAttribute("cuenta", cuenta);
		model.addAttribute("titulo", "Cuenta Bancaria: "+cuenta.getNumero().toString()+" "+cuenta.getBanco().getNombre());
		return "cuenta/ver";
	}

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable(value = "clienteId") Long clienteId, Model model,
			RedirectAttributes flash) {

		Cliente cliente = clienteService.findById(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}

		CuentaBancaria cuenta = new CuentaBancaria();
		cuenta.setCliente(cliente);

		model.addAttribute("cuenta", cuenta);
		model.addAttribute("titulo", "Crear cuenta");

		return "cuenta/form";
	}

	@GetMapping(value = "/cargar-banco/{term}", produces = { "application/json" })
	public @ResponseBody List<Banco> cargarProductos(@PathVariable String term) {
		return bancoService.findByNombre(term);
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid CuentaBancaria cuenta, BindingResult result, Model model,
			@RequestParam(name = "banco_id", required = false) Long bancoId,
			RedirectAttributes flash,SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cuenta Bancaria");
			return "cuenta/form";
		}

		Banco banco = bancoService.findById(bancoId);
		cuenta.setBanco(banco);
		
		String mensajeFlash = (cuenta.getId() != null) ? "Cuenta editada con éxito!" : "Cuenta creada con éxito!";

		cuentaService.save(cuenta);

		status.setComplete();

		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/ver/" + cuenta.getCliente().getId();
	}

	

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		CuentaBancaria cuenta = cuentaService.findById(id);

		if (cuenta != null) {
			cuentaService.delete(id);
			flash.addFlashAttribute("success", "Cuenta eliminada con éxito!");
			return "redirect:/ver/" + cuenta.getCliente().getId();
		}
		flash.addFlashAttribute("error", "La factura no existe en la base de datos, no se pudo eliminar!");

		return "redirect:/listar";
	}

}
