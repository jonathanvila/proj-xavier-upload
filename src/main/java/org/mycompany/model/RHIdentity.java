package org.mycompany.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class RHIdentity {
    String accountNumber;
    Map<String,String> internal;

    
}
