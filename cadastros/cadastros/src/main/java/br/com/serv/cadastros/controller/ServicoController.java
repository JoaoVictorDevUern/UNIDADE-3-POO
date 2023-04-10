package br.com.serv.cadastros.controller;

import br.com.serv.cadastros.entity.Servico;
import br.com.serv.cadastros.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servico")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;

    @GetMapping("/")
    public List<Servico> buscarTodos(){
        return servicoService.buscarTodos();
    }
    @GetMapping("/pagamentoPedente")
    public List<Servico> buscarServicosPagamentoPedente(){
        return servicoService.buscarServicosPagamentoPedente();
    }
    @GetMapping("/cancelados")
    public List<Servico> buscarServicosCancelados(){
        return servicoService.buscarServicosCancelados();
    }
    @PostMapping("/")
    public ResponseEntity<Void> cancelar(@PathVariable("id") Long id){
        servicoService.cancelarServico(id);
        try {
            servicoService.cancelarServico(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Não foi possível cancelar o serviço com o ID especificado.");
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/")
    public Servico inserir(@RequestBody Servico servico){
        return servicoService.inserir(servico);
    }

    @PutMapping("/")
    public Servico alterar(@RequestBody Servico servico){
        return servicoService.alterar(servico);
    }
    @DeleteMapping("/")
    public ResponseEntity<Void> remover(@PathVariable("id") Long id){
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }


}
