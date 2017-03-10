package TrainSchedule;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class Tests {
    //Карта поездов:
    private Map<String, LinkedList<String>> trains = new HashMap<String, LinkedList<String>>();

    //Поезда:
    private LinkedList<String> train1 = new LinkedList<String>();
    private LinkedList<String> train2 = new LinkedList<String>();
    private LinkedList<String> train3 = new LinkedList<String>();
    private String nameOfTrain1 = "44";
    private String nameOfTrain2 = "36";
    private String nameOfTrain3 = "1";
    private void addTrains(){
        //Поезд №1:
        train1.add("12:34");
        train1.add("Калуга");
        train1.add("Великие Луки");
        train1.add("Санкт-Петербург");
        train1.add("Брюссель");
        train1.add("Псков");
        train1.add("Оттава"); //Конечная
        trains.put(nameOfTrain1, train1);
        //Поезд №2:
        train2.add("12:35");
        train2.add("Псков"); //Конечная
        trains.put(nameOfTrain2, train2);
        //Поезд №3:
        train3.add("12:36");
        train3.add("Санкт-Петербург");
        train3.add("Тверь");
        train3.add("Псков"); //Конечная
        trains.put(nameOfTrain3, train3);
    }

    //4. Добавить поезд:
    @Test
    public void addTrain() {
        addTrains();
        TrainSchedule schedule = new TrainSchedule(trains);
        assertEquals(false, schedule.addTrain(nameOfTrain1, "12:30", "Омск"));
        assertEquals(true, schedule.addTrain("25", "17:59", "Томск"));
        trains.clear();
    }
    //5. Удалить поезд:
    @Test
    public void removeTrain() {
        addTrains();
        TrainSchedule schedule = new TrainSchedule(trains);
        assertEquals(false, schedule.removeTrain("Пхеньян"));
        assertEquals(true, schedule.removeTrain(nameOfTrain3));
    }
    //6. Добавить промежуточную станцию существующему поезду:
    @Test
    public void addTrainStation() {
        addTrains();
        TrainSchedule schedule = new TrainSchedule(trains);
        assertEquals(true, schedule.addTrainStation(nameOfTrain1, "Великий Новгород"));
        assertEquals(false, schedule.addTrainStation(nameOfTrain1, "Калуга"));
        trains.clear();
    }
    //7. Удалить промежуточную станцию для поезда:
    @Test
    public void removeTrainStation(){
        addTrains();
        TrainSchedule schedule = new TrainSchedule(trains);
        assertEquals(true, schedule.removeTrainStation(nameOfTrain3, "Тверь"));
        assertEquals(false, schedule.removeTrainStation(nameOfTrain2, "Псков"));
        assertEquals(false, schedule.removeTrainStation(nameOfTrain1, "Мурманск"));
        trains.clear();
    }
    //8. Найти ближайший поезд по времени:
    @Test
    public void searchTheNextTrain(){
        addTrains();
        TrainSchedule schedule = new TrainSchedule(trains);
        assertEquals(train2, schedule.searchTheNextTrain("Псков", "12:35"));
        assertNull(schedule.searchTheNextTrain("К", "10:00"));
        trains.clear();
    }
}


