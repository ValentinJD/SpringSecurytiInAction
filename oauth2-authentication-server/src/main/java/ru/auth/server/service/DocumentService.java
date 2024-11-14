package ru.auth.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ru.auth.server.entity.Document;
import ru.auth.server.repository.DocumentRepository;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

//    @PostAuthorize("hasPermission(returnObject, 'ROLE_ADMIN')")
//    public Document getDocument(String code) {
//        return documentRepository.findDocument(code);
//    }

    @PreAuthorize("hasPermission(#code, 'document', 'ROLE_ADMIN')")
    public Document getDocument(String code) {
        return documentRepository.findDocument(code);
    }
}
