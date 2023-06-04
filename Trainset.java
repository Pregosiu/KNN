import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.stream.Collectors;
import java.util.AbstractMap.*;

public class Trainset {
    public Trainset() {
    }

    public static ArrayList<SimpleImmutableEntry<String, ArrayList<Double>>> returnTrainset(String path) throws IOException {
        ArrayList<SimpleImmutableEntry<String, ArrayList<Double>>> map;
        Path paths = Paths.get(path);
        map = Files.lines(paths)
                .map(x -> x.split(";"))
                .map(x -> new SimpleImmutableEntry<>(x[x.length - 1], Arrays.stream(x).limit(x.length-1).map(s -> Double.parseDouble(s)).collect(Collectors.toCollection(ArrayList::new))))
                .collect(Collectors.toCollection(ArrayList::new));
        return map;
    }

    public static ArrayList<SimpleImmutableEntry<String, ArrayList<Double>>> returnNamedTrainset(ArrayList<SimpleImmutableEntry<String, ArrayList<Double>>> trainset, String nazwa) {

        trainset.stream()
                .filter(x -> x.getKey().equals(nazwa))
                .forEach(System.out::println);

        return trainset.stream()
                .filter(x -> x.getKey().equals(nazwa))
                .collect(Collectors.toCollection(ArrayList::new));

    }


    public static ArrayList<ArrayList<Double>> returnTestset(String path) throws IOException {
        return Files.lines(Path.of(path))
                .map(x -> x.split(";"))
                .map(x -> Arrays.stream(x)
                        .limit(x.length-1)
                        .map(s -> Double.parseDouble(s))
                        .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));

    }
}
