package br.com.serv.cadastros.repository;

import br.com.serv.cadastros.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Servico, Long > {
    @Query("select s from Servico s where s.valorPago is null or s.valorPago = 0")
    List<Servico> buscarServicosPagamentoPedente();

    @Query("select s from Servico s where s.status = 'cancelado'")
    List<Servico> buscarServicosCancelados();
}
