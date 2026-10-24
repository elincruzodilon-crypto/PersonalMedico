package tarea1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;


// 1. CLASE PRINCIPAL (CONTIENE EL MAIN)
public class Main {
    public static void main(String[] args) {

        //CREACIÓN DE OBJETOS USANDO LA CLASE PADRE Y EL CONSTRUCTOR DE LAS HIJAS
        // Se intercalan variables con tipo primitivo (int) y tipo objeto (Integer) con el mismo valor numérico
        PersonalMedico medico1 = new Cirujano("Dr. Méndez", 45, Integer.valueOf(45), "Alta Especialidad");
        PersonalMedico medico2 = new Pediatra("Dra. Sanabria", 38, Integer.valueOf(38), 14);
        PersonalMedico medico3 = new Cardiologo("Dr. Vega", 52, Integer.valueOf(52), true);

        // Agrupamos los objetos en un arreglo de la clase padre
        PersonalMedico[] personalHospital = { medico1, medico2, medico3 };

        System.out.println("--- EJECUCIÓN DEL POLIMORFISMO EN MEDICINA ---");
        // CICLO QUE EJECUTA EL METODO EN TODOS LOS OBJETOS
        for (PersonalMedico medico : personalHospital) {
            medico.atenderPaciente(); // Cada objeto responde según su propia especialidad
        }
        System.out.println();

        System.out.println(" GESTIÓN DE INVENTARIO Y REGISTROS MÉDICOS ");

        //USO DE ARRAYLIST (Tipo de dato 1: String)
        ArrayList<String> medicamentos = new ArrayList<>();
        medicamentos.add("Paracetamol");
        medicamentos.add("Ibuprofeno");
        System.out.print("ArrayList (Medicamentos disponibles): ");
        for (String med : medicamentos) {
            System.out.print("[" + med + "] ");
        }
        System.out.println("\n");

        //REQUISITO: USO DE LINKEDLIST (Tipo de dato 2: Double, Tipo de dato 3: Boolean)
        LinkedList<Double> dosisGramos = new LinkedList<>();
        dosisGramos.add(0.50); // Dosis exacta de un medicamento

        LinkedList<Boolean> esUrgencia = new LinkedList<>();
        esUrgencia.add(false); // Estado de Triage en sala de espera

        System.out.println("LinkedList (Dosis recomendada): " + dosisGramos.get(0) + "g");
        System.out.println("LinkedList (¿Es urgencia?): " + esUrgencia.get(0) + "\n");

        //REQUISITO: USO DE HASHSET (Tipo de dato 4: Character, Tipo de dato 5: Long)
        HashSet<Character> tipoSangre = new HashSet<>();
        tipoSangre.add('O'); // Grupo sanguíneo en el banco de sangre

        HashSet<Long> idExpedientes = new HashSet<>();
        idExpedientes.add(8402910392L); // ID largo y único de historial clínico

        System.out.println("HashSet (Grupo sanguíneo registrado): " + tipoSangre);
        System.out.println("HashSet (ID único de expediente): " + idExpedientes + "\n");

        //REQUISITO: USO DE HASHMAP (Tipo de dato 6: Short, Tipo de dato 7: Float)
        // Relaciona el número de cama (Short) con la temperatura actual del paciente (Float)
        HashMap<Short, Float> registroTemperatura = new HashMap<>();
        short numeroCama = 104;
        float temperaturaPaciente = 36.7f;
        registroTemperatura.put(numeroCama, temperaturaPaciente);

        System.out.println("HashMap (Monitoreo de camas y temperatura):");
        for (Map.Entry<Short, Float> registro : registroTemperatura.entrySet()) {
            System.out.println("  Cama hospitalaria N° (Short): " + registro.getKey() +
                    " -> Temperatura medida (Float): " + registro.getValue() + "°C");
        }

        // LOS 7 TIPOS DE DATOS DISTINTOS QUE FUERON DECLARADOS Y RECORRIDOS:
        // 1. String       4. Character   7. Float
        // 2. Double       5. Long
        // 3. Boolean      6. Short
    }
}

// 2. CLASE PADRE (ENFOQUE MÉDICO)
class PersonalMedico {
    // Atributo protegido: accesible por las clases especialistas (hijas)
    protected String nombre;

    // Atributos privados: encapsulados, accesibles solo por métodos getter/setter
    private int edadPrimitiva; // Tipo de dato primitivo (int)
    private Integer edadObjeto; // Tipo de dato objeto / no primitivo (Integer)

    // Constructor de la clase padre
    public PersonalMedico(String nombre, int edadPrimitiva, Integer edadObjeto) {
        this.nombre = nombre;
        this.edadPrimitiva = edadPrimitiva;
        this.edadObjeto = edadObjeto;
    }

    // Metodo que sera reinterpretado  (Polimorfismo)
    public void atenderPaciente() {
        System.out.println("El profesional " + nombre + " está brindando atención médica general.");
    }

    // Métodos Getter y Setter para mantener el Encapsulamiento
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdadPrimitiva() { return edadPrimitiva; }
    public void setEdadPrimitiva(int edadPrimitiva) { this.edadPrimitiva = edadPrimitiva; }

    public Integer getEdadObjeto() { return edadObjeto; }
    public void setEdadObjeto(Integer edadObjeto) { this.edadObjeto = edadObjeto; }
}


// 3. CLASES HIJAS (DEMOSTRACIÓN DE HERENCIA)

// Clase hija 1: Cirujano
class Cirujano extends PersonalMedico {
    private String tipoQuirofano;

    public Cirujano(String nombre, int edadPrimitiva, Integer edadObjeto, String tipoQuirofano) {
        super(nombre, edadPrimitiva, edadObjeto); // Invoca al constructor padre
        this.tipoQuirofano = tipoQuirofano;
    }

    @Override // Reinterpretación del metodo  (Polimorfismo)
    public void atenderPaciente() {
        System.out.println("El Cirujano " + nombre + " está operando en el quirófano de " + tipoQuirofano +
                ". Edad (prim): " + getEdadPrimitiva() + ", Edad (obj): " + getEdadObjeto());
    }
}

// Clase hija 2: Pediatra
class Pediatra extends PersonalMedico {
    private int maxEdadPacientes;

    public Pediatra(String nombre, int edadPrimitiva, Integer edadObjeto, int maxEdadPacientes) {
        super(nombre, edadPrimitiva, edadObjeto);
        this.maxEdadPacientes = maxEdadPacientes;
    }

    @Override // Reinterpretación del metodo (Polimorfismo)
    public void atenderPaciente() {
        System.out.println("La Pediatra " + nombre + " atiende a niños de hasta " + maxEdadPacientes + " años" +
                ". Edad (prim): " + getEdadPrimitiva() + ", Edad (obj): " + getEdadObjeto());
    }
}

// Clase hija 3: Cardiologo
class Cardiologo extends PersonalMedico {
    private boolean realizaCirugiaAbierta;

    public Cardiologo(String nombre, int edadPrimitiva, Integer edadObjeto, boolean realizaCirugiaAbierta) {
        super(nombre, edadPrimitiva, edadObjeto);
        this.realizaCirugiaAbierta = realizaCirugiaAbierta;
    }

    @Override // Reinterpretación del metodo (Polimorfismo)
    public void atenderPaciente() {
        String realiza = realizaCirugiaAbierta ? "Sí" : "No";
        System.out.println("El Cardiólogo " + nombre + " realiza electrogramas. ¿Opera a corazón abierto?: " + realiza +
                ". Edad (prim): " + getEdadPrimitiva() + ", Edad (obj): " + getEdadObjeto());
    }
}