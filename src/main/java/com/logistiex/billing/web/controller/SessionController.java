package com.logistiex.billing.web.controller;

import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import com.logistiex.billing.data.model.Session;
import com.logistiex.billing.service.dto.SessionDTO;
import com.logistiex.billing.service.SessionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
public class SessionController extends BaseCrudController<String, SessionDTO, Session> {

    private final SessionService sessionService;

    public SessionController(SessionService service, BaseRepository<Session, String> repository) {
        super(service, repository);
        this.sessionService = service;
    }
}
