package ManejoDeUsuarios;

public enum Opinion {

    // VINCHUCA ahora toma un argumento String para la especie
    VINCHUCA("Se ha identificado una vinchuca de especie: "),
    CHINCHEFOLIADA("La imagen corresponde a una Chinche Foliada."),
    PHTIACHINCHE("La imagen corresponde a una Phthia Chinche."),
    NINGUNA("No se ha identificado ninguna vinchuca o chinche."),
    IMAGENPOCOCLARA("La imagen no es lo suficientemente clara para una determinación.");

    // Atributo para almacenar el mensaje asociado a cada opinión
    private final String mensajeAsociado;

    Opinion(String mensaje) {
        this.mensajeAsociado = mensaje;
    }

    // Método público para acceder al mensaje asociado a cada opinión
    public String getMensajeAsociado() {
        return this.mensajeAsociado;
    }

}
