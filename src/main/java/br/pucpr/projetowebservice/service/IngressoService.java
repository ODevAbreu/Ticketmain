package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Ingresso;
import br.pucpr.projetowebservice.repository.IngressoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngressoService {
    private final IngressoRepository ingressoRepository;

    public Ingresso save(Ingresso ingresso) {
        return ingressoRepository.save(ingresso);
    }

    public List<Ingresso> findAll() {
        return ingressoRepository.findAll();
    }

    public Ingresso findById(Long id) {
        return ingressoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingresso não encontrado com ID: " + id));
    }

    public void delete(Long id) {
        ingressoRepository.deleteById(id);
    }
}
