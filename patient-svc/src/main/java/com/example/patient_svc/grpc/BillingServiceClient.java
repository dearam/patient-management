package com.example.patient_svc.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillingServiceClient {
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    //calling localhost:9091/BillingService/CreateBillingAccount
    //aws.grpc:123123123/BillingService/CreateBillingAccount
    public BillingServiceClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.port:9091}") int serverPort) {
        log.info("connecting to BillingService at {}:{}", serverAddress, serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                serverAddress,
                serverPort).usePlaintext().build();

        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String name, String email) {
        log.info("Creating billing account for patientId: {}", patientId);

        billing.BillingRequest request = billing.BillingRequest.newBuilder()
                .setPatientId(patientId)
                .setName(name)
                .setEmail(email)
                .build();

        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Received billing account response: {}", response);
        return response;
    }
}
