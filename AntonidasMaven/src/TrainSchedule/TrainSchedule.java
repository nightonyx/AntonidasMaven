package TrainSchedule;

import java.util.*;

public class TrainSchedule {
    public TrainSchedule(Map<String, LinkedList<String>> trains) {
        this.trains = trains;
    }

    //Format of train: key - name, value - departure time, stations..., last station;
    private Map<String, LinkedList<String>> trains;

    //Functions:
    //1. Correct time:
    private boolean correctTime(String time) {
        int hours;
        int minutes;
        try {
            String[] parts = time.split(":");
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return false;
        }
        return !(hours < 0 || hours > 23) && !(minutes < 0 || minutes > 59);
    }

    //2. From hours to minutes:
    @org.jetbrains.annotations.NotNull
    private Integer toMinutes(String time) {
        if (!correctTime(time)) {
            throw new IllegalArgumentException();
        }
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    //3. Add train:
    public boolean addTrain(String nameTrain, String timeTrain, String lastStation) {
        if (!trains.containsKey(nameTrain)) {
            LinkedList<String> trainProperties = new LinkedList<String>();
            trainProperties.add(timeTrain);
            trainProperties.add(lastStation);
            trains.put(nameTrain, trainProperties);
            return true;
        } else {
            return false;
        }
    }

    //4. Remove train:
    public boolean removeTrain(String nameTrain) {
        if (trains.containsKey(nameTrain)) {
            trains.remove(nameTrain);
            return true;
        } else {
            return false;
        }
    }

    //5. Add station for current train:
    public boolean addTrainStation(String nameTrain, String station) {
        if (trains.containsKey(nameTrain)) {
            if (!trains.get(nameTrain).contains(station)) {
                trains.get(nameTrain).add(1, station);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //6. Remove station for current train:
    public boolean removeTrainStation(String nameTrain, String station) {
        if (trains.containsKey(nameTrain)) {
            if (trains.get(nameTrain).contains(station)) {
                if (trains.get(nameTrain).indexOf(station) != trains.get(nameTrain).size() - 1){
                    trains.get(nameTrain).remove(station);
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }

    //7. Search station
    public LinkedList<String> searchTheNextTrain(String station, String time) {
        //Output time:
        int outputTime = 1440;
        //Counter:
        int i = 0;
        //Output train:
        LinkedList<String> outputTrain = null;
        //List for names of Trains:
        List<String> listOfTrainNames = new ArrayList<String>();
        listOfTrainNames.addAll(trains.keySet());
        List<LinkedList<String>> listOfTrainProperties = new ArrayList<LinkedList<String>>();
        listOfTrainProperties.addAll(trains.values());
        for (LinkedList<String> train : listOfTrainProperties) {
            if (train.contains(station) && Math.abs(toMinutes(time) - toMinutes(train.getFirst())) < outputTime) {
                outputTime = Math.abs(toMinutes(time) - toMinutes(train.getFirst()));
                outputTrain = train;
                outputTrain.addFirst(listOfTrainNames.get(i));
            }
            i++;
        }
        return outputTrain;
    }
}


