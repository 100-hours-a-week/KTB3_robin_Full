package service;

import domain.MappingData;
import domain.player.Position;
import domain.player.Team;

public class MappingService {
    private final MappingData mappingData = new MappingData();

    public Team getTeamEnumValue(int teamNumber) {
        return mappingData.getTeamEnumValue(teamNumber);
    }
    public Position getPositionEnumValue(int positionNumber) {
        return mappingData.getPositionEnumValue(positionNumber);
    }
    public String getActionName(int actionNumber) {
        return mappingData.getActionName(actionNumber);
    }
}
