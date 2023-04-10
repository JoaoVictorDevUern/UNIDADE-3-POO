package br.com.serv.cadastros.service;

import br.com.serv.cadastros.entity.Servico;
import br.com.serv.cadastros.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class ServicoService {
    @Autowired
    private ServiceRepository servicoRepository;

    public List<Servico> buscarTodos(){

        return servicoRepository.findAll();
    }

    public List<Servico> buscarServicosPagamentoPedente(){
        return servicoRepository.buscarServicosPagamentoPedente();
    }

    public List<Servico> buscarServicosCancelados(){
        return servicoRepository.buscarServicosCancelados();
    }

    public Servico inserir(Servico servico){
        if(servico.getValorPago()==null || servico.getValorPago()==0 || servico.getDataPagamento()==null){
            servico.setStatus("Pendente");
        }else{
            servico.setStatus("Realizado-");
        }
        return servicoRepository.saveAndFlush(servico);
    }

    public Servico alterar(Servico servico){
        if(servico.getValorPago()!=null && servico.getValorPago()>0 && servico.getDataPagamento()!=null){
            servico.setStatus("Realizado");
        }
        return servicoRepository.saveAndFlush(servico);
    }

    public void cancelarServico(Long id){
        Servico servico = servicoRepository.findById(id).get();
        servico.setStatus("cancelado");
        servicoRepository.save(servico);
    }

    public void excluir(Long id){
        Servico servico = servicoRepository.findById(id).get();
        servicoRepository.delete(servico);
    }
}
