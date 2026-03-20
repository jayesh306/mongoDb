package com.example.Java8Lab.streams.customcollector;

import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomStringJoinCollector implements Collector<String, StringJoiner, String> {

    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public CustomStringJoinCollector(String delimiter, String prefix, String suffix) {
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public Supplier<StringJoiner> supplier() {
        return () -> new StringJoiner(delimiter,prefix,suffix);
    }

    @Override
    public BiConsumer<StringJoiner, String> accumulator() {
        return StringJoiner::add;
    }

    @Override
    public BinaryOperator<StringJoiner> combiner() {
        return StringJoiner::merge;
    }

    @Override
    public Function<StringJoiner, String> finisher() {
        return StringJoiner::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Set.of();  //No special characterstics;
    }
}
