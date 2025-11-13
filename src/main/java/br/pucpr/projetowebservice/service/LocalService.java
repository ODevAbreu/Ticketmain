package br.pucpr.projetowebservice.service;

import br.pucpr.projetowebservice.model.Local;
import br.pucpr.projetowebservice.repository.LocalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalService {

    private final LocalRepository localRepository;

    public Local save(Local local) {
        return localRepository.save(local);
    }

    public List<Local> findAll() {
        return localRepository.findAll();
    }

    public void delete(Integer idLocal) {
        localRepository.deleteById(idLocal);
    }

    public Local findById(Integer idLocal) {
        return localRepository.findById(idLocal)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));
    }
}
