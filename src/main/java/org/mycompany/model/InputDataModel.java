package org.mycompany.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputDataModel {
    String customerId;
    String filename;
    Integer numberOfHosts;
    Integer totalDiskSpace;
}
