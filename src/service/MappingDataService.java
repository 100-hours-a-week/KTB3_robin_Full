package service;

import domain.MappingData;
import domain.player.Position;
import domain.player.Team;

import java.util.HashSet;

public class MappingDataService {
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
    public HashSet<Integer> getAvailableActionNumbersBySituation(int situationNumber) {
        return mappingData.getActionNumbersBySituation(situationNumber);
    }
    public HashSet<Integer> getAvailableActionNumbersByPosition(String position) {
        return mappingData.getActionNumbersByPosition(position);
    }
}
