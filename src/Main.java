import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase principal de la aplicación Pokémon con interfaz visual mejorada.
 * Muestra sprites de los Pokémon, fondos temáticos y tarjetas de información.
 */
public class Main extends Application {

    // -----------------------------------------------------------------------
    //  Constantes de diseño
    // -----------------------------------------------------------------------

    /** URL base para los sprites oficiales de la PokéAPI (front default). */
    private static final String SPRITE_BASE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

    /** Colores por tipo de Pokémon (CSS hex). */
    private static final Map<String, String> TYPE_COLORS = new HashMap<>() {{
        put("FUEGO",    "#FF9741");
        put("AGUA",     "#3892DC");
        put("PLANTA",   "#38BF4B");
        put("NORMAL",   "#919AA2");
        put("ELECTRICO","#FBD100");
        put("PSIQUICO", "#FF6675");
        put("HIELO",    "#70CBD4");
        put("DRAGON",   "#006FC9");
        put("SINIESTRO","#5B5466");
        put("HADA",     "#FB89EB");
        put("LUCHA",    "#CE4069");
        put("VENENO",   "#AB6AC8");
        put("TIERRA",   "#D97845");
        put("VOLADOR",  "#89AAE3");
        put("BICHO",    "#91C12F");
        put("ROCA",     "#C5B78C");
        put("FANTASMA", "#5269AC");
        put("ACERO",    "#5A8EA2");
    }};

    // -----------------------------------------------------------------------
    //  Estado de la aplicación
    // -----------------------------------------------------------------------

    private CreadorPokemons creador;
    private StackPane root;   // StackPane permite apilar fondo + contenido
    private VBox content;

    // -----------------------------------------------------------------------
    //  Arranque JavaFX
    // -----------------------------------------------------------------------

    @Override
    public void start(Stage stage) {
        creador = new CreadorPokemons();
        creador.inicializarPokemons();

        // Capa de fondo animada (gradiente temático)
        Pane background = crearFondo();

        // Capa de contenido principal
        content = new VBox(20);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(30));

        // ScrollPane para que el contenido sea desplazable
        ScrollPane scroll = new ScrollPane(content);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root = new StackPane(background, scroll);

        Scene scene = new Scene(root, 860, 650);
        stage.setTitle("✦ PokéRealm ✦");
        stage.setScene(scene);
        stage.show();

        mostrarPantallaInicio();
    }

    // -----------------------------------------------------------------------
    //  Fondo temático
    // -----------------------------------------------------------------------

    /**
     * Crea un panel de fondo con un degradado temático inspirado en Pokémon.
     * Usa colores azul-marino y azul claro como la pantalla de título de los juegos.
     */
    private Pane crearFondo() {
        Pane bg = new Pane();
        bg.setStyle("""
            -fx-background-image: url('file:docs/spriteV/fondoPokemon.png');
            -fx-background-size: cover;
            -fx-background-position: center;
            -fx-background-repeat: no-repeat;
            """);

        // Círculos decorativos estilo Pokéball
        for (int i = 0; i < 6; i++) {
            double size = 80 + i * 60;
            javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(size / 2);
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.rgb(255, 255, 255, 0.04));
            circle.setStrokeWidth(1.5);
            circle.setLayoutX(720 - i * 30);
            circle.setLayoutY(100 + i * 20);
            bg.getChildren().add(circle);
        }

        // Pokéball decorativa grande (esquina superior derecha)
        javafx.scene.shape.Circle pokeball = new javafx.scene.shape.Circle(90);
        pokeball.setFill(Color.TRANSPARENT);
        pokeball.setStroke(Color.rgb(255, 255, 255, 0.06));
        pokeball.setStrokeWidth(3);
        pokeball.setLayoutX(800);
        pokeball.setLayoutY(60);
        bg.getChildren().add(pokeball);

        return bg;
    }

    // -----------------------------------------------------------------------
    //  Pantalla de inicio
    // -----------------------------------------------------------------------

    private void mostrarPantallaInicio() {
        content.getChildren().clear();

        // Logo / título
        Label titulo = new Label("POKÉREALM");
        titulo.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 42px;
                -fx-font-weight: bold;
                -fx-font-family: 'Georgia';
                -fx-effect: dropshadow(gaussian, rgba(255,200,0,0.8), 20, 0.4, 0, 0);
                """);

        Label subtitulo = new Label("¿Estás listo para tu aventura?");
        subtitulo.setStyle("""
                -fx-text-fill: rgba(255,255,255,0.7);
                -fx-font-size: 16px;
                -fx-font-family: 'Georgia';
                """);

        // Imagen de Pokéball como logo
        ImageView pokeballImg = cargarSprite(0, 80);

        // Botones principales
        Button btnAventura = crearBotonPrincipal("⚔  Empezar Aventura", "#FFD700", "#333");
        btnAventura.setOnAction(e -> mostrarSeleccionGeneracion());

        Button btnVerTodo = crearBotonPrincipal("📋  Ver todos los Pokémon", "#4A90D9", "white");
        btnVerTodo.setOnAction(e -> mostrarTodo());

        VBox box = new VBox(16, titulo, subtitulo, pokeballImg, btnAventura, btnVerTodo);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(40));

        content.getChildren().add(box);
    }

    // -----------------------------------------------------------------------
    //  Pantalla: ver todos
    // -----------------------------------------------------------------------

    private void mostrarTodo() {
        content.getChildren().clear();

        Label titulo = labelTitulo("Todos los Pokémon por Generación");
        content.getChildren().add(titulo);

        // Gen 1
        content.getChildren().add(labelSeccion("— Generación 1 · Kanto —"));
        HBox gen1Row = new HBox(20);
        gen1Row.setAlignment(Pos.CENTER);
        for (Pokémon p : creador.getGen1()) gen1Row.getChildren().add(tarjetaPokemon(p));
        content.getChildren().add(gen1Row);

        // Gen 3
        content.getChildren().add(labelSeccion("— Generación 3 · Hoenn —"));
        HBox gen3Row = new HBox(20);
        gen3Row.setAlignment(Pos.CENTER);
        for (Pokémon p : creador.getGen3()) gen3Row.getChildren().add(tarjetaPokemon(p));
        content.getChildren().add(gen3Row);

        // Gen 5
        content.getChildren().add(labelSeccion("— Generación 5 · Unova —"));
        HBox gen5Row = new HBox(20);
        gen5Row.setAlignment(Pos.CENTER);
        for (Pokémon p : creador.getGen5()) gen5Row.getChildren().add(tarjetaPokemon(p));
        content.getChildren().add(gen5Row);

        content.getChildren().add(crearBotonVolver(() -> mostrarPantallaInicio()));
    }

    // -----------------------------------------------------------------------
    //  Pantalla: selección de generación
    // -----------------------------------------------------------------------

    private void mostrarSeleccionGeneracion() {
        content.getChildren().clear();

        content.getChildren().add(labelTitulo("Elige tu Generación"));
        content.getChildren().add(labelSeccion("¿Con qué región quieres comenzar?"));

        HBox genRow = new HBox(24);
        genRow.setAlignment(Pos.CENTER);

        genRow.getChildren().add(tarjetaGeneracion("GEN 1", "Kanto", "#E8A838",
                new int[]{4, 1, 7}, () -> mostrarSeleccionInicial(creador.getGen1())));
        genRow.getChildren().add(tarjetaGeneracion("GEN 3", "Hoenn", "#5DB9E0",
                new int[]{255, 252, 258}, () -> mostrarSeleccionInicial(creador.getGen3())));
        genRow.getChildren().add(tarjetaGeneracion("GEN 5", "Unova", "#78C85A",
                new int[]{498, 495, 501}, () -> mostrarSeleccionInicial(creador.getGen5())));

        content.getChildren().add(genRow);
        content.getChildren().add(crearBotonVolver(() -> mostrarPantallaInicio()));
    }

    /**
     * Tarjeta visual para cada generación con mini-sprites de sus tres iniciales.
     */
    private VBox tarjetaGeneracion(String gen, String region, String color,
                                   int[] ids, Runnable onClic) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(200);
        card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.08);
                -fx-background-radius: 18;
                -fx-border-color: %s;
                -fx-border-width: 2;
                -fx-border-radius: 18;
                -fx-cursor: hand;
                """, color));

        Label lGen = new Label(gen);
        lGen.setStyle(String.format("-fx-text-fill: %s; -fx-font-size: 22px; " +
                "-fx-font-weight: bold; -fx-font-family: 'Georgia';", color));

        Label lRegion = new Label(region);
        lRegion.setStyle("-fx-text-fill: rgba(255,255,255,0.7); -fx-font-size: 13px;");

        HBox sprites = new HBox(4);
        sprites.setAlignment(Pos.CENTER);
        for (int id : ids) sprites.getChildren().add(cargarSprite(id, 52));

        Button btn = crearBotonPrincipal("Elegir", color, "#111");
        btn.setOnAction(e -> onClic.run());

        card.getChildren().addAll(lGen, lRegion, sprites, btn);

        // Hover effect
        card.setOnMouseEntered(e -> card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.14);
                -fx-background-radius: 18;
                -fx-border-color: %s;
                -fx-border-width: 2.5;
                -fx-border-radius: 18;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, %s, 18, 0.3, 0, 0);
                """, color, color)));
        card.setOnMouseExited(e -> card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.08);
                -fx-background-radius: 18;
                -fx-border-color: %s;
                -fx-border-width: 2;
                -fx-border-radius: 18;
                -fx-cursor: hand;
                """, color)));

        return card;
    }

    // -----------------------------------------------------------------------
    //  Pantalla: selección de inicial
    // -----------------------------------------------------------------------

    private void mostrarSeleccionInicial(ArrayList<Pokémon> pokemons) {
        content.getChildren().clear();
        content.getChildren().add(labelTitulo("Elige tu Pokémon inicial"));

        HBox row = new HBox(24);
        row.setAlignment(Pos.CENTER);

        for (Pokémon p : pokemons) {
            VBox card = tarjetaPokemonSeleccionable(p);
            row.getChildren().add(card);
        }

        content.getChildren().add(row);
        content.getChildren().add(crearBotonVolver(() -> mostrarSeleccionGeneracion()));
    }

    /**
     * Tarjeta grande y clicable para seleccionar un Pokémon inicial.
     */
    private VBox tarjetaPokemonSeleccionable(Pokémon p) {
        String colorTipo = colorDeTipo(p.getTipoPrincipal().name());

        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(200);
        card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.08);
                -fx-background-radius: 20;
                -fx-border-color: %s;
                -fx-border-width: 2;
                -fx-border-radius: 20;
                -fx-cursor: hand;
                """, colorTipo));

        ImageView sprite = cargarSprite(getPokemonId(p), 100);

        Label nombre = new Label(p.getNombre());
        nombre.setStyle("-fx-text-fill: white; -fx-font-size: 18px; " +
                "-fx-font-weight: bold; -fx-font-family: 'Georgia';");

        Label tipo = etiquetaTipo(p.getTipoPrincipal().name());

        Label ps = new Label("PS: " + p.getPs());
        ps.setStyle("-fx-text-fill: rgba(255,255,255,0.75); -fx-font-size: 13px;");

        Button btn = crearBotonPrincipal("¡Elegir!", colorTipo, "#111");
        btn.setOnAction(e -> mostrarDetallePokemon(p));

        card.getChildren().addAll(sprite, nombre, tipo, ps, btn);

        card.setOnMouseEntered(ev -> card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.15);
                -fx-background-radius: 20;
                -fx-border-color: %s;
                -fx-border-width: 2.5;
                -fx-border-radius: 20;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, %s, 22, 0.4, 0, 0);
                """, colorTipo, colorTipo)));
        card.setOnMouseExited(ev -> card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.08);
                -fx-background-radius: 20;
                -fx-border-color: %s;
                -fx-border-width: 2;
                -fx-border-radius: 20;
                -fx-cursor: hand;
                """, colorTipo)));

        return card;
    }

    // -----------------------------------------------------------------------
    //  Pantalla: detalle del Pokémon elegido
    // -----------------------------------------------------------------------

    private void mostrarDetallePokemon(Pokémon p) {
        content.getChildren().clear();

        String colorTipo = colorDeTipo(p.getTipoPrincipal().name());
        int id = getPokemonId(p);

        // Cabecera con sprite grande
        VBox header = new VBox(8);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(20, 20, 10, 20));
        header.setStyle(String.format("""
                -fx-background-color: linear-gradient(to bottom, rgba(0,0,0,0.4), transparent);
                -fx-background-radius: 20;
                """));

        Label elegido = new Label("¡Has elegido a...");
        elegido.setStyle("-fx-text-fill: rgba(255,255,255,0.6); -fx-font-size: 15px;");

        Label nombre = new Label(p.getNombre().toUpperCase());
        nombre.setStyle(String.format("""
                -fx-text-fill: %s;
                -fx-font-size: 34px;
                -fx-font-weight: bold;
                -fx-font-family: 'Georgia';
                -fx-effect: dropshadow(gaussian, %s, 14, 0.5, 0, 0);
                """, colorTipo, colorTipo));

        ImageView sprite = cargarSprite(id, 140);

        header.getChildren().addAll(elegido, nombre, sprite, etiquetaTipo(p.getTipoPrincipal().name()));

        // Tarjeta de estadísticas
        VBox stats = crearTarjetaStats(p, colorTipo);

        // Tarjeta de ataques
        VBox ataques = crearTarjetaAtaques(p, colorTipo);

        HBox infoRow = new HBox(16, stats, ataques);
        infoRow.setAlignment(Pos.CENTER);

        Button btnReiniciar = crearBotonPrincipal("🔄  Reiniciar aventura", colorTipo, "#111");
        btnReiniciar.setOnAction(e -> mostrarPantallaInicio());

        content.getChildren().addAll(header, infoRow, btnReiniciar);
    }

    // -----------------------------------------------------------------------
    //  Componentes visuales de Pokémon
    // -----------------------------------------------------------------------

    /** Tarjeta compacta usada en la vista "Ver todos". */
    private VBox tarjetaPokemon(Pokémon p) {
        String colorTipo = colorDeTipo(p.getTipoPrincipal().name());
        int id = getPokemonId(p);

        VBox card = new VBox(6);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(12));
        card.setPrefWidth(140);
        card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.07);
                -fx-background-radius: 14;
                -fx-border-color: %s44;
                -fx-border-width: 1.5;
                -fx-border-radius: 14;
                """, colorTipo));

        ImageView sprite = cargarSprite(id, 72);

        Label nombre = new Label(p.getNombre());
        nombre.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-font-weight: bold;");

        Label ataques = new Label("⚔ " + p.mostrarAtaques()
                .replace("[", "").replace("]", "")
                .replace("|", "").trim());
        ataques.setWrapText(true);
        ataques.setMaxWidth(130);
        ataques.setStyle("-fx-text-fill: rgba(255,255,255,0.6); -fx-font-size: 11px;");

        card.getChildren().addAll(sprite, nombre, etiquetaTipo(p.getTipoPrincipal().name()), ataques);
        return card;
    }

    /** Tarjeta con estadísticas base del Pokémon. */
    private VBox crearTarjetaStats(Pokémon p, String colorTipo) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(18));
        card.setPrefWidth(260);
        card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.07);
                -fx-background-radius: 16;
                -fx-border-color: %s55;
                -fx-border-width: 1.5;
                -fx-border-radius: 16;
                """, colorTipo));

        Label titulo = new Label("📊  Estadísticas");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        card.getChildren().add(titulo);
        card.getChildren().add(filaStats("Generación", p.getGeneracion().name(), colorTipo));
        card.getChildren().add(filaStats("Tipo principal", p.getTipoPrincipal().name(), colorTipo));
        card.getChildren().add(filaStats("Tipo secundario", p.getTipoSecundario().name(), colorTipo));
        card.getChildren().add(filaStats("PS", p.getPs(), colorTipo));

        return card;
    }

    /** Tarjeta con los ataques del Pokémon, formateados visualmente. */
    private VBox crearTarjetaAtaques(Pokémon p, String colorTipo) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(18));
        card.setPrefWidth(260);
        card.setStyle(String.format("""
                -fx-background-color: rgba(255,255,255,0.07);
                -fx-background-radius: 16;
                -fx-border-color: %s55;
                -fx-border-width: 1.5;
                -fx-border-radius: 16;
                """, colorTipo));

        Label titulo = new Label("⚔  Ataques");
        titulo.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        card.getChildren().add(titulo);

        // Parsear los ataques quitando corchetes y pipes
        String raw = p.mostrarAtaques()
                .replace("[", "").replace("]", "").trim();

        for (String ataque : raw.split(",")) {
            String nombre = ataque.replace("|", "").trim();
            if (!nombre.isEmpty()) {
                Label lAtaque = new Label("▸  " + nombre);
                lAtaque.setStyle(String.format("""
                        -fx-text-fill: %s;
                        -fx-font-size: 13px;
                        -fx-background-color: rgba(255,255,255,0.06);
                        -fx-background-radius: 8;
                        -fx-padding: 6 12 6 12;
                        """, colorTipo));
                card.getChildren().add(lAtaque);
            }
        }

        return card;
    }

    /** Fila de clave-valor para las estadísticas. */
    private HBox filaStats(String clave, String valor, String colorTipo) {
        Label lClave = new Label(clave + ":");
        lClave.setStyle("-fx-text-fill: rgba(255,255,255,0.5); -fx-font-size: 13px;");
        lClave.setMinWidth(120);

        Label lValor = new Label(valor);
        lValor.setStyle(String.format("-fx-text-fill: %s; -fx-font-size: 13px; " +
                "-fx-font-weight: bold;", colorTipo));

        HBox row = new HBox(8, lClave, lValor);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    // -----------------------------------------------------------------------
    //  Utilidades de UI
    // -----------------------------------------------------------------------

    /** Carga el sprite de un Pokémon desde la PokéAPI. Si falla, muestra una "?" */
    private ImageView cargarSprite(int id, int size) {
        ImageView iv = new ImageView();
        iv.setFitWidth(size);
        iv.setFitHeight(size);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);

        if (id > 0) {
            try {
                Image img = new Image(SPRITE_BASE + id + ".png", size, size, true, true, true);
                iv.setImage(img);
            } catch (Exception ignored) {
                // Si no carga, queda vacío
            }
        }
        return iv;
    }

    /** Devuelve la ID numérica del Pokémon leyendo el campo privado vía toString() workaround. */
    private int getPokemonId(Pokémon p) {
        // Los IDs están hardcodeados en CreadorPokemons, así que usamos un mapa por nombre
        Map<String, Integer> ids = new HashMap<>() {{
            put("Bulbasur", 1);  put("Bulbasaur", 1);
            put("Charmander", 4);
            put("Squirtle", 7);
            put("Treecko", 252);
            put("Torchic", 255);
            put("Mudkip", 258);
            put("Snivy", 495);
            put("Tepig", 498);
            put("Oshawott", 501);
        }};
        return ids.getOrDefault(p.getNombre(), 0);
    }

    /** Etiqueta de tipo con fondo de color. */
    private Label etiquetaTipo(String tipo) {
        String color = colorDeTipo(tipo);
        Label l = new Label(tipo);
        l.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-text-fill: white;
                -fx-font-size: 11px;
                -fx-font-weight: bold;
                -fx-padding: 3 10 3 10;
                -fx-background-radius: 20;
                """, color));
        return l;
    }

    /** Devuelve el color hex asociado a un tipo Pokémon. */
    private String colorDeTipo(String tipo) {
        return TYPE_COLORS.getOrDefault(tipo.toUpperCase(), "#888888");
    }

    /** Label de título principal. */
    private Label labelTitulo(String texto) {
        Label l = new Label(texto);
        l.setStyle("""
                -fx-text-fill: white;
                -fx-font-size: 26px;
                -fx-font-weight: bold;
                -fx-font-family: 'Georgia';
                """);
        return l;
    }

    /** Label de sección/subtítulo. */
    private Label labelSeccion(String texto) {
        Label l = new Label(texto);
        l.setStyle("-fx-text-fill: rgba(255,255,255,0.55); -fx-font-size: 14px;");
        return l;
    }

    /** Botón con color de fondo personalizable. */
    private Button crearBotonPrincipal(String texto, String bgColor, String textColor) {
        Button btn = new Button(texto);
        btn.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-text-fill: %s;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-background-radius: 24;
                -fx-padding: 10 26 10 26;
                -fx-cursor: hand;
                """, bgColor, textColor));
        btn.setOnMouseEntered(e -> btn.setStyle(String.format("""
                -fx-background-color: derive(%s, 20%%);
                -fx-text-fill: %s;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-background-radius: 24;
                -fx-padding: 10 26 10 26;
                -fx-cursor: hand;
                -fx-effect: dropshadow(gaussian, %s, 12, 0.3, 0, 0);
                """, bgColor, textColor, bgColor)));
        btn.setOnMouseExited(e -> btn.setStyle(String.format("""
                -fx-background-color: %s;
                -fx-text-fill: %s;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-background-radius: 24;
                -fx-padding: 10 26 10 26;
                -fx-cursor: hand;
                """, bgColor, textColor)));
        return btn;
    }

    /** Botón "Volver" estándar. */
    private Button crearBotonVolver(Runnable accion) {
        Button btn = crearBotonPrincipal("← Volver", "rgba(255,255,255,0.12)", "white");
        btn.setOnAction(e -> accion.run());
        return btn;
    }

    // -----------------------------------------------------------------------
    //  Main
    // -----------------------------------------------------------------------

    public static void main(String... args) {
        launch(args);
    }
}