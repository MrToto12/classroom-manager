package Main;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public abstract class DayOfWeekTranslator {
    public static DayOfWeek getDayOfWeek(String input) throws IllegalArgumentException{
        Map<String, DayOfWeek> spanishToEnglishMap = new HashMap<>();
        spanishToEnglishMap.put("LUNES", DayOfWeek.MONDAY);
        spanishToEnglishMap.put("MARTES", DayOfWeek.TUESDAY);
        spanishToEnglishMap.put("MIÉRCOLES", DayOfWeek.WEDNESDAY);
        spanishToEnglishMap.put("JUEVES", DayOfWeek.THURSDAY);
        spanishToEnglishMap.put("VIERNES", DayOfWeek.FRIDAY);
        spanishToEnglishMap.put("SÁBADO", DayOfWeek.SATURDAY);
        spanishToEnglishMap.put("DOMINGO", DayOfWeek.SUNDAY);

        String uppercasedInput = input.toUpperCase();
        if(!spanishToEnglishMap.containsKey(uppercasedInput)){
            IllegalArgumentException diaErroneo = new IllegalArgumentException();
            throw diaErroneo;
        }
        return spanishToEnglishMap.get(uppercasedInput);
    }

    public static String getDiaDeLaSemana(DayOfWeek dayOfWeek){
        Map<DayOfWeek, String> englishToSpanishMap = new HashMap<>();
        englishToSpanishMap.put(DayOfWeek.MONDAY, "Lunes");
        englishToSpanishMap.put(DayOfWeek.TUESDAY, "Martes");
        englishToSpanishMap.put(DayOfWeek.WEDNESDAY, "Miercoles");
        englishToSpanishMap.put(DayOfWeek.THURSDAY, "Jueves");
        englishToSpanishMap.put(DayOfWeek.FRIDAY, "Viernes");
        englishToSpanishMap.put(DayOfWeek.SATURDAY, "Sabado");
        englishToSpanishMap.put(DayOfWeek.SUNDAY, "Domingo");

        return englishToSpanishMap.get(dayOfWeek);
    }
}
