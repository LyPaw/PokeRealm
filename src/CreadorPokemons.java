import java.util.ArrayList;
import java.util.HashMap;

public class CreadorPokemons {


    /**
     * Generaciones
     */
    private HashMap<Generacion,ArrayList<Pokémon>> pokemonsPorGeneracion = new HashMap<>();
    private ArrayList<Pokémon> gen1 =  new ArrayList<>();
    private ArrayList<Pokémon> gen2 =  new ArrayList<>();
    private ArrayList<Pokémon> gen3 =  new ArrayList<>();

    /**
     * Ataques
     */
    Ataque ascuas = new Ataque("Ascuas",TipoPokemon.FUEGO,80,100);
    Ataque drenadoras = new Ataque("Drenadoras",TipoPokemon.PLANTA,80,100);
    Ataque latigoCepa = new Ataque("Latigo Cepa",TipoPokemon.PLANTA,80,20);
    Ataque burbuja = new Ataque("Burbuja",TipoPokemon.AGUA,40,100);


    /**
     * CreacionPokemon
     */
    
    // GEN 1
    Pokémon charmander;
    Pokémon bulbasur;
    Pokémon squirtle;
    
    // GEN 3
     Pokémon treecko;
     Pokémon torchic;
     Pokémon mudkip;

    public void inicializarPokemons(){
        /**
         * Lo he hecho asi para que sea muy facil meterlos y se puedan reutilizar
         */

        //Gen 1
        charmander =new Pokémon(1,"Charmander",TipoPokemon.FUEGO,TipoPokemon.FUEGO,Generacion.GEN1,1,39,39);
        charmander.aprenderAtaque(ascuas);
        gen1.add(charmander);
        /// //////////
        bulbasur =new Pokémon(2,"Bulbasur",TipoPokemon.PLANTA,TipoPokemon.PLANTA,Generacion.GEN1,1,45,45);
        bulbasur.aprenderAtaque(latigoCepa);
        bulbasur.aprenderAtaque(drenadoras);
        gen1.add(bulbasur);
        /// //////////
        squirtle = new Pokémon(3,"Squirtle",TipoPokemon.AGUA,TipoPokemon.AGUA,Generacion.GEN1,1,44,44);
        squirtle.aprenderAtaque(burbuja);
        gen1.add(squirtle);

        pokemonsPorGeneracion.put(Generacion.GEN1,gen1);
        /// //////////
        //Gen3
         treecko = new Pokémon(4, "Treecko", TipoPokemon.PLANTA, TipoPokemon.PLANTA, Generacion.GEN3, 1,40,40);
         treecko.aprenderAtaque(latigoCepa);
         torchic = new Pokémon(5, "Torchic", TipoPokemon.FUEGO, TipoPokemon.FUEGO, Generacion.GEN3, 1,45,45);
         torchic.aprenderAtaque(ascuas);
         mudkip = new Pokémon(6, "Mudkip", TipoPokemon.AGUA, TipoPokemon.AGUA, Generacion.GEN3, 1,50,50);
         mudkip.aprenderAtaque(burbuja);
    }


     public String AtaquesCharmander(){
        return charmander.mostrarAtaques();
    }
     public String AtaquesBulbasur(){
        return bulbasur.mostrarAtaques();
    }
     public String AtaquesSquirtle(){
        return squirtle.mostrarAtaques();
    }
    
     public String AtaquesTorchic(){return torchic.mostrarAtaques();}
     public String AtaquesTreecko(){return treecko.mostrarAtaques();}
     public String AtaquesMudkip(){return mudkip.mostrarAtaques();}


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        /**
         * Itera las claves el HashMap(Generaciones) y las va imprimiento
         */
        for (Generacion gen : pokemonsPorGeneracion.keySet()) {
            sb.append(gen).append(":\n");

            /**
             * Itera los nombre del HashMap y los imprime
             */
            for (Pokémon p : pokemonsPorGeneracion.get(gen)) {
                sb.append("  - ").append(p).append("\n");
            }
        }

        return sb.toString();
    }



}
