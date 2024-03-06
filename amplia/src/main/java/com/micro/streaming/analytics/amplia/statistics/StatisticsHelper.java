package com.micro.streaming.analytics.amplia.statistics;

import javax.swing.*;
import java.util.*;

public class StatisticsHelper { // Helper class to do statistics calcs, always working with a StatisticsAnalysis object

    // Media: Addition of all the measurements divided by the total number of added values
    public static double getMedia(ArrayList<Integer> valuesList) {
        int sumValues = 0;
        for (int val : valuesList) {
            sumValues += val;
        }

        return (double) sumValues / valuesList.size();
    }

    // Mediana: Find the number in between the set of values. If it's odd is the middle one if its not its the two halves of the value
    // set divided by two and added together
    public static double getMediana(ArrayList<Integer> valuesList) {
        // We get the list of the values and get them in order
        valuesList.sort(Comparator.naturalOrder());

        if (valuesList.size() > 1) {
            // We do the calc
            if (valuesList.size() % 2 == 0) {
                return (double) valuesList.get(valuesList.size() / 2);

            } else {
                return (double) (valuesList.get(valuesList.size() / 2) + valuesList.get(valuesList.size() / 2 - 1))/2;
            }
        } else {
            return valuesList.get(0);
        }
    }

    // Moda: The value with the most frecuency in the value set.
    public static ArrayList<Integer> getModa(ArrayList<Integer> valuesList) {

        // Get the frequency of each integer in the values set
        Map<Integer, Integer> frequency = new HashMap<>();
        valuesList.forEach(val -> {
            frequency.put(val, frequency.getOrDefault(val, 0) + 1);
        });

        // Find value(s) wit the most frequency
        int maxFrequency = 0;
        ArrayList<Integer> moda = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            int value = entry.getKey();
            int freq = entry.getValue();

            if (freq > maxFrequency) {
                maxFrequency = freq;
                moda.clear();
                moda.add(value);
            } else if (freq == maxFrequency) {
                moda.add(value);
            }
        }

        return moda;
    }

    // Desviacion estandar:
    public static double getDesviacionEstandar(ArrayList<Integer> valuesList) {
        // Get media
        double media = getMedia(valuesList);

        // Calculate the sum of the squares of the differences between each value and the media.
        double addedSquaresDifference = 0;
        for (int val : valuesList) {
            double diferencia = val - media;
            addedSquaresDifference += Math.pow(diferencia, 2);
        }

        // Divide the addition with the total number of values
        double variance = addedSquaresDifference / valuesList.size();

        // Do the square root of the variance
        return Math.sqrt(variance);
    }

    public static Map<String, Double> getCuartiles(ArrayList<Integer> valuesList) {
        // Instantiate a hashmap where we'll store our results (second cuartil is mediana)
        Map<String, Double> cuartilObject = new HashMap<>();
        cuartilObject.put("Q1", (double) 0);
        cuartilObject.put("Q2", getMediana(valuesList));
        cuartilObject.put("Q3", (double) 0);

        // Order value set
        valuesList.sort(Comparator.naturalOrder());
        ArrayList<Integer> cuartiles = new ArrayList<Integer>(Arrays.asList(25, 75));

        cuartiles.forEach(cuartil -> {
            double position = (double) (cuartil / 100) * (valuesList.size() - 1);
            double value = 0;
            // Verificar si la posición es un número entero
            if (position % 1 == 0) {
                // Si es entero, devolver el valor en esa posición
                value = valuesList.get((int) position);

            } else {
                // Si no es entero, interpolar entre los valores circundantes
                int inferior = (int) Math.floor(position);
                int superior = (int) Math.ceil(position);
                double valorInferior = valuesList.get(inferior);
                double valorSuperior = valuesList.get(superior);

                value = valorInferior + (position - inferior) * (valorSuperior - valorInferior);
            }

            if (cuartil.equals(25)) {
                cuartilObject.put("Q1", value);
            } else {
                cuartilObject.put("Q3", value);
            }
        });

        return cuartilObject;
    }

    public static Integer getMinValue(ArrayList<Integer> valuesList) {
        Integer minValue = valuesList.get(0);

        for (int val : valuesList) {
            if (val < minValue) {
                minValue = val;
            }
        }

        return minValue;
    }

    public static Integer getMaxValue(ArrayList<Integer> valuesList) {
        Integer maxValue = valuesList.get(0);

        for (int val : valuesList) {
            if (val > maxValue) {
                maxValue = val;
            }
        }

        return maxValue;
    }
}
