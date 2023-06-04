import java.util.ArrayList;
import java.util.AbstractMap.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Klasyfikator {
    public static Double liczEuklides(ArrayList<Double> wektor1, ArrayList<Double> wektor2){
        Double wynik = 0.0;
        for (int i = 0; i < wektor1.size(); i++) {
            wynik = wynik + Math.pow(wektor1.get(i)-wektor2.get(i),2);
        }
        return wynik;
    }
    public static Map<Object, Long> klasyfikuj(ArrayList<SimpleImmutableEntry<String,ArrayList<Double>>> trainset, ArrayList<ArrayList<Double>> testset, int k){
        Map<Object, Long> aa;
        aa = testset.stream()
                .map(x -> trainset.stream()
                        .map(s -> new SimpleImmutableEntry(s.getKey(),liczEuklides(x,s.getValue())))
                        .sorted((o1,o2) -> {
                            if((Double)(o1.getValue()) > (Double) (o2.getValue())){
                                return 1;
                            }
                            if((Double)(o2.getValue()) > (Double) (o1.getValue())){
                                return -1;
                            }
                            if((o1.getValue()) == (o2.getValue())){
                                return 1;
                            }
                            return 1;
                        })
                        .limit(k)
                        .collect(Collectors.toCollection(ArrayList<SimpleImmutableEntry>::new)))
                .map(x-> x.stream()
                        .collect(Collectors.groupingBy(s -> s.getKey(), Collectors.counting())))
                .map(x-> x.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(1)
                        .collect(Collectors.toMap(s -> s.getKey(), a -> a.getValue())))
                .collect(Collectors.groupingBy(x-> x.keySet(), Collectors.counting()));
        return aa;
    }
}
