package com.logistiex.billing.batch.processor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.logistiex.billing.data.model.TransactionUploadRequest;
import com.logistiex.billing.service.dto.TransactionUploadRequestDTO;
import com.logistiex.common.batch.config.LoaderConfig;
import com.logistiex.common.batch.config.LoaderConfigRepository;
import com.logistiex.common.batch.data.model.AbstractUploadRequest;
import com.logistiex.common.batch.processor.AbstractCrudUploadProcessor;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.FileStoreService;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionUploadProcessor extends AbstractCrudUploadProcessor<TransactionUploadRequest, String, TransactionDTO, Transaction, TransactionUploadRequestDTO> {
    /**
     * Constructor
     *
     * @param service          - instance of {@link BaseCrudService} for the entity which represents the records in the file
     * @param requestService   - instance of {@link BaseCrudService} for {@link AbstractUploadRequest}
     * @param objectMapper     - JSON object mapper
     * @param validator        - jakarta annotation validator
     * @param configRepository - instance of {@link LoaderConfigRepository}
     * @param fileStoreService - instance of {@link FileStoreService} to interact with {@code StorageEngine}
     */
    protected TransactionUploadProcessor(BaseCrudService<String, TransactionDTO, Transaction> service, BaseCrudService<String, TransactionUploadRequestDTO, TransactionUploadRequest> requestService, ObjectMapper objectMapper, Validator validator, LoaderConfigRepository configRepository, FileStoreService fileStoreService) {
        super(service, requestService, objectMapper, validator, configRepository, fileStoreService);
    }

    @Override
    protected String getContext(TransactionUploadRequest request, LoaderConfig config) {
        return request.getOrgCode();
    }
}
