package proyecto.codigoInterno.Animales.Bosque;

import java.util.concurrent.*;

import proyecto.codigoInterno.Alimento.Carnivoro;
import proyecto.codigoInterno.Animales.Animal;

public class Buho extends Animal implements Carnivoro{
    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> futureTask;
    private boolean come;
    private String nombre;

    public Buho(String nombre){
        super(nombre);
        this.come = false;
    }
    public String getEspecie(){
        return "Buho";
    }
    public String getHabitat(){
        return "Bosque";
    }
    public String getNombre(){
        return nombre;
    }
    @Override
    public boolean comerCarne(boolean come) {
        this.come = come;
        return come;
    }
    @Override
    public String pedirCarne() {
        if(comerCarne(come)){
            futureTask.cancel(true);
        }
        futureTask = scheduler.schedule(() -> {
            // Aquí asumimos que la clase gestora tiene un método para manejar la desaparición
            desaparecer();
        }, 5, TimeUnit.MINUTES);
        return "Necesita carne...";
    }
    @Override
    public void desaparecer(){
        
        System.out.println("La foca ha desaparecido de la lista por no recibir carne en 5 minutos.");
    }
}
