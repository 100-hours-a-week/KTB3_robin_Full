package service;

import domain.MappingData;

public class MappingService {
    private final MappingData mappingData = new MappingData();

    public String getActionName(int actionNumber) {
        return mappingData.getActionName(actionNumber);
    }
}
