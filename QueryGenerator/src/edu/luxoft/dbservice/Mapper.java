package edu.luxoft.dbservice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Mapper<T> {

    //How to take
    List<Function<String, String>> takerList = new ArrayList<>();

    //How to put
    List<BiConsumer<T, String>> consumerList = new ArrayList<>();

    public Mapper() {
    }

    public Mapper<T> setRule(Function<String, String> taker, BiConsumer<T, String> consumer){
        takerList.add(taker);
        consumerList.add(consumer);

        return this;
    }


    public String take(String line,Function<String, String> taker){
        return taker.apply(line);
    }
}
