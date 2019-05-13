package org.mycompany;

import lombok.Builder;
import lombok.Data;

import java.util.Base64;

@Data
@Builder
public class RHIdentity {
    String accountNumber;
    String internalOrgId;

    public String toHash() {
        return Base64.getEncoder().encodeToString(getJSON().getBytes());
    }

    private String getJSON() {
        // '{"identity": {"account_number": "12345", "internal": {"org_id": "54321"}}}'
        return String.format("{\"identity\": {\"account_number\": \"%s\", \"internal\": {\"org_id\": \"%s\"}}}", accountNumber, internalOrgId);
    }
}
