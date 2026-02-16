public class Pokeball {
    private TipoItem item;
    private double ratioCaptura;

    public TipoItem getItem() {
        return item;

    }
    public void setItem(TipoItem item) {
        this.item = item;

    }
    public double getRatioCaptura() {
        return ratioCaptura;
    }
    public void setRatioCaptura(double ratioCaptura) {
        this.ratioCaptura = ratioCaptura;
    }

    public double calcularProbabilidadCaptura(Pokémon salvaje,int PsRestantes){
        return 2.2;
    }
}
